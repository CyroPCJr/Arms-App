package com.example.armsapp.network.service

import com.example.armsapp.network.ArmsApiClientProvider
import io.ktor.client.HttpClient
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ArmsTeamServiceImplTest {

    private lateinit var client: HttpClient
    private lateinit var service: ArmsTeamServiceImpl

    @Before
    fun setup() {
        client = ArmsApiClientProvider.client
        service = ArmsTeamServiceImpl(client)
    }

    @Test
    fun `apiGetCall should return list of ArmsTeamDto`() = runTest {
        val result = service.apiGetCall("armsTeam")
        assertTrue(result.isNotEmpty())
        println(result)
    }
}