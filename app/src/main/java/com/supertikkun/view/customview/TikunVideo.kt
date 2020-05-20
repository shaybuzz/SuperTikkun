package com.supertikkun.view.customview

import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import android.util.AttributeSet
import android.widget.MediaController
import android.widget.VideoView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TikunVideo @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : VideoView(context, attrs, defStyleAttr) {

    private val PositionEmitDelay = 100L
    private val ShowControlTime = 2000
    private var _currentPositionVal: MutableLiveData<Int>? = null
    private var vidMediaController: MediaController
    private val positionRunnable: Runnable = object : Runnable {
        override fun run() {
            _currentPositionVal?.value = currentPosition
            handler.postDelayed(this, PositionEmitDelay)
        }
    }

    init {
        vidMediaController = MediaController(context)
        vidMediaController.setPrevNextListeners(null, null)//not working //TODO

        setOnCompletionListener {
            removePositionListener()
        }

        setOnPreparedListener {
            it.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING)
            it.setOnVideoSizeChangedListener(object : MediaPlayer.OnVideoSizeChangedListener {
                override fun onVideoSizeChanged(mp: MediaPlayer?, width: Int, height: Int) {
                    setMediaController(vidMediaController)
                    vidMediaController.setBackgroundColor(Color.WHITE)
                    vidMediaController.setAnchorView(this@TikunVideo)
                    vidMediaController.setPrevNextListeners(null, null)//not working //TODO
                    vidMediaController.show(ShowControlTime)
                }
            })
            start()
        }
    }

    fun setPositionListener(positionListener: MutableLiveData<Int>) {
        _currentPositionVal = positionListener
    }

    override fun start() {
        super.start()
        addPositionListener()
    }

    override fun pause() {
        super.pause()
        removePositionListener()
    }

    override fun resume() {
        super.resume()
        addPositionListener()
    }

    override fun stopPlayback() {
        super.stopPlayback()
        removePositionListener()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        removePositionListener()
    }

    private fun removePositionListener() {
        handler.removeCallbacks(positionRunnable)
    }

    private fun addPositionListener() {
        handler.post(positionRunnable)
    }
}