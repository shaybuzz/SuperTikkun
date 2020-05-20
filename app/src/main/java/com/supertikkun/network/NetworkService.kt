package com.supertikkun.network

import com.github.leonardoxh.livedatacalladapter.LiveDataCallAdapterFactory
import com.supertikkun.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber


const val BASE = "https://supertikkun-dev.herokuapp.com"

class NetworkService {
    companion object {

        val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger{

            override fun log(message: String) {
                Timber.d(message)
            }
        })



        val client = OkHttpClient().newBuilder()
            .addInterceptor(logging.apply {
                level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
            })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory.create())

            .build()
        val tikunService = retrofit.create(TikunService::class.java)
    }


}
