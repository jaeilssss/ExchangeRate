package com.example.exchangeratecal

import retrofit2.http.Headers
import com.example.exchangeratecal.model.data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ExchangeRateApiService {


    @GET("live")
    suspend fun test(
            @Query("source") source : String,
            @Query("currencies") currencies  : String,
            @Header("apikey") key : String
    ) : Response<data>
}