package org.example.domain.entities

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class TaskTest {

    @Test
    fun `should initialize Task with valid values`() {
        val task = Task(name = "Test Task", description = "Test Description", priority = Priority.High)
        assertEquals("Test Task", task.name)
        assertEquals("Test Description", task.description)
        assertEquals(Priority.High, task.priority)
    }

    @Test
    fun `should handle empty description`() {
        val task = Task(name = "Empty Description Task", description = "", priority = Priority.Low)
        assertEquals("", task.description)
        assertEquals(Priority.Low, task.priority)
    }

    @Test
    fun `should initialize Task with Low priority`() {
        val task = Task(name = "Low Priority Task", description = "Low priority description", priority = Priority.Low)
        assertEquals(Priority.Low, task.priority)
    }

    @Test
    fun `should initialize Task with Medium priority`() {
        val task = Task(name = "Medium Priority Task", description = "Medium priority description", priority = Priority.Medium)
        assertEquals(Priority.Medium, task.priority)
    }


    @Test
    fun `should create task with high priority`() {
        val task = Task(name = "High Priority Task", description = "", priority = Priority.High)
        assertEquals(Priority.High, task.priority)

    }
}
