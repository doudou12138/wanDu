package com.tianyouWang.wanDu.activities

import UserManager
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tianyouWang.wanDu.R
import util.MySQLiteHelper
import java.lang.Exception
import kotlin.math.log

class DetailInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_user_info)

        UserManager.initialize(this)

        addEditBtn()
        addBackBtn()
        addExitBtn()
    }

    override fun onResume() {
        super.onResume()
        showAcc_num()
        showInfo()
    }

    private fun addEditBtn(){
        val editBtn = findViewById<Button>(R.id.userinfo_edit_btn)
        editBtn.setOnClickListener {
            var infos = listOf<EditText>(
                findViewById(R.id.detail_info_username),
                findViewById(R.id.detail_info_age),
                findViewById(R.id.detail_info_email),
                findViewById(R.id.detail_info_self_introduction)
            )
            if(editBtn.text.toString()=="编辑"){
                for(view:EditText in infos){
                    view.isEnabled=true
                }

                //编辑键消失
                editBtn.text = "完成"
            }else if(editBtn.text.toString()=="完成"){
                for(view:EditText in infos){
                    view.isEnabled=false
                }
                UserManager.savaUser(UserManager.getAccountNum(),infos[0].text.toString(),infos[3].text.toString(),null)
                MySQLiteHelper(this).updateUserInfo(UserManager.getAccountNum(),infos[0].text.toString(),infos[1].text.toString().toInt(),infos[2].text.toString(),infos[3].text.toString())
                editBtn.text = "编辑"
            }

        }
    }

    private fun showInfo(){
        if(!UserManager.isLoggedIn()){
            Toast.makeText(
                this,
                "未登录",
                Toast.LENGTH_SHORT
            ).show()
            throw Exception()
        }
        val head_image = findViewById<ImageView>(R.id.detail_info_head_image)
        head_image.setImageResource(R.drawable.head_image)

        val infos = listOf<EditText>(
            findViewById(R.id.detail_info_username),
            findViewById(R.id.detail_info_age),
            findViewById(R.id.detail_info_email),
            findViewById(R.id.detail_info_self_introduction)
        )

        val user = MySQLiteHelper(this).queryByAccount_num(UserManager.getAccountNum())

        infos[0].text = Editable.Factory.getInstance().newEditable(user?.getUserName())
        infos[1].text = Editable.Factory.getInstance().newEditable(user?.getAge().toString())
        infos[2].text = Editable.Factory.getInstance().newEditable(user?.getEmail()?:"")
        infos[3].text = Editable.Factory.getInstance().newEditable(user?.getSelfDescr()?:"")
    }

    //为从个人详细页面跳转到MainPage添加逻辑
    private fun addBackBtn(){
        val backBtn = findViewById<TextView>(R.id.from_userinfo_to_home)
        backBtn.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }

    private fun addExitBtn(){
        val exitLog = findViewById<TextView>(R.id.exitLog)
        exitLog.setOnClickListener {
            UserManager.clearUser()
            val intent = Intent(this,MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

    }

    //显示account_num的数据
    private fun showAcc_num(){
        val account_num = findViewById<TextView>(R.id.detail_info_acc_num)
        println( UserManager.getAccountNum())
        account_num.text= UserManager.getAccountNum()
    }
}