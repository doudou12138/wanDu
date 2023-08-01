package com.tianyouWang.wanDu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.compose.ui.Alignment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tianyouWang.wanDu.R
import com.tianyouWang.wanDu.adapter.WeatherAdapter
import com.tianyouWang.wanDu.bean.WeatherItemBean
import com.tianyouWang.wanDu.enum.WeatherType

class WeatherActivity :ComponentActivity() {
    private var weatherRecycleView: RecyclerView? = null
    private var weatherAdapter: WeatherAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        //为back按钮添加跳转回首页的逻辑
        val back = findViewById<Button>(R.id.back)
        back.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        addRecycleView();
    }

    //添加每小时天气的RecycleView
    private fun addRecycleView(){
        weatherRecycleView = findViewById(R.id.weather_list)
        weatherAdapter = WeatherAdapter(onCreateWeatherData())
        weatherRecycleView?.adapter = weatherAdapter
        weatherRecycleView?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

    }

    //创建每小时天气的假数据并返回
    private fun onCreateWeatherData() :List<WeatherItemBean>{
        val result = ArrayList<WeatherItemBean>();
        for( i in 11..20){
            val bean = WeatherItemBean(i.toString()+"时",WeatherType.SUNNY)
            result.add(bean)
        }

        return result
    }
}