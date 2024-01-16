package com.example.weatherappcompose

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherappcompose.data.WeatherModel
import com.example.weatherappcompose.screens.DialogSearch
import com.example.weatherappcompose.screens.MainCard

import com.example.weatherappcompose.screens.TabLayout
import com.example.weatherappcompose.screens.TabLayoutV2
import com.example.weatherappcompose.ui.theme.Main
import com.example.weatherappcompose.ui.theme.WeatherAppComposeTheme
import org.json.JSONObject

const val API_KEY = "d573dcacbf8f43b4a2985122232512"
var CITY = "Moscow"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.statusBarColor = getColor(R.color.ee)
            MainScreen(context = this)
        }
    }
}


@Composable
fun MainScreen(context: Context) {

    var darkTheme by remember { mutableStateOf(false) }
    WeatherAppComposeTheme(darkTheme = darkTheme) {


        val daysList = remember {
            mutableStateOf(listOf<WeatherModel>())
        }


        val currentDay = remember {
            mutableStateOf(
                WeatherModel(
                    "",
                    "",
                    "0.0",
                    "",
                    "",
                    "0.0",
                    "0.0",
                    "",
                )
            )
        }
        val dialogState = remember {
            mutableStateOf(false)
        }
        if (dialogState.value) {
            DialogSearch(dialogState, onSubmit = {

                getData(it, context, daysList, currentDay)
                CITY = it
            }
            )
        }
        getData(CITY, context, daysList, currentDay)
        if (darkTheme) {
            Image(
                painter = painterResource(id = R.drawable.blue),
                contentDescription = "image",
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.6f),
                contentScale = ContentScale.Crop
            )
        }else{
            Image(
                painter = painterResource(id = R.drawable.image_1),
                contentDescription = "image",
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.8f),
                contentScale = ContentScale.Crop
            )// растягивание изображения
        }


        /* ThemeMutable(darkTheme = darkTheme,
             onThemeUpdated = { updatedTheme -> darkTheme = updatedTheme })
 */

        Column {
            MainCard(currentDay, onClickSync = {
                getData(CITY, context, daysList, currentDay)
            }, onClickSearch = {
                dialogState.value = true
            }, onThemeUpdated = { updatedTheme ->
                darkTheme = updatedTheme
            },darkTheme)
                TabLayout(daysList, currentDay)
            }
        }
    }


    private fun getData(
        city: String,
        context: Context,
        daysList: MutableState<List<WeatherModel>>,
        currentDay: MutableState<WeatherModel>
    ) {
        val url = "https://api.weatherapi.com/v1/forecast.json?key=$API_KEY" +
                "&q=$city" +
                "&days=" +
                "3" +
                "&aqi=no&alerts=no"
        val queue = Volley.newRequestQueue(context)
        val sRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                val list = getWeatherByDays(response)
                Log.d("MyLog", "Response: $response")
                currentDay.value = list[0]
                daysList.value = list


            },
            {
                Log.d("MyLog", "VolleyError: $it")
            }
        )
        queue.add(sRequest)
    }


    private fun getWeatherByDays(response: String): List<WeatherModel> {
        if (response.isEmpty()) return listOf()
        val list = ArrayList<WeatherModel>()
        val mainObject = JSONObject(response)
        val city = mainObject.getJSONObject("location").getString("name")
        val days = mainObject.getJSONObject("forecast").getJSONArray("forecastday")

        for (i in 0 until days.length()) {
            val item = days[i] as JSONObject
            list.add(
                WeatherModel(
                    city,
                    item.getString("date"),
                    "",
                    item.getJSONObject("day").getJSONObject("condition")
                        .getString("text"),
                    item.getJSONObject("day").getJSONObject("condition")
                        .getString("icon"),
                    item.getJSONObject("day").getString("maxtemp_c"),
                    item.getJSONObject("day").getString("mintemp_c"),
                    item.getJSONArray("hour").toString()
                )
            )
        }
        list[0] = list[0].copy(
            time = mainObject.getJSONObject("current").getString("last_updated"),
            currentTemp = mainObject.getJSONObject("current").getString("temp_c"),
        )
        return list
    }

    @Composable
    fun ThemeMutable(darkTheme: Boolean = false, onThemeUpdated: (Boolean) -> Unit) {
        /*Row(
            Modifier
                .padding(end = 16.dp)
                .fillMaxWidth(),
            //horizontalArrangement = Arrangement.SpaceBetween
        ) {
*/
            //val checkedState = remember { mutableStateOf(true) }
            //TextTitle("Лакомка из Строгино  ")


            Switch(
                checked = darkTheme,
                onCheckedChange = {
                    onThemeUpdated(!darkTheme)

                }
            )
        }
