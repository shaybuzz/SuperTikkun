package com.supertikkun.network

import com.supertikkun.network.response.ReadSectionsResponse
import com.supertikkun.network.response.TikkunTextResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TikunService {
//    @GET("/read-sections/F5166F17-6D33-4210-940B-045A8392FEDD/media")
//    fun getReadSection2():LiveData<ReadSectionsResponse>
//@GET("/read-sections/F5166F17-6D33-4210-940B-045A8392FEDD/media")
//@GET("/read-sections/F5166F17-6D33-4210-940B-045A8392FEDD/text?text-export-option=client")



    @GET("/read-sections/{id}/media")
    suspend fun getTextChunks(@Path("id")id:String):ReadSectionsResponse

    @GET("/read-sections/{id}/text?text-export-option=client")
    suspend fun getRawText(@Path("id")id:String):TikkunTextResponse
}