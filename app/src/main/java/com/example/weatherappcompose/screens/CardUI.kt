package com.example.weatherappcompose.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherappcompose.R
import com.example.weatherappcompose.data.WeatherModel
import com.example.weatherappcompose.ui.theme.Main


@Composable
fun ListItem(item: WeatherModel, currentDays: MutableState<WeatherModel>) {
    val romulFontFamily = FontFamily(Font(R.font.sf_pro_display_light))
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .clickable {
                if (item.hours.isEmpty()) return@clickable
                currentDays.value = item
            },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.padding(start = 8.dp, top = 5.dp, bottom = 5.dp)) {
                Text(
                    text = item.time, color = MaterialTheme.colorScheme.onPrimary,
                    style = TextStyle(fontSize = 14.sp, fontFamily = romulFontFamily)
                )
                Text(
                    text = item.condition, color = MaterialTheme.colorScheme.onPrimary,
                    style = TextStyle(fontSize = 14.sp, fontFamily = romulFontFamily)
                )
            }
            Text(
                text = item.currentTemp.ifEmpty { "${item.maxTemp} °C /${item.minTemp} °C" },
                color = MaterialTheme.colorScheme.onPrimary,
                style = TextStyle(fontSize = 25.sp, fontFamily = romulFontFamily)
            )
            AsyncImage(
                model = "https:${item.icon}",
                contentDescription = "image2",
                modifier = Modifier
                    .size(40.dp)
                    .padding(top = 10.dp, end = 5.dp)
            )
        }

    }
}

@Composable
fun MainList(list: List<WeatherModel>, currentDays: MutableState<WeatherModel>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(
            list
        ) { _, item ->
            ListItem(item, currentDays)

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogSearch(dialogState: MutableState<Boolean>, onSubmit: (String) -> Unit) {
    val romulFontFamily = FontFamily(Font(R.font.sf_pro_display_light))
    val dialogText = remember {
        mutableStateOf(value = "")
    }
    AlertDialog(onDismissRequest = {
        dialogState.value = false

    }, confirmButton = {
        TextButton(onClick = {
            onSubmit(dialogText.value)
            dialogState.value = false
        }) {
            Text(
                text = "OK",
                style = TextStyle(fontFamily = romulFontFamily),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

    }, dismissButton = {
        TextButton(onClick = { dialogState.value = false }) {
            Text(
                text = "Cancel",
                style = TextStyle(fontFamily = romulFontFamily),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    },
        title = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Enter the name of the city",
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontFamily = romulFontFamily,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
                TextField(value = dialogText.value, onValueChange = {
                    dialogText.value = it
                })
            }

        })
}


