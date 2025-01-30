package org.example.domain.usecases

import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.example.domain.entities.Priority
import org.example.domain.entities.Task
import org.example.domain.repository.TaskRepository
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import org.junit.jupiter.api.Assertions.*


class GetTaskByNameServiceTest{

    private lateinit var taskRepository: TaskRepository

    @BeforeEach
    fun setup() {
        taskRepository = mockk()
    }

    @Test
    fun `should return a task by name if it exists`() = runTest {
        val task = Task("Task1", "Description1", Priority.Medium)
        coEvery { taskRepository.taskByName("Task1") } returns task

        val result = taskRepository.taskByName("Task1")
        assertNotNull(result)
        assertEquals("Task1", result?.name)

        coVerify { taskRepository.taskByName("Task1") }
    }

    @Test
    fun `should return null when task by name does not exist`() = runTest {
        coEvery { taskRepository.taskByName("NonExistentTask") } returns null

        val result = taskRepository.taskByName("NonExistentTask")
        assertNull(result)

        coVerify { taskRepository.taskByName("NonExistentTask") }
    }
}
