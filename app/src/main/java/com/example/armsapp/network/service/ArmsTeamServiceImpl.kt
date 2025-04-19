package com.example.armsapp.network.service

import com.example.armsapp.network.dto.ArmsTeamDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArmsTeamServiceImpl(private val client: HttpClient) : ApiService<ArmsTeamDto> {

    override suspend fun apiGetCall(endpoint: String): List<ArmsTeamDto> {
        return client.get(endpoint).body()
    }

}