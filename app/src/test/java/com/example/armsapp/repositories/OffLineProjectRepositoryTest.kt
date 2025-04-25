package com.example.armsapp.repositories

import com.example.armsapp.data.local.repositories.ArmsLocalRepository
import com.example.armsapp.data.local.repositories.OffLineArmsRepository
import com.example.armsapp.domain.model.Project
import com.example.armsapp.network.dto.ProjectDto
import com.example.armsapp.network.service.ApiService
import com.example.armsapp.utils.Logger
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class OffLineProjectRepositoryTest {

    private val localDataSource = mockk<ArmsLocalRepository<Project, ProjectDto>>(relaxed = true)
    private val remoteDataSource = mockk<ApiService<ProjectDto>>(relaxed = true)
    private val logger = mockk<Logger>(relaxed = true)

    private lateinit var repository: OffLineArmsRepository<Project, ProjectDto>

    @Before
    fun setup() {
        repository = OffLineArmsRepository(localDataSource, remoteDataSource, logger)
    }

    @Test
    fun `fetchAndSaveProjects should return success when remote call works`() = runTest {
        // Arrange
        val fakeProjects = listOf(
            ProjectDto(
                id = 1,
                urlImage = "https://example.com/images/project1.png",
                linkPage = "https://example.com/projects/1",
                name = "ArmsApp",
                type = "Mobile"
            ),
            ProjectDto(
                id = 2,
                urlImage = "https://example.com/images/project2.png",
                linkPage = "https://example.com/projects/2",
                name = "ArmsAPI",
                type = "Backend"
            ),
            ProjectDto(
                id = 3,
                urlImage = "https://example.com/images/project3.png",
                linkPage = "https://example.com/projects/3",
                name = "ArmsDashboard",
                type = "Web"
            )
        )
        coEvery { remoteDataSource.apiGetCall() } returns fakeProjects
        coEvery { localDataSource.insertAll(fakeProjects) } returns Result.success(Unit)

        // Act
        val result = repository.fetchAndSaveArmsRepo()

        // Assert
        assertTrue(result.isSuccess)
        coVerify(exactly = 1) { remoteDataSource.apiGetCall() }
        coVerify(exactly = 1) { localDataSource.insertAll(fakeProjects) }
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
        val fakeProjects = listOf(
            ProjectDto(
                id = 1,
                urlImage = "https://example.com/images/project1.png",
                linkPage = "https://example.com/projects/1",
                name = "ArmsApp",
                type = "Mobile"
            ),
            ProjectDto(
                id = 2,
                urlImage = "https://example.com/images/project2.png",
                linkPage = "https://example.com/projects/2",
                name = "ArmsAPI",
                type = "Backend"
            ),
            ProjectDto(
                id = 3,
                urlImage = "https://example.com/images/project3.png",
                linkPage = "https://example.com/projects/3",
                name = "ArmsDashboard",
                type = "Web"
            )
        )
        // Arrange
        coEvery { localDataSource.hasData() } returns false
        coEvery { remoteDataSource.apiGetCall() } returns fakeProjects
        coEvery { localDataSource.insertAll(fakeProjects) } returns Result.success(Unit)

        // Act
        val result = repository.fetchAndSaveArmsRepo()

        // Assert
        assertTrue(result.isSuccess)
        coVerify { localDataSource.hasData() }
        coVerify { remoteDataSource.apiGetCall() }
        coVerify { localDataSource.insertAll(fakeProjects) }
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