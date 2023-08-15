package com.tianyouWang.wanDu.model


class User(private val account_num:String,private var userName:String,private var pwd:String) {
    private var age: Int = 0
    private var email: String? = null
    private var self_introduction:String? = null
    private var head_image:ByteArray?=null

    constructor(account_num:String,userName:String,pwd:String,age:Int,email:String?,self_introduction:String?,head_image:ByteArray?):this(account_num,userName,pwd){
        this.age = age
        this.email = email
        this.self_introduction = self_introduction
        this.head_image = head_image

    }

    fun getAccountNum(): String {
        return account_num
    }

    fun getUserName(): String {
        return userName
    }

    fun getPassword(): String {
        return pwd
    }

    fun getAge(): Int {
        return age
    }

    fun getEmail(): String? {
        return email
    }

    fun getSelfDescr():String?{
        return self_introduction
    }

    fun getHeadImage():ByteArray?{
        return head_image
    }

    fun greet(): String {
        return "Hello, I'm $userName!"
    }

    fun updateEmail(newEmail: String) {
        email = newEmail
    }

    //验证密码是否正确
    fun confirmPwd(pwd_a: String):Boolean{
        return pwd_a.equals(pwd)
    }

    override fun toString(): String {
        return "User(account=$account_num,password = ??, username=$userName, email=$email, age=$age, self_introduction=$self_introduction)"
    }

}
