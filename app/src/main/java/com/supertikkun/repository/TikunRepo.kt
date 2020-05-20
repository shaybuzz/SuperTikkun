package com.supertikkun.repository

import android.net.Uri
import androidx.lifecycle.LiveData
import com.supertikkun.local.entities.TextChunksData
import com.supertikkun.local.entities.TextRawData

interface TikunRepo {
    val textSimple:LiveData<String>
    val textWithTaam:LiveData<String>

    val audioUrl:LiveData<Uri?>
    val videoUrl:LiveData<Uri?>
    val textChunksData:LiveData<TextChunksData?>


    val textRawData:LiveData<TextRawData?>

    suspend fun pullRawTextData(tikunId:String)
    suspend fun pullChunksVideoData(tikunId:String)
}