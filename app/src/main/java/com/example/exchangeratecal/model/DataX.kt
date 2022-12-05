package com.example.exchangeratecal.model


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("quotes")
    val quotes: QuotesX?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("timestamp")
    val timestamp: Int?
)