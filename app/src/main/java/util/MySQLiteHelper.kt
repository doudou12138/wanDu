package util

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.tianyouWang.wanDu.model.User.User

class MySQLiteHelper(context:Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    override fun onCreate(db:SQLiteDatabase) {
        db.execSQL(INIT_SENTENCE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // 删除旧表
        db?.execSQL("DROP TABLE IF EXISTS User")

        // 创建新表（根据您的 INIT_SENTENCE）
        db?.execSQL(INIT_SENTENCE)

    }

    //传入用户类，在数据库中添加用户
    fun addUser(user: User) :Long{
        val values = ContentValues()
        values.put("account_num",user.getAccountNum())
        values.put("userName", user.getUserName())
        values.put("pwd", user.getPassword())
        values.put("age",user.getAge())
        values.put("email",user.getEmail())
        values.put("self_introduction",user.getSelfDescr())
        values.put("head_image",user.getHeadImage())

        val db = this.writableDatabase
        val success = db.insert("User", null, values)
        values.clear()
        db.close()
        return success
    }

    //根据账号查找用户，并返回User类型
    fun queryByAccount_num(account_num:String): User? {
        val query = "SELECT * FROM User WHERE account_num = ?"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, arrayOf(account_num))
        //注意判断查询结果是否为空不能使用 cursor==/!=null
        //可以使用moveToFirst()方法，当查询结果为空时该方法会返回false
        if (cursor.moveToFirst()) {
            val account_num_r = cursor.getString(cursor.getColumnIndexOrThrow("account_num"))
            val username_r = cursor.getString(cursor.getColumnIndexOrThrow("userName"))
            val pwd_r = cursor.getString(cursor.getColumnIndexOrThrow("pwd"))
            val age_r = cursor.getInt(cursor.getColumnIndexOrThrow("age"))
            val email_r = cursor.getString(cursor.getColumnIndexOrThrow("email"))
            val self_intro_r = cursor.getString(cursor.getColumnIndexOrThrow("self_introduction"))
            val head_image_r = cursor.getBlob(cursor.getColumnIndexOrThrow("head_image"))
            cursor.close()
            return User(account_num_r, username_r, pwd_r, age_r, email_r,self_intro_r,head_image_r)
        } else {
            return null
        }
    }

    //更新用户信息
    fun updateUserInfo(account_num: String,user_name:String,age:Int,email:String,self_description:String){
        println("user_name=$user_name,age = $age,email:$email,self_introduction:$self_description")
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("userName",user_name)
        values.put("age",age)
        values.put("email",email)
        values.put("self_introduction",self_description)
        val whereClause = "account_num = ?"
        val whereArgs = arrayOf(account_num)

        db.update("User", values, whereClause, whereArgs)
        prtUsers()
        values.clear()
        db.close()
    }

    fun prtUsers(){
        var stringBuilder = StringBuilder()
        val query = "SELECT * FROM User"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val accoun_num = cursor.getString(cursor.getColumnIndexOrThrow("account_num"))
                val username = cursor.getString(cursor.getColumnIndexOrThrow("userName"))
                val age = cursor.getInt(cursor.getColumnIndexOrThrow("age"))
                val email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
                val self_intro = cursor.getString(cursor.getColumnIndexOrThrow("self_introduction"))
                val head_image = cursor.getBlob(cursor.getColumnIndexOrThrow("head_image"))
                stringBuilder.append("(Id:$id, Account_num:$accoun_num, UserName:$username, Age:$age, Email:$email, self_introduction:$self_intro, head_image:waitForYou)\n")
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        println(stringBuilder.toString())
    }

    fun getNum():Int {
        val query = "SELECT COUNT(*) FROM User"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        var recordCount = 0

        if (cursor.moveToFirst()) {
            recordCount = cursor.getInt(0) // 获取第一列的值（记录数量）
        }

        cursor.close()
        db.close()
        return recordCount
    }

    fun clearUsers(){
        val db = this.writableDatabase
        db.delete("User", null,null) // 删除 User 表中的所有数据
        db.close()
    }

    companion object{
        const val DATABASE_NAME = "Wandu.db" //数据库名称
        const val DATABASE_VERSION = 2  //数据库版本
        //初始化时的SQL语句
        const val INIT_SENTENCE =
            "CREATE TABLE User ("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "account_num TEXT UNIQUE,"+
            "pwd TEXT NOT NULL,"+
           "userName TEXT NOT NULL,"+
            "age INTEGER DEFAULT 0," +
            "email TEXT," +
            "self_introduction TEXT," +
            "head_image BLOB" +
            ")";

    }

}