package com.example.networkktor.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClint {

    val json= Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        isLenient = true
    }

    val httpClint = HttpClient{
        install(HttpTimeout){
            socketTimeoutMillis = 3000
            requestTimeoutMillis = 3000
            connectTimeoutMillis = 3000
        }

        install(Logging){
            level = LogLevel.ALL
        }

        install(ContentNegotiation){
            json(
                Json {
                   ignoreUnknownKeys  =true
                    isLenient = true
                }
            )
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }
}