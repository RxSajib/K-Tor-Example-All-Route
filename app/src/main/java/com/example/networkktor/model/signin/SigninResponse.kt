package com.example.networkktor.model.signin


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class SigninResponse(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("data")
    val `data`: Data,
    @SerialName("message")
    val message: String,
    @SerialName("success")
    val success: Boolean
)