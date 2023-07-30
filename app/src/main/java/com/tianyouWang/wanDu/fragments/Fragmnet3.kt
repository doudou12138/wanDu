package com.tianyouWang.wanDu.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tianyouWang.wanDu.R
import com.tianyouWang.wanDu.adapter.NewsAdapter
import com.tianyouWang.wanDu.bean.NewsItemBean
import com.tianyouWang.wanDu.bean.NewsItemBeanWithAuther
import com.tianyouWang.wanDu.bean.NewsItemBeanWithImage

class Fragment3: Fragment() {
    private var view: View? = null

    private var myWorksRecycleView:RecyclerView? = null
    private var myWorksAdapter: NewsAdapter? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment3, container, false)
        addRecycleView()
        return view
    }

    // 可以在此处对视图进行初始化等操作
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 在这里可以进行对视图组件的操作和事件绑定
    }

    private fun addRecycleView(){
        myWorksRecycleView = view?.findViewById(R.id.myWorksList);
        myWorksAdapter = NewsAdapter(createNewsData());//创建了假数据
        myWorksRecycleView?.adapter = myWorksAdapter
        myWorksRecycleView?.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun createNewsData():List<NewsItemBean>{
        val result = ArrayList<NewsItemBean>()

        val first_item_auther = NewsItemBeanWithAuther("玩原神以来最激动的一次抽奖","阿右")
        val second_item_auther = NewsItemBeanWithAuther("晶核今日全面开放内测","朝夕光年")

        val first_item_image = NewsItemBeanWithImage("原神启动",R.drawable.yuan)
        val second_item_image = NewsItemBeanWithImage("yuanshen,qidong!",R.drawable.role)

        result.add(first_item_auther)
        result.add(second_item_auther)
        result.add(first_item_image)
        result.add(second_item_image)
        result.add(first_item_image)
        result.add(first_item_auther)
        result.add(first_item_image)
        result.add(first_item_auther)

        return result
    }
}