package com.tianyouWang.wanDu.bean

/**
 * 列表每一项的数据
 *
 * @author: doudou
 * @since: 2023/7/19
 */
sealed class NewsItemBean;

data class NewsItemBeanWithAuther(
    /*
    标题
     */
    val title: String?,

    /*
    作者
     */
    val auther: String?,

    /*
    新闻内容
     */
    val content:String?
): NewsItemBean();

data class NewsItemBeanWithImage(
    /*
    标题
     */
    var title:String?,

    /*
    插图
     */
    val imageUrl:Int,

    /*
    新闻内容
     */
    var content:String?
): NewsItemBean();