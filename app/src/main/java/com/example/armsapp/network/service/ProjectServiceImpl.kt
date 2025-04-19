package com.example.armsapp.network.service

import com.example.armsapp.network.dto.ProjectDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ProjectServiceImpl(private val client: HttpClient) : ApiService<ProjectDto> {

    override suspend fun apiGetCall(endpoint: String): List<ProjectDto> {
        return client.get(endpoint).body()
    }
}