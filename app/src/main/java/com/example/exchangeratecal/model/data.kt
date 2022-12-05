package com.example.exchangeratecal.model


import com.google.gson.annotations.SerializedName

data class data(
    @SerializedName("quotes")
    val quotes: Quotes?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("timestamp")
    val timestamp: Int?
)