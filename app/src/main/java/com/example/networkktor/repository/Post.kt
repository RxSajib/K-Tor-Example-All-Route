package com.example.networkktor.repository

import com.example.networkktor.model.PostReponse
import com.example.networkktor.model.PostReponseItem
import com.example.networkktor.network.KtorClint
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.url

class Post {

    suspend fun getPost() : List<PostReponseItem> = KtorClint.httpClint.get {
        url("https://jsonplaceholder.typicode.com/posts")
    }.body<List<PostReponseItem>>()
}