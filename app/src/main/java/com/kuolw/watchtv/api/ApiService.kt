package com.kuolw.watchtv.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.*

interface ApiService {
    @GET("test.json")
    fun test(): Call<List<Channel>>

    companion object Factory {
        fun create(): ApiService {
            val props = Properties()
            this::class.java.classLoader?.getResourceAsStream("config.properties").use {
                props.load(it)
            }

            val retrofit = Retrofit.Builder()
                .baseUrl(props.getProperty("api"))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java);
        }
    }
}

data class Channel(
    var name: String,
    var sources: List<Source>
)

data class Source(
    var name: String,
    var src: String
)