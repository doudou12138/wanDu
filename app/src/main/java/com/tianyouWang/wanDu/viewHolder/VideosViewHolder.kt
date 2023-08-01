package com.tianyouWang.wanDu.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tianyouWang.wanDu.R

class VideosViewHolder :ViewHolder {
    var ivVideoHome: ImageView
        private set

    var tvLike: TextView
        private set

    var tvComment: TextView
        private set

    constructor(view: View) :super(view){
        ivVideoHome = view.findViewById(R.id.ivVideo_home)
        tvLike = view.findViewById(R.id.tvLike)
        tvComment = view.findViewById(R.id.tvComment)
    }
}