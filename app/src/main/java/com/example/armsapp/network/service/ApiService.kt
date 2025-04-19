package com.example.armsapp.network.service

interface ApiService<T> {

    suspend fun apiGetCall(endpoint: String) : List<T>
}