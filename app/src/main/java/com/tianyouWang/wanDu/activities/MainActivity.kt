package com.tianyouWang.wanDu.activities

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tianyouWang.wanDu.R
import com.tianyouWang.wanDu.adapter.NewsAdapter
import com.tianyouWang.wanDu.bean.NewsItemBean
import com.tianyouWang.wanDu.bean.NewsItemBeanWithAuther
import com.tianyouWang.wanDu.bean.NewsItemBeanWithImage
import util.LoadingUtils


class MainActivity : ComponentActivity() {

    /** RecycleView 实例 */
    private var newsRecycleView: RecyclerView? = null

    /** RecycleView 的适配器 */
    private var newsAdapter: NewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val weatherPart = findViewById<LinearLayout>(R.id.weather)
        weatherPart.setOnClickListener {
            // 在这里处理点击事件
            // 执行跳转到指定页面的操作
            LoadingUtils.startWithLoading(this,WeatherActivity::class.java)

        }

        var wanDuOnce = findViewById<TextView>(R.id.searchNow)

        wanDuOnce.setOnClickListener{
            Toast.makeText(this,
                "正在努力检索了，请小主稍等", Toast.LENGTH_SHORT).show()
        }

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
        addRecycleView();
        weather_alpha();
    }

    private fun addRecycleView(){
        newsRecycleView = findViewById(R.id.news_list0);
        newsAdapter = NewsAdapter(createNewsData());//创建了假数据
        newsRecycleView?.adapter = newsAdapter
        newsRecycleView?.layoutManager = LinearLayoutManager(this)
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