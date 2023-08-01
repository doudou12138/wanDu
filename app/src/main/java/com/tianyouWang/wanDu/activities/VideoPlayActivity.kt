package com.tianyouWang.wanDu.activities

import android.media.MediaPlayer
import android.os.Bundle
import android.text.TextUtils
import android.view.Surface
import android.view.TextureView
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.tianyouWang.wanDu.R
import java.io.IOException

class VideoPlayActivity : AppCompatActivity(), View.OnClickListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
    private lateinit var textureView: TextureView
    private lateinit var btn: Button
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video_play)

        textureView = findViewById(R.id.textureViewVideo)
        btn = findViewById(R.id.btnStartPlay)

        btn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val text = btn.text
        if (TextUtils.equals(text, "开始")) {
            btn.text = "结束"

            mediaPlayer = MediaPlayer().apply {
                setOnPreparedListener(this@VideoPlayActivity)
                setOnCompletionListener(this@VideoPlayActivity)
                try {
                    setDataSource("https://globalimg.sucai999.com/uploadfile/20211210/267440/132836000096069452.mp4")
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                setSurface(Surface(textureView.surfaceTexture))

                prepareAsync()
            }
        } else {
            btn.text = "开始"
            mediaPlayer?.apply {
                stop()
                release()
            }
        }
    }

    override fun onPrepared(mediaPlayer: MediaPlayer) {
        mediaPlayer.start()
    }

    override fun onCompletion(mediaPlayer: MediaPlayer) {
        btn.text = "开始"
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}
