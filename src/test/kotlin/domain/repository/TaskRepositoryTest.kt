package org.example.domain.repository

import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.example.domain.entities.Priority
import org.example.domain.entities.Task
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test



class TaskRepositoryTest {

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

    @Test
    fun `should add a task successfully`() = runTest {
        val newTask = Task("Task3", "Description3", Priority.Low)

        coEvery { taskRepository.addTask(newTask) } just Runs

        taskRepository.addTask(newTask)

        coVerify { taskRepository.addTask(newTask) }
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
