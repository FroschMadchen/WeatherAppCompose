package com.example.weatherappcompose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherappcompose.R
import com.example.weatherappcompose.ui.theme.Main
import com.example.weatherappcompose.ui.theme.Red


@Preview(showBackground = true)
@Composable
fun MainScreen() {
    val romulFontFamily = FontFamily(Font(R.font.sf_pro_display_light))
    Image(
        painter = painterResource(id = R.drawable.image_1),
        contentDescription = "image",
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.8f),
        contentScale = ContentScale.Crop
    ) // растягивание изображения
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Main),
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
                        text = "23 Jun 2023 13:00",
                        style = TextStyle(fontSize = 15.sp, fontFamily = romulFontFamily),
                        color = Color.White
                    )
                    AsyncImage(
                        model = "https://cdn.weatherapi.com/weather/64x64/day/122.png",
                        contentDescription = "image2",
                        modifier = Modifier
                            .size(50.dp)
                            .padding(top = 10.dp, end = 5.dp)
                    )
                }
                Text(
                    text = "Moscow",
                    style = TextStyle(fontSize = 24.sp,fontFamily = romulFontFamily),
                    color = Color.White
                )
                Text(
                    text = "4℃",
                    style = TextStyle(fontSize = 64.sp,fontFamily = romulFontFamily),
                    color = Color.White
                )
                Text(
                    text = "Sunny",
                    style = TextStyle(fontSize = 64.sp,fontFamily = romulFontFamily),
                    color = Color.White
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_search_24),
                            contentDescription = "icon",
                            tint = Color.White
                        )
                    }
                    Text(text ="-13℃/4℃",
                        style = TextStyle(fontSize = 18.sp,fontFamily = romulFontFamily),
                        color = Color.White)

                    IconButton(
                        onClick = { /*TODO*/ }
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








