package com.example.networkktor.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep
import io.ktor.serialization.kotlinx.json.*

@Serializable
data class PostReponseItem(
    val body: String,
    val title: String,
)