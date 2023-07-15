package com.tianyouWang.wanDu.activities

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.tianyouWang.wanDu.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val weatherPart = findViewById<LinearLayout>(R.id.weather)
        weatherPart.setOnClickListener {
            // 在这里处理点击事件
            // 执行跳转到指定页面的操作
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
        }

        var wanDuOnce = findViewById<TextView>(R.id.searchNow)
        wanDuOnce.setOnClickListener{
            Toast.makeText(this, "正在努力检索了，请小主稍等", Toast.LENGTH_SHORT).show()
        }

        var newsClick = findViewById<LinearLayout>(R.id.news_list)
        newsClick.setOnClickListener{
            Toast.makeText(this,"正在为小主加载文章",Toast.LENGTH_SHORT).show()
        }

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