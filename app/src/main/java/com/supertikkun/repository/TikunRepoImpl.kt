package com.supertikkun.repository

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.supertikkun.local.TextRawDao
import com.supertikkun.local.TikunDataBase
import com.supertikkun.local.entities.*
import com.supertikkun.network.TikunService
import com.supertikkun.network.response.toTextChunksData
import timber.log.Timber
import java.lang.Exception

class TikunRepoImpl(
    private val db: TikunDataBase,
    private val tikunService: TikunService
) : TikunRepo {

    override val textRawData = db.tikunDao().getTextRawData()

    override val textChunksData  = Transformations.map(db.tikunChunksDao().getTextChunksData(), {
        it.firstOrNull()
    })


    override val textSimple: LiveData<String> = Transformations.map(textRawData, {
        it?.textSimple
    })

    override val textWithTaam: LiveData<String> = Transformations.map(textRawData, {
        it?.textWithTaam
    })
    override val audioUrl = Transformations.map(textChunksData, {
        it?.let {
            Uri.parse(it.audioUrl())
        }
    })

    override val videoUrl = Transformations.map(textChunksData, {
        it?.let {
            Uri.parse(it.videoUrl())
        }

    })

    override suspend fun pullRawTextData(tikkunId:String) {
        try {
            val textResponse = tikunService.getRawText(tikkunId)
            val textRawData = TextRawData(textResponse.taamim, textResponse.tikkun)
            db.tikunDao().insert(textRawData)
        }catch (exception:Exception){
            Timber.e("No connection")
        }

    }

    override suspend fun pullChunksVideoData(tikkunId:String) {
        try {
            val readSectionResponse = tikunService.getTextChunks(tikkunId)
            val chunks = readSectionResponse.map {
                it.toTextChunksData()
            }
            Timber.d("### chenc ${chunks.size} ### \n ### ${chunks.get(0)}")
            db.tikunChunksDao().insertAll(chunks)
        }catch (exception:Exception){
            Timber.e("No connection")
        }
    }


}