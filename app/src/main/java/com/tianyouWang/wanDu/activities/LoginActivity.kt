package com.tianyouWang.wanDu.activities

import UserManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.tianyouWang.wanDu.R
import util.MySQLiteHelper

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)


        addClickToSigup()
        addLoginFunc()
    }

    //为“注册”加入点击跳转的功能
    private fun addClickToSigup(){
        val toSigup = findViewById<TextView>(R.id.toRegisterLink)
        toSigup.setOnClickListener {
            val intent = Intent(this,SigupActivity::class.java)
            startActivity(intent)
        }
    }

    //加入登录逻辑
    private fun addLoginFunc(){
        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            val accoun_input = findViewById<EditText>(R.id.acc_input)
            val pwd_input = findViewById<EditText>(R.id.pwd_input)
            //账号为空
            if(accoun_input.text.isNullOrEmpty()){
                Toast.makeText(
                    this,
                    "未输入账号",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            //密码为空
            if(pwd_input.text.isNullOrEmpty()){
                Toast.makeText(
                    this,
                    "未输入密码",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }


            var mySQLiteHelper = MySQLiteHelper(this)
            var user = mySQLiteHelper.queryByAccount_num(accoun_input.text.toString())
            if(user==null){
                Toast.makeText(
                    this,
                    "该账号不存在",
                    Toast.LENGTH_SHORT
                ).show()
            }else if (user.confirmPwd(pwd_input.text.toString())) {
                Toast.makeText(
                    this,
                    "密码正确。登陆成功",
                    Toast.LENGTH_SHORT
                ).show()
                UserManager.savaUser(user.getAccountNum(),user.getUserName(),user.getSelfDescr()?:"",null)

                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(
                    this,
                    "密码错误",
                    Toast.LENGTH_SHORT
                ).show()
            }
            mySQLiteHelper.prtUsers()
        }
    }
}