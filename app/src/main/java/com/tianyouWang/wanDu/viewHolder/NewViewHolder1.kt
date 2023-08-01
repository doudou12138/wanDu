package com.tianyouWang.wanDu.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tianyouWang.wanDu.R
import com.tianyouWang.wanDu.bean.NewsItemBeanWithAuther

class NewsViewHolder1 : ViewHolder {
    var tvTitle: TextView
        private set

    var tvAuther: TextView
        private set

    constructor(view: View) :super(view){
        tvTitle=view.findViewById(R.id.tvTitle)
        tvAuther=view.findViewById(R.id.tvAuther)

    }

    fun bindData(newsItemBean: NewsItemBeanWithAuther){
        tvTitle.text = newsItemBean.title;
        tvAuther.text = newsItemBean.auther
    }

}