package com.example.armsapp.network.service

interface ApiService<T> {
    suspend fun apiGetCall(): List<T>
}
