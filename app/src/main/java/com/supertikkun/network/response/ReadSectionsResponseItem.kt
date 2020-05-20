package com.supertikkun.network.response


import com.google.gson.annotations.SerializedName
import com.supertikkun.local.entities.TextChunksData

data class ReadSectionsResponseItem(
    @SerializedName("audio_size")
    val audioSize: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("file_id")
    val fileId: String,
    val id: String,
    @SerializedName("is_available")
    val isAvailable: Boolean,
    val length: Int,
    val narrator: Narrator,
    @SerializedName("read_section")
    val readSection: ReadSection,
    @SerializedName("sync_times")
    val syncTimes: List<SyncTime>,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("video_size")
    val videoSize: Int
)

fun ReadSectionsResponseItem.toTextChunksData(): TextChunksData {
    return TextChunksData(
        id,
        syncTimes.map {
            it.toChunkData()
        },
        fileId,
        length,
        videoSize,
        audioSize
    )
}
