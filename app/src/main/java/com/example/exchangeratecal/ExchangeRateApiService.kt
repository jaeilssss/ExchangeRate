package com.example.exchangeratecal

import com.example.exchangeratecal.model.DataX
import retrofit2.http.Headers

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ExchangeRateApiService {


    @GET("live")
    suspend fun getData(
            @Query("source") source : String,
            @Header("apikey") key : String
    ) : Response<DataX>
}