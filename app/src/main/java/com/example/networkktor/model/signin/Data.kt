package com.example.networkktor.model.signin


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Data(
    val name : String,
    val email : String
)