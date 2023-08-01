package com.tianyouWang.wanDu.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tianyouWang.wanDu.R

abstract class NewsViewHolder : RecyclerView.ViewHolder {
    var type:Int

    var view:View

    constructor(view: View,type:Int) :super(view){
        this.type = type
        this.view = view

    }

}