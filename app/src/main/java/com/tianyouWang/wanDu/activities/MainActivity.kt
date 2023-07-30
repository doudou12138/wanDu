package com.tianyouWang.wanDu.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tianyouWang.wanDu.R
import com.tianyouWang.wanDu.adapter.NewsAdapter
import com.tianyouWang.wanDu.bean.NewsItemBean
import com.tianyouWang.wanDu.bean.NewsItemBeanWithAuther
import com.tianyouWang.wanDu.bean.NewsItemBeanWithImage
import com.tianyouWang.wanDu.fragments.Fragment1
import com.tianyouWang.wanDu.fragments.Fragment2
import com.tianyouWang.wanDu.fragments.Fragment3
import util.LoadingUtils
import kotlin.math.log


class MainActivity : FragmentActivity(){

    /** RecycleView 实例 */
    private var newsRecycleView: RecyclerView? = null

    /** RecycleView 的适配器 */
    private var newsAdapter: NewsAdapter? = null

    /** 进行fragments之间跳转的管理 **/
    private var fragmentTransaction: FragmentTransaction? = null

    /** 记录现在处于哪个fragment **/
    private var now_page: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tabs_demo)
        //val weatherPart = findViewById<LinearLayout>(R.id.weather)
//        weatherPart.setOnClickListener {
//            // 在这里处理点击事件
//            // 执行跳转到指定页面的操作
//            LoadingUtils.startWithLoading(this,WeatherActivity::class.java)
//
//        }

//        var wanDuOnce = findViewById<TextView>(R.id.searchNow)
//
//        wanDuOnce.setOnClickListener{
//            Toast.makeText(this,
//                "正在努力检索了，请小主稍等", Toast.LENGTH_SHORT).show()
//        }

//        var newsClick = findViewById<LinearLayout>(R.id.news_list0)
//        newsClick.setOnClickListener{
//            Toast.makeText(this,"正在为小主加载文章",Toast.LENGTH_SHORT).show()
//        }

//        setContent {
//            WanDuTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }
        //addRecycleView();
        //weather_alpha();
        addFragment()
        addFragmentsSwitch()
    }

    private fun addRecycleView(){
        newsRecycleView = findViewById(R.id.news_list0);
        newsAdapter = NewsAdapter(createNewsData());//创建了假数据
        newsRecycleView?.adapter = newsAdapter
        newsRecycleView?.layoutManager = LinearLayoutManager(this)
    }

    private fun addFragment() {
        val fragment1: Fragment = Fragment1()

        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction!!.add(R.id.content, fragment1)
        fragmentTransaction!!.commit()
        now_page=0;
    }

    private fun setSelectPage(page_no :Int){
        if(now_page==page_no){

        }else{
            now_page = page_no
            val fragmentManager : FragmentManager = supportFragmentManager
            fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction?.replace(R.id.content,getFragmentByPageNo(page_no))?.commit()

        }
    }

    private fun addFragmentsSwitch(){
        val homePageButton = findViewById<TextView>(R.id.homePageButton)
        homePageButton.setOnClickListener {
            setSelectPage(0)
        }
        val video = findViewById<TextView>(R.id.videoPageButton)
        video.setOnClickListener{
            setSelectPage(1)
        }

        val myPageText = findViewById<TextView>(R.id.myPageButton)
        myPageText.setOnClickListener{
            setSelectPage(2)

        }
    }
    /*
    启动天气的透明度动画
     */
    private fun weather_alpha(){
        var li_weather = findViewById<LinearLayout>(R.id.weather)  //找到组件
        val alpha = AnimationUtils.loadAnimation(this, R.anim.alpha_anim)   //找到动画

        li_weather.startAnimation(alpha)
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

    private fun getFragmentByPageNo(page_no: Int): Fragment {
        return when (page_no) {
            0 -> Fragment1()
            1 -> Fragment2()
            2 -> Fragment3()
            else -> throw IllegalArgumentException("Invalid page number: $page_no")
        }
    }

}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    //生成一个Text,文本内容是"Hello "+参数name，颜色为Pink80
//    Text(
//        text = "Hello $name!",
//        modifier = modifier,
//        color = Pink80
//    )
//
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    WanDuTheme {
//        Greeting("Android")
//    }
//}