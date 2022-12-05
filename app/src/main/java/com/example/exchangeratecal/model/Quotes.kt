package com.example.exchangeratecal.model


import com.google.gson.annotations.SerializedName

data class Quotes(
    @SerializedName("USDKRW")
    val uSDKRW: Double?
)