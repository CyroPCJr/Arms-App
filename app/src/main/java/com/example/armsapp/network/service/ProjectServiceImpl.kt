package com.example.armsapp.network.service

import com.example.armsapp.network.dto.ProjectDto
import com.example.armsapp.utils.AndroidLogger
import com.example.armsapp.utils.Logger
import com.example.armsapp.network.utils.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ProjectServiceImpl(
    private val client: HttpClient,
    private val logger: Logger = AndroidLogger()
) : ApiService<ProjectDto> {

    override suspend fun apiGetCall(): List<ProjectDto> {
        return safeApiCall<List<ProjectDto>>(logger) {
            client.get("projects").body()
        }.getOrElse {
            logger.e("ProjectServiceImpl", "Failed to fetch projects: ${it.message}")
            emptyList<ProjectDto>()
        }
    }

}