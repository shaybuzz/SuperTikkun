package com.supertikkun.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.supertikkun.local.entities.TextChunksData
import com.supertikkun.local.entities.TextRawData

@Dao
interface TextChunksDao {

    @Query("SELECT * FROM text_chunks ")
    fun getTextChunksData():LiveData<List<TextChunksData?>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(textChunkData: TextChunksData)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(textChunkData: List<TextChunksData>)

}