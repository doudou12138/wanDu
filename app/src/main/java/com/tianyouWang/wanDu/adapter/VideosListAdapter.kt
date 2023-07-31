package com.tianyouWang.wanDu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tianyouWang.wanDu.R
import com.tianyouWang.wanDu.bean.VideoItemBean
import com.tianyouWang.wanDu.viewHolder.VideosViewHolder

class VideosListAdapter: RecyclerView.Adapter<VideosViewHolder>{
    private val videos_list : List<VideoItemBean>;

    constructor(video_list:List<VideoItemBean>){
        this.videos_list = video_list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.video_item,parent,false);
        return VideosViewHolder(view);
    }

    override fun onBindViewHolder(holder: VideosViewHolder, position: Int) {
        val itemBean = videos_list[position];

        itemBean.video_home_src.let{
            holder.ivVideoHome.setImageResource(it)
        }

        itemBean.like_num.let{
            holder.tvLike.text = it.toString()
        }

        itemBean.comments_list.let{
            holder.tvComment.text = it.size.toString()
        }

    }

    override fun getItemCount(): Int {
        return videos_list.size;
    }
}