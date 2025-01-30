package org.example.domain.usecases

import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.example.domain.entities.Priority
import org.example.domain.entities.Task
import org.example.domain.repository.TaskRepository
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import org.junit.jupiter.api.Assertions.*


class GetAllTaskServiceTest{

    private lateinit var taskRepository: TaskRepository

    @BeforeEach
    fun setup() {
        taskRepository = mockk()
    }

    @Test
    fun `should return all tasks when tasks are available`() = runTest {
        val tasks = listOf(
            Task("Task1", "Description1", Priority.Medium),
            Task("Task2", "Description2", Priority.High)
        )
        coEvery { taskRepository.allTasks() } returns tasks

        val result = taskRepository.allTasks()
        assertEquals(2, result.size)
        assertEquals("Task1", result[0].name)
        assertEquals(Priority.High, result[1].priority)

        coVerify { taskRepository.allTasks() }
    }

    @Test
    fun `should return an empty list when no tasks are available`() = runTest {
        coEvery { taskRepository.allTasks() } returns emptyList()

        val result = taskRepository.allTasks()
        assertTrue(result.isEmpty())

        coVerify { taskRepository.allTasks() }
    }
}
