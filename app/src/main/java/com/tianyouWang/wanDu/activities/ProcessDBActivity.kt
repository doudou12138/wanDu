package com.tianyouWang.wanDu.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tianyouWang.wanDu.R
import util.MySQLiteHelper

class ProcessDBActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.process_db)

        addToHomeBtn()
        addClearBtn()
        addSearchByAccBtn()
        showNum()
        setUserInfoVisibility(View.INVISIBLE)
    }

    private fun addToHomeBtn(){
        val toHomeBtn = findViewById<Button>(R.id.fromProDataToHomeBtn)
        toHomeBtn.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addClearBtn(){
        val clearBtn = findViewById<Button>(R.id.clear_db_btn)
        clearBtn.setOnClickListener {
            var mySQLiteHelper = MySQLiteHelper(this)
            mySQLiteHelper.clearUsers()
            showNum()
            setUserInfoVisibility(View.INVISIBLE)
        }
    }

    private fun addSearchByAccBtn(){
        val findByAccBtn = findViewById<Button>(R.id.search_by_acc_btn)
        findByAccBtn.setOnClickListener {
            val input= findViewById<EditText>(R.id.acc_search_input)

            var mySQLiteHelper = MySQLiteHelper(this)
            val user = mySQLiteHelper.queryByAccount_num(input.text.toString())
            if(user==null){
                Toast.makeText(
                    this,
                    "无相应用户",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                setUserInfoVisibility(View.VISIBLE)
            }

        }
    }

    private fun showNum(){
        val record_num = findViewById<TextView>(R.id.accou_num)
        var mySQLiteHelper = MySQLiteHelper(this)
        record_num.text = mySQLiteHelper.getNum().toString()
    }

    private fun setUserInfoVisibility(state:Int){
        var views :List<View> = arrayListOf(
            findViewById<TextView>(R.id.detail_info_head_image_title),
            findViewById<ImageView>(R.id.detail_info_head_image),
            findViewById(R.id.detail_info_username_title),
            findViewById(R.id.detail_info_username),
            findViewById(R.id.detail_info_acc_num_title),
            findViewById(R.id.detail_info_acc_num),
            findViewById(R.id.detail_info_age_title),
            findViewById(R.id.detail_info_age),
            findViewById(R.id.detail_info_email_title),
            findViewById(R.id.detail_info_email),
            findViewById(R.id.detail_info_self_intro_title),
            findViewById(R.id.detail_info_self_introduction)
        )

        for(view:View in views){
            view.visibility=state
        }
    }
}