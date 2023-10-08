package com.tianyouWang.wanDu.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tianyouWang.wanDu.bean.NewsItemBean
import com.tianyouWang.wanDu.R
import com.tianyouWang.wanDu.activities.ShowNewsDetailActivity
import com.tianyouWang.wanDu.bean.NewsItemBeanWithAuther
import com.tianyouWang.wanDu.bean.NewsItemBeanWithImage
import com.tianyouWang.wanDu.viewHolder.NewsViewHolder
import com.tianyouWang.wanDu.viewHolder.NewsViewHolder1
import com.tianyouWang.wanDu.viewHolder.NewsViewHolder2

class NewsAdapter :RecyclerView.Adapter<ViewHolder>{
    private val newsList: List<NewsItemBean>;
    private final val ITEM_TYPE1=1;
    private final val ITEM_TYPE2=2;
    private final val END_ITEM = 0;
    private final val itemType_pos = listOf(ITEM_TYPE1,ITEM_TYPE1,ITEM_TYPE2,ITEM_TYPE2,ITEM_TYPE1,ITEM_TYPE2);

    constructor(newsList:List<NewsItemBean>){
        this.newsList = newsList;
    }

    /*
    获得第i个位置的类型,这里是死数据,直接返回
     */
    override fun getItemViewType(position: Int): Int {
        return itemType_pos.get(position)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View
        return when (viewType) {
            ITEM_TYPE1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
                NewsViewHolder1(view)
            }
            ITEM_TYPE2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.news_item2, parent, false)
                NewsViewHolder2(view)
            }
            // 添加其他类型对应的 ViewHolder...
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsItem = newsList[position]

        when (holder) {
            is NewsViewHolder1 -> holder.bindData(newsItem as NewsItemBeanWithAuther) // 如果是 NewsViewHolder 类型，调用其 bindData 方法
            is NewsViewHolder2 -> holder.bindData(newsItem as NewsItemBeanWithImage) // 如果是 NewsViewHolder2 类型，调用其 bindData 方法
            // 添加其他类型对应的 bindData 调用...
        }

        holder.itemView.setOnClickListener{
            Toast.makeText(holder.itemView.context,
                "正在为小主加载文章",
                Toast.LENGTH_SHORT)
                .show()
            val intent = Intent(it.context,ShowNewsDetailActivity::class.java)
            when(newsItem){
                is NewsItemBeanWithImage -> {
                    intent.putExtra(ShowNewsDetailActivity.NEWS_TITLE,newsItem.title)
                    intent.putExtra(ShowNewsDetailActivity.NEWS_CONTENT,newsItem.content)
                }
                is NewsItemBeanWithAuther ->{
                    intent.putExtra(ShowNewsDetailActivity.NEWS_TITLE,newsItem.title)
                    intent.putExtra(ShowNewsDetailActivity.NEWS_CONTENT,newsItem.content)
                }
            }
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return newsList.size;
    }

}