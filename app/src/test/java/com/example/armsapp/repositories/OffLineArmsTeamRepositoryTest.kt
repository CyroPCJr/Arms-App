package com.example.armsapp.repositories

import com.example.armsapp.data.local.repositories.ArmsLocalRepository
import com.example.armsapp.data.local.repositories.OffLineArmsRepository
import com.example.armsapp.domain.model.ArmsTeam
import com.example.armsapp.network.dto.ArmsTeamDto
import com.example.armsapp.network.service.ApiService
import com.example.armsapp.utils.Logger
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class OfflineArmsTeamRepositoryTest {

    private val localDataSource = mockk<ArmsLocalRepository<ArmsTeam, ArmsTeamDto>>(relaxed = true)
    private val remoteDataSource = mockk<ApiService<ArmsTeamDto>>(relaxed = true)
    private val logger = mockk<Logger>(relaxed = true)

    private lateinit var repository: OffLineArmsRepository<ArmsTeam, ArmsTeamDto>

    @Before
    fun setup() {
        repository = OffLineArmsRepository(localDataSource, remoteDataSource, logger)
    }

    @Test
    fun `fetchAndSaveProjects should return success when remote call works`() = runTest {
        // Arrange
        val fakeArmsTeam = listOf(
            ArmsTeamDto(
                id = 1,
                name = "Alice Costa",
                jobPosition = "Desenvolvedora Android",
                instagramLabel = "@alice.dev",
                instagramUrl = "https://instagram.com/alice.dev",
                image = "https://example.com/images/alice.jpg"
            ),
            ArmsTeamDto(
                id = 2,
                name = "Bruno Silva",
                jobPosition = "Designer UI/UX",
                instagramLabel = "@bruno.design",
                instagramUrl = "https://instagram.com/bruno.design",
                image = "https://example.com/images/bruno.jpg"
            ),
            ArmsTeamDto(
                id = 3,
                name = "Carla Souza",
                jobPosition = "Gerente de Produto",
                instagramLabel = "@carla.pm",
                instagramUrl = "https://instagram.com/carla.pm",
                image = "https://example.com/images/carla.jpg"
            )
        )
        coEvery { remoteDataSource.apiGetCall() } returns fakeArmsTeam
        coEvery { localDataSource.insertAll(fakeArmsTeam) } returns Result.success(Unit)

        // Act
        val result = repository.fetchAndSaveArmsRepo()

        // Assert
        assertTrue(result.isSuccess)
        coVerify(exactly = 1) { remoteDataSource.apiGetCall() }
        coVerify(exactly = 1) { localDataSource.insertAll(fakeArmsTeam) }
    }

    @Test
    fun `fetchAndSaveProjects should return failure when exception is thrown`() = runTest {
        // Arrange
        coEvery { remoteDataSource.apiGetCall() } throws Exception("Network error")

        // Act
        val result = repository.fetchAndSaveArmsRepo()

        // Assert
        assertTrue(result.isFailure)
        coVerify(exactly = 1) { remoteDataSource.apiGetCall() }
        coVerify(exactly = 0) { localDataSource.insertAll(any()) }
    }

    @Test
    fun `fetchAndSaveIfEmpty should fetch and insert when local is empty`() = runTest {
        val fakeArmsTeam = listOf(
            ArmsTeamDto(
                id = 1,
                name = "Alice Costa",
                jobPosition = "Desenvolvedora Android",
                instagramLabel = "@alice.dev",
                instagramUrl = "https://instagram.com/alice.dev",
                image = "https://example.com/images/alice.jpg"
            ),
            ArmsTeamDto(
                id = 2,
                name = "Bruno Silva",
                jobPosition = "Designer UI/UX",
                instagramLabel = "@bruno.design",
                instagramUrl = "https://instagram.com/bruno.design",
                image = "https://example.com/images/bruno.jpg"
            ),
            ArmsTeamDto(
                id = 3,
                name = "Carla Souza",
                jobPosition = "Gerente de Produto",
                instagramLabel = "@carla.pm",
                instagramUrl = "https://instagram.com/carla.pm",
                image = "https://example.com/images/carla.jpg"
            )
        )
        // Arrange
        coEvery { localDataSource.hasData() } returns false
        coEvery { remoteDataSource.apiGetCall() } returns fakeArmsTeam
        coEvery { localDataSource.insertAll(fakeArmsTeam) } returns Result.success(Unit)

        // Act
        val result = repository.fetchAndSaveArmsRepo()

        // Assert
        assertTrue(result.isSuccess)
        coVerify { localDataSource.hasData() }
        coVerify { remoteDataSource.apiGetCall() }
        coVerify { localDataSource.insertAll(fakeArmsTeam) }
    }

    @Test
    fun `fetchAndSaveIfEmpty should skip fetch when local is not empty`() = runTest {
        // Arrange
        coEvery { localDataSource.hasData() } returns true

        // Act
        val result = repository.fetchAndSaveArmsRepo()

        // Assert
        assertTrue(result.isSuccess)
        coVerify { localDataSource.hasData() }
        coVerify(exactly = 0) { remoteDataSource.apiGetCall() }
        coVerify(exactly = 0) { localDataSource.insertAll(any()) }
    }
}