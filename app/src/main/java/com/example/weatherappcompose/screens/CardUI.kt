package com.example.weatherappcompose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherappcompose.R
import com.example.weatherappcompose.data.WeatherModel
import com.example.weatherappcompose.ui.theme.Main


@Composable
fun ListItem(item:WeatherModel) {
    val romulFontFamily = FontFamily(Font(R.font.sf_pro_display_light))
    Card(
        modifier = Modifier
            .fillMaxWidth().padding(top=10.dp),
        colors = CardDefaults.cardColors(containerColor = Main),
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
                    text = item.time, color = Color.White,
                    style = TextStyle(fontSize = 14.sp, fontFamily = romulFontFamily)
                )
                Text(text = item.condition, color = Color.White,
                    style = TextStyle(fontSize = 14.sp, fontFamily = romulFontFamily))
            }
            Text(
                text = item.currentTemp.ifEmpty { "${item.maxTemp} °C /${item.minTemp} °C" },
                color = Color.White,
                style = TextStyle(fontSize = 25.sp, fontFamily = romulFontFamily)
            )
            AsyncImage(
                model = "https:${item.icon}",
                contentDescription = "image2",
                modifier = Modifier
                    .size(30.dp)
                    .padding(top = 10.dp, end = 5.dp)
            )
        }

    }
}

