package com.example.exchangeratecal

import com.example.exchangeratecal.model.DataX
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object Repository {


    suspend fun getExchangeRateData() : DataX? {

        return exchangeRateApiService.getData("USD",BuildConfig.API_KEY)
                .body()


    }

    private val exchangeRateApiService : ExchangeRateApiService by lazy {

        Retrofit.Builder()
                .baseUrl(URL.Url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(buildHttpClient())
                .build()
                .create()
    }


    private fun buildHttpClient() : OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(
                            HttpLoggingInterceptor()
                    ).build()
}