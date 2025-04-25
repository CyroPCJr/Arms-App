package com.example.armsapp.network.service

import com.example.armsapp.network.ArmsApiClientProvider
import com.example.armsapp.utils.Logger
import io.ktor.client.HttpClient
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ProjectServiceImplTest {

    private lateinit var client: HttpClient
    private lateinit var service: ProjectServiceImpl
    private val mockLogger = mockk<Logger>(relaxed = true)

    @Before
    fun setup() {
        client = ArmsApiClientProvider.createClient(logger = mockLogger)
        service = ProjectServiceImpl(client, logger = mockLogger)
    }

    @Test
    fun `apiGetCall should return list of ProjectDto`() = runTest {
        val result = service.apiGetCall()
        assertTrue(result.isNotEmpty())
        println(result)

        verify {
            mockLogger.d(
                tag = "ArmsApiClientProvider",
                "Initializing ArmsApiClientProvider.client"
            )
        }
    }
}
