package com.tianyouWang.wanDu.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import com.tianyouWang.wanDu.R

class WebviewDemoActivity : AppCompatActivity() {
    private val base_add = "http://www.baidu.com/"
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview_demo)
        webView = findViewById(R.id.webView)

        addControllerfunc()
        setControllerColor()

        setupWebView()
        intent.getStringExtra(EXTRA_SEARCH_QUERY)?.let { baiduSearch(it) }
    }

    private fun setupWebView() {
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true
    }

    /*
    传入搜索内容，借助百度完成搜索，并加载目标页面
     */
    private fun baiduSearch(searchText:String){
        val webView = findViewById<WebView>(R.id.webView)
        webView.loadUrl(base_add+"s?wd=$searchText")
    }

    /*
    为webView的控制栏中的按钮添加前进、后退、关闭的功能
    */
    private fun addControllerfunc(){
        //为前进按钮添加功能
        val enterButton = findViewById<Button>(R.id.webEnter)
        enterButton.setOnClickListener {
            webView = findViewById(R.id.webView)
            if(webView.canGoForward()){
                webView.goForward()
            }
        }

        //为回退按钮添加功能
        val backButton = findViewById<Button>(R.id.webBack)
        backButton.setOnClickListener {
            webView = findViewById(R.id.webView)
            if(webView.canGoBack()){
                webView.goBack()
            }
        }

        //为关闭按钮添加功能
        val shutButton = findViewById<Button>(R.id.shutDown)
        shutButton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
    /*
    判断能否回退前进，从而对应的修改前进后退按钮的颜色；（提供用户反馈）
     */
    private fun setControllerColor(){
        val backButton = findViewById<Button>(R.id.webBack)
        if(webView.canGoBack()){
            backButton.setTextColor(Color.parseColor("#000000"))
        }else{
            backButton.setTextColor(Color.parseColor("#B0B0B0"))
        }

        val enterButton = findViewById<Button>(R.id.webEnter)
        if(webView.canGoForward()){
            enterButton.setTextColor(Color.parseColor("#000000"))
        }else{
            enterButton.setTextColor(Color.parseColor("#B0B0B0"))
        }
    }

    /*
    用于拿数据的键
     */
    companion object {
        const val EXTRA_SEARCH_QUERY = "search_query"
    }
}