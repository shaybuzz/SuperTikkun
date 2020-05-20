package com.supertikkun.network.response


import com.google.gson.annotations.SerializedName
import com.supertikkun.local.entities.ChunkData

data class SyncTime(
    val l: Int,
    val r: Int,
    val t: Double,
    val w: Int
)

fun SyncTime.toChunkData():ChunkData{
    return ChunkData(t, r)
}