package com.tianyouWang.wanDu.bean

import com.tianyouWang.wanDu.enum.WeatherType

data class WeatherItemBean (
    /*
    时间
     */
    val time: String?,

    /*
    天气种类
    */
    val weatherType: WeatherType?
)