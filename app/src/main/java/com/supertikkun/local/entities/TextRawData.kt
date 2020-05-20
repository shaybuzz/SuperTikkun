package com.supertikkun.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "text_source")
data class TextRawData(
    @ColumnInfo(name = "taam") val textWithTaam: String,
    @ColumnInfo(name = "simple") val textSimple: String){

    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}