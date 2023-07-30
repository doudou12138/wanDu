package com.tianyouWang.wanDu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tianyouWang.wanDu.R
import com.tianyouWang.wanDu.bean.WeatherItemBean
import com.tianyouWang.wanDu.viewHolder.WeatherViewHolder

class WeatherAdapter : RecyclerView.Adapter<WeatherViewHolder>{
    private val weatherList: List<WeatherItemBean>;

    constructor(weatherList:List<WeatherItemBean>){
        this.weatherList = weatherList;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.weather_item,parent,false);
        return WeatherViewHolder(view);
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val itemBean = weatherList[position];

        itemBean.time?.let {
            holder.tvTime.text = it;
        }

        itemBean.weatherType?.let{
            holder.ivIcon.setImageResource(it.getIconResId());
        }
    }

    override fun getItemCount(): Int {
        return weatherList.size;
    }

}
