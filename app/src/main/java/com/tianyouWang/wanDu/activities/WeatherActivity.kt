package com.tianyouWang.wanDu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.compose.ui.Alignment
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tianyouWang.wanDu.R
import com.tianyouWang.wanDu.adapter.WeatherAdapter
import com.tianyouWang.wanDu.bean.WeatherItemBean
import com.tianyouWang.wanDu.enum.WeatherType

class WeatherActivity :ComponentActivity() {
    private val weather_each_hour: List<WeatherType> = listOf(
        WeatherType.SUNNY,
        WeatherType.SUNNY,
        WeatherType.CLOUDY,
        WeatherType.CLOUDY,
        WeatherType.CLOUDY,
        WeatherType.CLOUDY,
        WeatherType.SUNNY,
        WeatherType.CLOUDY,
        WeatherType.RAINY,
        WeatherType.RAINY
    )

    private var weatherRecycleView: RecyclerView? = null
    private var weatherAdapter: WeatherAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val back = findViewById<Button>(R.id.back)
        back.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        addRecycleView();
        checkWarn()
    }

    //加入横向的小时天气RecycleView
    private fun addRecycleView(){
        weatherRecycleView = findViewById(R.id.weather_list)
        weatherAdapter = WeatherAdapter(onCreateWeatherData())
        weatherRecycleView?.adapter = weatherAdapter
        weatherRecycleView?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

    }

    //制造小时天气的假数据
    private fun onCreateWeatherData() :List<WeatherItemBean>{
        val result = ArrayList<WeatherItemBean>();
        for( i in 11..20){
            val bean = WeatherItemBean(i.toString()+"时",weather_each_hour.get(i-11))
            result.add(bean)
        }

        return result
    }

    /*
    检查是否有恶劣天气引发警告，从而调整页面中warn的可见性
     */
    private fun checkWarn(){
        val warn = findViewById<ConstraintLayout>(R.id.warn)
        val hasWarn = false
        if(hasWarn){
            //
            warn.visibility = View.VISIBLE
        }else{
            warn.visibility = View.INVISIBLE
        }
    }
}