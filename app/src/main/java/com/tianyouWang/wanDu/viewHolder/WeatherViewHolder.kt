package com.tianyouWang.wanDu.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tianyouWang.wanDu.R

class WeatherViewHolder:ViewHolder {
    var tvTime:TextView
        private set

    var ivIcon:ImageView
        private set

    constructor(view: View) :super(view){
        tvTime = view.findViewById(R.id.tvTime)
        ivIcon = view.findViewById(R.id.ivIcon)
    }
}