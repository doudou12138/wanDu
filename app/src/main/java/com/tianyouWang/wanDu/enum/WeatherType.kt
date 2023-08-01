package com.tianyouWang.wanDu.enum

import com.tianyouWang.wanDu.R

enum class WeatherType {
    SUNNY,
    RAINY,
    CLOUDY,
    SNOW;

    fun getIconResId(): Int {
        return when (this) {
            SUNNY -> R.drawable.sunny_icon
            RAINY -> R.drawable.rainy_icon
            CLOUDY -> R.drawable.cloudy_icon
            SNOW -> R.drawable.snowy_icon
        }
    }

    fun getBackResId(): Int{
        return when(this){
            SUNNY -> R.drawable.sunny_back
            RAINY -> R.drawable.rainy_back
            CLOUDY -> R.drawable.cloudy_back
            SNOW -> R.drawable.snowy_back
        }
    }
}
