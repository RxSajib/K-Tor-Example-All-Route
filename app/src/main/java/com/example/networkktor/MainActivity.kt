package com.example.networkktor

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.networkktor.model.Comment
import com.example.networkktor.model.POST
import com.example.networkktor.model.PostReponse
import com.example.networkktor.model.PostReponseItem
import com.example.networkktor.model.signin.SigninResponse
import com.example.networkktor.network.KtorClint
import com.example.networkktor.repository.Post
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.http.buildUrl
import io.ktor.http.path
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        textView = findViewById(R.id.Data)

        getErrorHandling()

        //todo get request
        // sampleGetRequest()
        //todo get request


        //todo get request with query
        // getRequestQuery()
        //todo get request with query


        //todo get request
        //  getRequestPathValue()
        //todo get request

        //todo post request
        // postRequest()
        //todo post request


        //todo put request
        // putRequest()
        //todo put request

        //todo delete request
        // deleteRequest()
        //todo delete request
    }

    private fun sampleGetRequest() {
        lifecycleScope.launch(Dispatchers.IO) {

            try {
                var res = KtorClint.httpClint.get(
                    buildUrl {
                        host = "jsonplaceholder.typicode.com/"
                        path("posts")
                        protocol = URLProtocol.HTTPS
                    }
                )

                if (res.status == HttpStatusCode.OK) {
                    val posts: List<PostReponseItem> = res.body()
                    Log.d(TAG, "onCreate: list of post $posts")
                } else {
                    Log.d(TAG, "onCreate: failed")
                }
            } catch (e: Exception) {
                Log.d(TAG, "onCreate: error ${e.message}")
            }


        }
    }

    private fun getRequestQuery() {
        lifecycleScope.launch(Dispatchers.IO) {

            try {
                val response = KtorClint.httpClint.get(
                    buildUrl {
                        host = "jsonplaceholder.typicode.com/"
                        path("comments")
                        parameters.append("postId", "1") // query append
                        protocol = URLProtocol.HTTPS
                    }
                )

                if (response.status == HttpStatusCode.OK) {
                    val data = response.body<List<Comment>>()

                    Log.d(TAG, "onCreate: single response $data")
                    withContext(Dispatchers.Main) {
                        textView.setText(data[0].body)
                    }

                } else {
                    Log.d(TAG, "onCreate: failed")
                }

            } catch (e: Exception) {
                Log.d(TAG, "onCreate exc: ${e.message}")
            }
        }
    }

    private fun getRequestPathValue() {
        lifecycleScope.launch {
            try {
                val response = KtorClint.httpClint.get(
                    buildUrl {
                        host = "jsonplaceholder.typicode.com"
                        path("posts", "1")
                        protocol = URLProtocol.HTTPS
                    }
                )

                if (response.status == HttpStatusCode.OK) {
                    val response = response.body<PostReponseItem>()
                    Log.d(TAG, "onCreate: success ${response.body}")
                    textView.setText(response.body)
                } else {
                    Log.d(TAG, "onCreate: failed")
                }
            } catch (e: Exception) {
                Log.d(TAG, "onCreate: ${e.message}")
            }
        }
    }

    private fun deleteRequest() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = KtorClint.httpClint.delete(
                    buildUrl {
                        host = "jsonplaceholder.typicode.com"
                        path("posts", "1")
                        protocol = URLProtocol.HTTPS
                    }
                )
                if (response.status == HttpStatusCode.OK) {
                    Log.d(TAG, "deleteRequest: ${response.status.value}")
                } else {
                    Log.d(TAG, "deleteRequest: failed")
                }

            } catch (e: Exception) {
                Log.d(TAG, "onCreate: error ${e.message}")
            }
        }
    }

    private fun putRequest() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = KtorClint.httpClint.put(
                    buildUrl {
                        host = "jsonplaceholder.typicode.com"
                        path("posts", "1")
                        protocol = URLProtocol.HTTPS
                    }
                ) {
                    setBody(
                        POST(
                            id = 1,
                            title = "sample title put",
                            body = "sample details put",
                            userId = 10
                        )
                    )
                }

                if (response.status == HttpStatusCode.OK) {
                    val data = response.body<POST>()
                    withContext(Dispatchers.Main) {
                        textView.setText(data.toString())
                    }
                } else {
                    Log.d(TAG, "onCreate: code ${response.status.value} ")
                    /*  val data = response.body<POST>()
                      withContext(Dispatchers.Main){
                          textView.setText(data.toString())
                      }*/
                }

            } catch (e: Exception) {
                Log.d(TAG, "onCreate: error ${e.message}")
            }
        }
    }

    private fun postRequest() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = KtorClint.httpClint.post(
                    buildUrl {
                        host = "jsonplaceholder.typicode.com"
                        protocol = URLProtocol.HTTPS
                        path("posts")

                    }
                ) {
                    setBody(
                        POST(
                            id = 1,
                            title = "sample title",
                            body = "sample details",
                            userId = 10
                        )
                    )
                }

                if (response.status == HttpStatusCode.OK) {
                    val response = response.body<POST>()
                    Log.d(TAG, "onCreate: success $response")
                } else {
                    Log.d(TAG, "onCreate: failed ${response.status.value}")
                    val response = response.body<POST>()
                    Log.d(TAG, "onCreate: success $response")

                    withContext(Dispatchers.Main) {
                        textView.setText(response.toString())
                    }
                }

            } catch (e: Exception) {
                Log.d(TAG, "onCreate: error ${e.message}")
            }
        }
    }
    
    
    private fun getErrorHandling(){
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = KtorClint.httpClint.post(
                    buildUrl {
                        host = "muktokowlom.com"
                        path("api/user/login")
                        protocol = URLProtocol.HTTPS
                        parameters.append("email", "sajibroy211@gmail.com")
                        parameters.append("password", "12345")
                    }
                )

                if(response.status == HttpStatusCode.OK){
                    val data : SigninResponse = response.body()
                    Log.d(TAG, "success: ${data.accessToken}")
                }else {
                    val error = response.bodyAsText()
                    Log.d(TAG, "getErrorHandling: error body $error")
                }
            }catch (e: ClientRequestException) { // Handles 4xx errors
                val errorBody = e.response.bodyAsText()
                Log.d(TAG, "Client error: ${e.response.status} - $errorBody")
            } catch (e: ServerResponseException) { // Handles 5xx errors
                val errorBody = e.response.bodyAsText()
                Log.d(TAG, "Server error: ${e.response.status} - $errorBody")
            }

            catch (e : Exception){
                Log.d(TAG, "getErrorHandling: error ${e.message}")
            }
        }

    }
}