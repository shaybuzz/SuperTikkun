package com.supertikkun.local

import android.content.Context
import androidx.room.*
import com.supertikkun.local.entities.Converters
import com.supertikkun.local.entities.TextChunksData
import com.supertikkun.local.entities.TextRawData


@Database(entities = arrayOf(TextRawData::class, TextChunksData::class), version = 1)
@TypeConverters(Converters::class)
abstract class TikunDataBase : RoomDatabase() {
    abstract fun tikunDao(): TextRawDao
    abstract fun tikunChunksDao(): TextChunksDao


    companion object {
        @Volatile
        private var INSTANCE: TikunDataBase? = null
        private val DB_NAME = "tikun.db"
        fun getDatabase(context: Context): TikunDataBase {
            if (INSTANCE == null) {
                synchronized(TikunDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            TikunDataBase::class.java, DB_NAME
                        )
                            //.allowMainThreadQueries() // SHOULD NOT BE USED IN PRODUCTION !!!
//                        .addCallback(object : Callback() {
//                            override fun onCreate(db: SupportSQLiteDatabase) {
//                                super.onCreate(db)
//                                Log.d("MoviesDatabase", "populating with data...")
//                                PopulateDbAsync(INSTANCE).execute()
//                            }
//                        })
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}

