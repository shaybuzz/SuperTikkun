package com.supertikkun.local.entities

import android.R.attr.data
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromTimestamp(value: Long?): Date? {
            return value?.let { Date(it) }
        }

        @TypeConverter
        @JvmStatic
        fun dateToTimestamp(date: Date?): Long? {
            return date?.time?.toLong()
        }


        @TypeConverter
        @JvmStatic
        fun listChunksToString(chunks: List<ChunkData>): String {
            return Gson().toJson(chunks)
        }

        @TypeConverter
        @JvmStatic
        fun toChunksList(chunks: String): List<ChunkData> {
            val listType: Type = object : TypeToken<List<ChunkData>>() {}.type
            return Gson().fromJson(chunks, listType)
        }
    }
}