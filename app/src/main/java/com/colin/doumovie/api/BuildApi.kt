package com.colin.doumovie.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by tianweiping on 2017/12/18.
 */
class BuildApi {

    companion object {
        var retrofit: Retrofit? = null
        fun buildApiServers(): ApiServers? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(BaseURL.SERVER_URL)
                        .client(getCusHttpClient())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
            }
            return retrofit!!.create(ApiServers::class.java)
        }

        fun getCusHttpClient(): OkHttpClient {
            val client = OkHttpClient.Builder()
                    .addInterceptor(HttpLogInterceptor("http", true))
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .build()
            return client
        }
    }
}