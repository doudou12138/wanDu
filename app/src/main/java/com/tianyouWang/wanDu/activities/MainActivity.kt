package com.tianyouWang.wanDu.activities

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import com.tianyouWang.wanDu.R
import com.tianyouWang.wanDu.fragments.Fragment1
import com.tianyouWang.wanDu.fragments.Fragment2
import com.tianyouWang.wanDu.fragments.Fragment3


class MainActivity : FragmentActivity(){


    /** 进行fragments之间跳转的管理 **/
    private var fragmentTransaction: FragmentTransaction? = null

    /** 记录现在处于哪个fragment **/
    private var now_page: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        addFragment()
        addFragmentsSwitch()
    }


    /**
     * 为mainActivity加入fragments
     */
    private fun addFragment() {
        val fragment1: Fragment = Fragment1()

        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction!!.add(R.id.content, fragment1)
        fragmentTransaction!!.commit()

        val home_button = findViewById<TextView>(R.id.homePageButton)
        home_button.setCompoundDrawablesWithIntrinsicBounds(
            0,
            R.drawable.home_icon_48_blue,
            0,
            0
        )
    }

    /*
    添加fragment间的切换功能，调用setSelectPage完成跳转
     */
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
    根据传进来的页面号设置选择要跳转的页面，完成fragment的跳转
     */
    private fun setSelectPage(page_no :Int){
        if(now_page==page_no){

        }else{
            val selected_button = findViewById<TextView>(getIdByNo(now_page))
            selected_button.setCompoundDrawablesRelativeWithIntrinsicBounds(
                0,
                getOriginalImage(now_page),
                0,
                0
            )

            now_page = page_no
            val to_select_button = findViewById<TextView>(getIdByNo(now_page))
            to_select_button.setCompoundDrawablesWithIntrinsicBounds(
                0,
                getWantedImage(now_page),
                0,
                0
            )

            val fragmentManager : FragmentManager = supportFragmentManager
            fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction?.replace(R.id.content,getFragmentByPageNo(page_no))?.commit()
        }
    }

    //根据page_no获得对应的fragment
    private fun getFragmentByPageNo(page_no: Int): Fragment {
        return when (page_no) {
            0 -> Fragment1()
            1 -> Fragment2()
            2 -> Fragment3()
            else -> throw IllegalArgumentException("Invalid page number: $page_no")
        }
    }

    //根据page_no获得tabs中的对应的button
    private fun getIdByNo(page_no: Int): Int{
        return when(page_no){
            0 -> R.id.homePageButton
            1 -> R.id.videoPageButton
            2 -> R.id.myPageButton
            else -> throw IllegalArgumentException("Invalid page number: $page_no")
        }
    }

    //根据page_no获得该fragment未进入状态下button的资源
    private fun getOriginalImage(page_no: Int):Int{
        return when(page_no){
            0 -> R.drawable.home_icon_48_black
            1 -> R.drawable.video_icon_48_black
            2 -> R.drawable.man_icon_48_black
            else -> throw IllegalArgumentException("Invalid page number: $page_no")
        }
    }

    //根据page_no获得对应fragment进入状态下button的资源
    private fun getWantedImage(page_no: Int):Int{
        return when(page_no){
            0 -> R.drawable.home_icon_48_blue
            1 -> R.drawable.video_icon_48_blue
            2 -> R.drawable.man_icon_48_blue
            else -> throw IllegalArgumentException("Invalid page number: $page_no")
        }
    }

}
