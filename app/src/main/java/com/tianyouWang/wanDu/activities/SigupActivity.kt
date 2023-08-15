package com.tianyouWang.wanDu.activities

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.tianyouWang.wanDu.R
import com.tianyouWang.wanDu.model.User
import util.MySQLiteHelper

class SigupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sigup)

        addBackClick()
        addSigupClick()
    }

    private fun addBackClick(){
        val back = findViewById<TextView>(R.id.backToLogin)
        back.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }

    private fun addSigupClick() {
        val sigupBto = findViewById<Button>(R.id.sigupButton)
        sigupBto.setOnClickListener {
            val edit_list: List<EditText> = listOf(
                findViewById(R.id.acc_input),
                findViewById(R.id.pwd_input),
                findViewById(R.id.pwd_confirm_input)
            )
            for (editText: EditText in edit_list) {
                if (editText.text.isNullOrEmpty()) {
                    Toast.makeText(
                        this,
                        "为空",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
            }
            if(!edit_list.get(1).text.toString().equals(edit_list.get(2).text.toString())){
                Toast.makeText(
                    this,
                    "两次密码不一致",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            var user = User(
                edit_list.get(0).text.toString(),
                edit_list.get(0).text.toString(),
                edit_list.get(1).text.toString()
            )

            var mySQLiteHelper = MySQLiteHelper(this)
            if(mySQLiteHelper.addUser(user)==-1L){
                Toast.makeText(
                    this,
                    "注册失败",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                mySQLiteHelper.prtUsers()

                Toast.makeText(
                    this,
                    "注册成功！",
                    Toast.LENGTH_SHORT,
                ).show()
            }

        }
    }


}