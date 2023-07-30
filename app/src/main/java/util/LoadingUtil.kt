package util

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar

object LoadingUtils {
    fun startWithLoading(activity: Activity, targetActivity: Class<*>) {
        // 显示全屏加载动画
        val loadingProgressBar = ProgressBar(activity)
        loadingProgressBar.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT,
            Gravity.CENTER
        )
        loadingProgressBar.visibility = View.VISIBLE
        (activity.window.decorView as ViewGroup).addView(loadingProgressBar)

        // 模拟加载动画显示一段时间（比如3秒）
        Handler().postDelayed({
            // 隐藏加载动画
            loadingProgressBar.visibility = View.GONE

            // 启动目标页面
            val intent = Intent(activity, targetActivity)
            activity.startActivity(intent)

            // 移除加载动画
            (activity.window.decorView as ViewGroup).removeView(loadingProgressBar)
        }, 3000) // 延迟3秒
    }
}
