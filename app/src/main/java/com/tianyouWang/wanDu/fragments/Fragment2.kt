package com.tianyouWang.wanDu.fragments

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tianyouWang.wanDu.R
import com.tianyouWang.wanDu.activities.VideoPlayActivity
import com.tianyouWang.wanDu.adapter.NewsAdapter
import com.tianyouWang.wanDu.adapter.VideosListAdapter
import com.tianyouWang.wanDu.bean.CommentItemBean
import com.tianyouWang.wanDu.bean.VideoItemBean

class Fragment2: Fragment() {

    private var view:View? = null

    private var videosRecycleView: RecyclerView? = null
    private var videosAdapter:VideosListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment2, container, false)
        addVideosRecycleView()
        return view
    }

    // 可以在此处对视图进行初始化等操作
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 在这里可以进行对视图组件的操作和事件绑定
    }

    private fun addVideosRecycleView(){
        videosRecycleView = view?.findViewById(R.id.videos_list);
        videosAdapter = VideosListAdapter(createVideosData());//创建了假数据
        videosRecycleView?.adapter = videosAdapter
        videosRecycleView?.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun createVideosData():List<VideoItemBean>{
        var result= ArrayList<VideoItemBean>();
        var videos_home = listOf<Int>(R.drawable.a,R.drawable.b,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.b,R.drawable.b,R.drawable.b)
        var like_nums = listOf<Int>(2,4,6,0,77,1837,2,0)
        var all_comments_list = listOf<CommentItemBean>(
            CommentItemBean("hah"),
            CommentItemBean("有意思"),
            CommentItemBean("什么玩意"),
            CommentItemBean("笑不活了")
        )

        val comments_list = listOf<List<CommentItemBean>>(
            listOf(all_comments_list.get(1),all_comments_list.get(3)),
            listOf(),
            listOf(),
            listOf(all_comments_list.get(0),all_comments_list.get(1),all_comments_list.get(2),all_comments_list.get(3)),
            listOf(),
            listOf(all_comments_list.get(2)),
            listOf(all_comments_list.get(2),all_comments_list.get(3)),
            listOf()
        )

        for( i in 0..7){
            result.add(VideoItemBean(videos_home.get(i), like_nums.get(i),comments_list.get(i)))
        }

        return result
    }
}