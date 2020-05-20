package com.supertikkun.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.supertikkun.network.response.ReadSectionsResponseItem
import com.supertikkun.network.response.SyncTime
import com.supertikkun.network.response.toChunkData
import com.supertikkun.network.response.toTextChunksData

@Entity(tableName = "text_chunks")
data class TextChunksData(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "sync_times")
    val syncTimes: List<ChunkData>,
    @ColumnInfo(name = "file_id")
    val fileId: String,
    val length: Int,
    @ColumnInfo(name = "video_size")
    val videoSize: Int,
    @ColumnInfo(name = "audio_size")
    val audioSize: Int
){
}

fun TextChunksData.audioUrl():String =  "https://supertikkun.s3.amazonaws.com/audio/$fileId.mp3"
fun TextChunksData.videoUrl():String = "https://supertikkun.s3.amazonaws.com/video/$fileId.mp4"



data class ChunkData(val time: Double, val wordIndex: Int)


fun convert(serverData: List<ReadSectionsResponseItem>): List<TextChunksData> {
    return  serverData.map {
        it.toTextChunksData()
    }
}

