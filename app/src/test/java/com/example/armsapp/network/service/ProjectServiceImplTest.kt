package com.example.armsapp.network.service

import com.example.armsapp.network.ArmsApiClientProvider
import io.ktor.client.HttpClient
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ProjectServiceImplTest {

    private lateinit var client: HttpClient
    private lateinit var service: ProjectServiceImpl

    @Before
    fun setup() {
        client = ArmsApiClientProvider.client
        service = ProjectServiceImpl(client)
    }

    @Test
    fun `apiGetCall should return list of ProjectDto`() = runTest {
        val result = service.apiGetCall("projects")
        assertTrue(result.isNotEmpty())
        println(result)
    }
}
