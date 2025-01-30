package org.example.domain.usecases

import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.example.domain.repository.TaskRepository
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import org.junit.jupiter.api.Assertions.*


class DeleteTaskServiceTest{

    private lateinit var taskRepository: TaskRepository

    @BeforeEach
    fun setup() {
        taskRepository = mockk()
    }

    @Test
    fun `should remove a task by name successfully`() = runTest {
        coEvery { taskRepository.removeTask("Task1") } returns true

        val result = taskRepository.removeTask("Task1")
        assertTrue(result)

        coVerify { taskRepository.removeTask("Task1") }
    }

    @Test
    fun `should return false when removing a non-existent task`() = runTest {
        coEvery { taskRepository.removeTask("NonExistentTask") } returns false

        val result = taskRepository.removeTask("NonExistentTask")
        assertFalse(result)

        coVerify { taskRepository.removeTask("NonExistentTask") }
    }
}
