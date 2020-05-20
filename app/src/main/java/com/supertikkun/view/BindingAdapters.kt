package com.supertikkun.view

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.supertikkun.view.customview.TikunVideo

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("positionListener")
    fun bindTick(video:TikunVideo?, tickListener:MutableLiveData<Int>){
        video?.let {
            video.setPositionListener(tickListener)
        }
    }
}