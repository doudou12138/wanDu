import android.content.Context
import android.content.SharedPreferences
import com.tianyouWang.wanDu.model.User
import util.MySQLiteHelper

object UserManager {

    private const val PREF_NAME = "MyPrefs"
    private const val ACCOUNT_KEY = "account_num"
    private const val IS_LOGIN_KEY = "is_login"
    private const val USERNAME_KEY = "user_name"
    private const val SELF_DESCRIPTION_KEY = "self_description"
    private const val HEAD_IMAGE_KEY = "head_image"

    private lateinit var sharedPreferences: SharedPreferences

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    //判断现在是否有用户登录
    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(IS_LOGIN_KEY, false)
    }

    //保存现在登陆中的用户的信息于本机
    fun savaUser(account_num: String, user_name:String, self_desc:String, head_image: ByteArray?){
        var edit = sharedPreferences.edit()
        edit.putBoolean(IS_LOGIN_KEY,true)
        edit.putString(ACCOUNT_KEY,account_num)
        edit.putString(USERNAME_KEY,user_name)
        edit.putString(SELF_DESCRIPTION_KEY,self_desc)
        edit.apply()
    }

    //删除现在登陆的用户信息
    fun clearUser(){
        val edit = sharedPreferences.edit()
        edit.putBoolean(IS_LOGIN_KEY,false)
        edit.putString(ACCOUNT_KEY,"")
        edit.putString(USERNAME_KEY,"")
        edit.putString(SELF_DESCRIPTION_KEY,"")
        edit.apply()
    }


    fun getAccountNum() :String {
        return sharedPreferences.getString(ACCOUNT_KEY,"")?:""
    }

    fun getUserName():String{
        return sharedPreferences.getString(USERNAME_KEY,"")?:""
    }

    fun getSelfDescription(): String {
        return sharedPreferences.getString(SELF_DESCRIPTION_KEY,"")?:""
    }

    // Add methods for login, logout, and other user-related operations
}
