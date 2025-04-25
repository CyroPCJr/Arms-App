package com.example.armsapp.network.service

import com.example.armsapp.network.dto.ArmsTeamDto
import com.example.armsapp.utils.AndroidLogger
import com.example.armsapp.utils.Logger
import com.example.armsapp.utils.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArmsTeamServiceImpl(
    private val client: HttpClient,
    private val logger: Logger = AndroidLogger()
) : ApiService<ArmsTeamDto> {

    override suspend fun apiGetCall(): List<ArmsTeamDto> {
        return safeApiCall<List<ArmsTeamDto>>(logger) {
            client.get("armsTeam").body()
        }.getOrElse {
            logger.e("ArmsTeamServiceImpl", "Failed to fetch arms team: ${it.message}")
            emptyList<ArmsTeamDto>()
        }
    }

}