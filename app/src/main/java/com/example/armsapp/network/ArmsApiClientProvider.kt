package com.example.armsapp.network

import com.example.armsapp.utils.AndroidLogger
import com.example.armsapp.utils.Logger
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object ArmsApiClientProvider {
    private const val TAG = "ArmsApiClientProvider"

    fun createClient(logger: Logger = AndroidLogger()): HttpClient {
        logger.d(TAG, "Initializing ArmsApiClientProvider.client")
        return try {
            HttpClient(OkHttp) {
                engine {
                    config {
                        followRedirects(true)
                    }
                }

                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    })
                }

                install(Logging) {
                    level = LogLevel.BODY
                }

                install(HttpTimeout) {
                    requestTimeoutMillis = 30_000
                    connectTimeoutMillis = 10_000
                    socketTimeoutMillis = 15_000
                }

                defaultRequest {
                    url {
                        protocol = URLProtocol.HTTPS
                        host = "arms-api-latest.onrender.com"
                    }
                    header(HttpHeaders.UserAgent, "ArmsAppAndroid/1.0")
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                }
            }
        } catch (e: Exception) {
            logger.e(TAG, "Failed to initialize HttpClient")
            throw e
        }
    }

    val client: HttpClient by lazy {
        createClient()
    }

}

