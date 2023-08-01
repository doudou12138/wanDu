package com.tianyouWang.wanDu.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tianyouWang.wanDu.R
import com.tianyouWang.wanDu.activities.WeatherActivity
import com.tianyouWang.wanDu.activities.WebviewDemoActivity
import com.tianyouWang.wanDu.adapter.NewsAdapter
import com.tianyouWang.wanDu.bean.NewsItemBean
import com.tianyouWang.wanDu.bean.NewsItemBeanWithAuther
import com.tianyouWang.wanDu.bean.NewsItemBeanWithImage
import util.LoadingUtils

class Fragment1: Fragment() {

    private var view:View? =null;
    /** RecycleView 实例 */
    private var newsRecycleView: RecyclerView? = null

    /** RecycleView 的适配器 */
    private var newsAdapter: NewsAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment1, container, false)
        addRecycleView();

        val weatherPart = view?.findViewById<LinearLayout>(R.id.weather)
        weatherPart?.setOnClickListener {
            // 在这里处理点击事件
            // 执行跳转到指定页面的操作
            LoadingUtils.startWithLoading(requireActivity(), WeatherActivity::class.java)

        }

        var wanDuOnce = view?.findViewById<TextView>(R.id.searchNow)

        wanDuOnce?.setOnClickListener{

            val editText = view?.findViewById<EditText>(R.id.editText)
            val searchQuery = editText?.text.toString().trim()
            if (searchQuery.isNotEmpty()) {
                Toast.makeText(requireContext(),
                    "正在努力检索了，请小主稍等",
                    Toast.LENGTH_SHORT)
                    .show()
                val intent = Intent(requireContext(), WebviewDemoActivity::class.java)
                intent.putExtra(WebviewDemoActivity.EXTRA_SEARCH_QUERY, searchQuery)
                startActivity(intent)
            }else{
                Toast.makeText(requireContext(),
                    "小主的输入为空哦",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }

        weather_alpha();

        return view
    }

    // 可以在此处对视图进行初始化等操作
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 在这里可以进行对视图组件的操作和事件绑定
    }

    private fun addRecycleView(){
        newsRecycleView = view?.findViewById(R.id.news_list0);
        newsAdapter = NewsAdapter(createNewsData());//创建了假数据
        newsRecycleView?.adapter = newsAdapter
        newsRecycleView?.layoutManager = LinearLayoutManager(requireContext())
    }

    /*
    启动天气的透明度动画
     */
    private fun weather_alpha(){
        var li_weather = view?.findViewById<LinearLayout>(R.id.weather)  //找到组件
        val alpha = AnimationUtils.loadAnimation(requireContext(), R.anim.alpha_anim)   //找到动画

        li_weather?.startAnimation(alpha)
    }

    /*
    用来给news_list造假数据的方法
     */
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