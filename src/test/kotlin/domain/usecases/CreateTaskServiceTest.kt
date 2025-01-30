package org.example.domain.usecases

import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.example.domain.entities.Priority
import org.example.domain.entities.Task
import org.example.domain.repository.TaskRepository
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test


class CreateTaskServiceTest {

    private lateinit var taskRepository: TaskRepository

    @BeforeEach
    fun setup() {
        taskRepository = mockk()
    }

    @Test
    fun `should add a task successfully`() = runTest {
        val newTask = Task("Task", "Description", Priority.Low)

        coEvery { taskRepository.addTask(newTask) } just Runs

        taskRepository.addTask(newTask)

        coVerify { taskRepository.addTask(newTask) }
    }
}
