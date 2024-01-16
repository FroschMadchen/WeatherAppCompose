package com.example.weatherappcompose.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherappcompose.R
import com.example.weatherappcompose.ui.theme.Main
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.style.TextAlign
import com.example.weatherappcompose.ThemeMutable
import com.example.weatherappcompose.data.WeatherModel
import com.google.accompanist.pager.pagerTabIndicatorOffset
import org.json.JSONArray
import org.json.JSONObject


@Composable
fun MainCard(
    currentDay: MutableState<WeatherModel>,
    onClickSync: () -> Unit,
    onClickSearch: () -> Unit,
    onThemeUpdated: (Boolean) -> Unit,
    darkTheme:Boolean
) {
    val romulFontFamily = FontFamily(Font(R.font.sf_pro_display_light))

    Column(
        modifier = Modifier
            //  .fillMaxSize()
            .padding(5.dp),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            elevation = CardDefaults.cardElevation(0.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                        text = currentDay.value.time,
                        style = TextStyle(fontSize = 18.sp, fontFamily = romulFontFamily),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                   /* AsyncImage(
                        model = "https:" + currentDay.value.icon,
                        contentDescription = "image2",
                        modifier = Modifier
                            .size(70.dp)
                            .padding(top = 10.dp, end = 5.dp)

                    )*/
                    Box(modifier = Modifier.padding(end=10.dp)
                        ){
                        ThemeMutable(darkTheme = darkTheme , onThemeUpdated = onThemeUpdated)
                    }

                }
                AsyncImage(
                    model = "https:" + currentDay.value.icon,
                    contentDescription = "image2",
                    modifier = Modifier
                        .size(70.dp)
                        .padding(top = 10.dp, end = 5.dp)

                )
                Text(
                    text = currentDay.value.city,
                    style = TextStyle(fontSize = 24.sp, fontFamily = romulFontFamily),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = if (currentDay.value.currentTemp.isNotEmpty())
                        currentDay.value.currentTemp.toFloat().toInt().toString() + "℃"
                    else "${
                        currentDay.value.maxTemp.toFloat().toInt()
                    }℃/ ${currentDay.value.minTemp.toFloat().toInt()}℃",
                    style = TextStyle(fontSize = 60.sp, fontFamily = romulFontFamily),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(

                    text = currentDay.value.condition,
                    style = TextStyle(
                        fontSize = 40.sp,
                        fontFamily = romulFontFamily,
                        textAlign = TextAlign.Center
                    ),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { onClickSearch.invoke() }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_search_24),
                            contentDescription = "icon",
                            tint = Color.White
                        )
                    }
                    Text(
                        text = " ${currentDay.value.maxTemp}℃/${currentDay.value.minTemp}℃",
                        style = TextStyle(fontSize = 18.sp, fontFamily = romulFontFamily),
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    IconButton(
                        onClick = {
                            onClickSync.invoke()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.cloud_sync_icon),
                            contentDescription = "icon2",
                            tint = Color.White
                        )

                    }
                }
            }


        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout(daysList: MutableState<List<WeatherModel>>, currentDays: MutableState<WeatherModel>) {
    val tabList = listOf("HOURS", "DAYS")
    val pagerState = rememberPagerState()
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(start = 3.dp, end = 5.dp)
            .clip(RoundedCornerShape(5.dp))
    ) {
        TabRow(
            selectedTabIndex = tabIndex,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    color = MaterialTheme.colorScheme.onPrimary
                )

            },
            contentColor = MaterialTheme.colorScheme.onPrimary,
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            tabList.forEachIndexed { index, s ->
                Tab(
                    selected = false,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(text = s)
                    })
            }
        }
        HorizontalPager(
            count = tabList.size,
            state = pagerState,
            modifier = Modifier.weight(1.0f)
        ) { index ->
            val list = when (index) {
                0 -> getWeatherByHours(currentDays.value.hours)
                1 -> daysList.value
                else -> daysList.value
            }
            MainList(list, currentDays)
        }
    }

}

private fun getWeatherByHours(hours: String): List<WeatherModel> {
    if (hours.isEmpty()) return listOf()
    val hoursArray = JSONArray(hours)
    val list = ArrayList<WeatherModel>()
    for (i in 0 until hoursArray.length()) {
        val item = hoursArray[i] as JSONObject
        list.add(
            WeatherModel(
                "",
                item.getString("time"),
                item.getString("temp_c").toFloat().toInt().toString() + "℃",
                item.getJSONObject("condition").getString("text"),
                item.getJSONObject("condition").getString("icon"),
                "",
                "",
                ""
            )
        )
    }
    return list
}


@Preview(showBackground = true)
@Composable
fun TabLayoutV2() {
    val state = remember { mutableStateOf(0) }
    val titles = listOf("HOURS", "DAYS")
    Column() {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clip(RoundedCornerShape(5.dp))
        ) {

            TabRow(
//            modifier = Modifier.clip(),
                containerColor = Main,
                selectedTabIndex = state.value,


                ) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        selected = state.value == index,
                        onClick = { state.value = index },
                        text = {
                            Text(
                                text = title,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                color = Color.White
                            )
                        },
                    )

                }
            }
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Text tab ${state.value + 1} selected",
            )
        }
    }
}














