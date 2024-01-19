package com.example.weatherappcompose.screens.widget

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.color.ColorProvider
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.ContentScale
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.FontFamily
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.example.weatherappcompose.R

class SimpleWidget : GlanceAppWidget() {


    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            MaterialTheme {
                ContentView()
            }
            // create your AppWidget here

        }
    }


    @Composable
    private fun ContentView() {
        Box(
            modifier = GlanceModifier.fillMaxSize()
                .background(imageProvider = ImageProvider(R.drawable.photo1705577972_1_1_9))
                .cornerRadius(16.dp)
        ) {

            Column(

                modifier = GlanceModifier
                    .fillMaxSize()
                    // .background(imageProvider = ImageProvider(R.drawable.widget))
                    .cornerRadius(16.dp),


                horizontalAlignment = Alignment.CenterHorizontally,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(
                    modifier = GlanceModifier
                        .width(10.dp)
                )

                Text(
                    text = "16.01.2024",
                    style = TextStyle(
                        fontFamily = FontFamily.Serif,
                        color = ColorProvider(day = Color.White, night = Color.White),
                        fontSize = 16.sp
                    )
                )
                /*  Spacer(
                      modifier = GlanceModifier
                          .width(10.dp)
                  )*/
                Spacer(
                    modifier = GlanceModifier
                        .height(10.dp)
                )

                Image(
                    provider = ImageProvider(R.drawable.free_icon_2),
                    //  painter = painterResource(id = R.drawable.free_icon),
                    contentDescription = null,
                    // contentScale = ContentScale.Crop,
                    modifier = GlanceModifier
                        .size(40.dp)
                        .cornerRadius(30.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(
                    modifier = GlanceModifier
                        .height(10.dp)
                )
                Text(
                    text = "-3°С",
                    style = TextStyle(
                        fontFamily = FontFamily.Serif,
                        color = ColorProvider(day = Color.White, night = Color.White),
                        fontSize = 24.sp
                    )
                )
                Text(
                    text = "Light snow",
                    style = TextStyle(
                        fontFamily = FontFamily.Serif,
                        color = ColorProvider(day = Color.White, night = Color.White),
                        fontSize = 20.sp
                    )
                )
                Spacer(
                    modifier = GlanceModifier
                        .height(10.dp)
                )
                Row(

                ) {


                    Text(
                        text = "Moscow",
                        style = TextStyle(
                            fontFamily = FontFamily.Serif,
                            color = ColorProvider(day = Color.White, night = Color.White),
                            fontSize = 16.sp
                        )
                    )
                    Spacer(
                        modifier = GlanceModifier
                            .width(50.dp)
                    )

                    Image(
                        provider = ImageProvider(R.drawable.cloud_sync_icon),
                        //  painter = painterResource(id = R.drawable.free_icon),
                        contentDescription = null,
                        // contentScale = ContentScale.Crop,
                        modifier = GlanceModifier
                            .size(30.dp)
                            .cornerRadius(30.dp),
                        contentScale = ContentScale.Crop
                    )

                }


            }

        }
    }


}