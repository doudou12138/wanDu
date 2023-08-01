package com.tianyouWang.wanDu.bean

data class VideoItemBean(
    /*
    视频封面资源
     */
    var video_home_src:Int,

    /*
    like number
     */
    var like_num:Int,

    /*
    comments list
     */
    var comments_list:List<CommentItemBean>
)