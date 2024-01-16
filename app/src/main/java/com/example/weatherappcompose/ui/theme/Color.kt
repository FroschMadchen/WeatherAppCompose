package com.example.weatherappcompose.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val Main = Color(0x60E7ADA3)
val Red = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

sealed class ThemeColors(
    val background:Color,
    val surface:Color,
    val primary:Color,
    val text:Color
) {
    object Gentle:ThemeColors(
        background = Color(0xFFF1ADAD),
        surface =  Color(0x54F19191),
        primary =  Color(0x60E7ADA3),
        text =  Color(0xFFFFFFFF),
    )

    object Gloomy:ThemeColors(
        background =  Color(0xFF000000),
        surface = Color(0xC97B7CA5),
        primary = Color(0x619EA9C7),
        text =  Color(0xFF2E3177),
    )
}
