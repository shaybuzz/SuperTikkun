package com.supertikkun.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.supertikkun.local.entities.TextRawData

@Dao
interface TextRawDao {

    @Query("SELECT * FROM text_source ")
    fun getTextRawData():LiveData<TextRawData?>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(textRawData: TextRawData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(textRawData: TextRawData)

}