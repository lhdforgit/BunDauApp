package com.example.bundauapp.data.retrofit

import com.example.bundauapp.data.retrofit.response.LiveDataCallAdapterFactory
import com.example.bundauapp.data.retrofit.response.SafeGsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object ServiceGenerator {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    private val builder = Retrofit.Builder().apply {
        baseUrl(BASE_URL)
        addCallAdapterFactory(LiveDataCallAdapterFactory())
        addConverterFactory(SafeGsonConverterFactory())
    }

    private var retrofit: Retrofit? = null

    private val logging = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.HEADERS)

    /**
     *  Create default service
     */

    fun <S> createService(serviceClass: Class<S>): S {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        builder.client(httpClient.build())
        builder.baseUrl(BASE_URL)
        retrofit = builder.build()
        return retrofit!!.create(serviceClass)
    }
}