package com.tianyouWang.wanDu.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tianyouWang.wanDu.R
import com.tianyouWang.wanDu.bean.NewsItemBeanWithImage

class NewsViewHolder2 : ViewHolder {
    var tvTitle: TextView
        private set

    var ivImage: ImageView
        private set

    constructor(view: View) :super(view){
        tvTitle=view.findViewById(R.id.tvTitle)
        ivImage=view.findViewById(R.id.ivImage)
    }

    fun bindData(newsItemBeanWithImage: NewsItemBeanWithImage){
        tvTitle.text = newsItemBeanWithImage.title
        ivImage.setImageResource(newsItemBeanWithImage.imageUrl)
    }
}