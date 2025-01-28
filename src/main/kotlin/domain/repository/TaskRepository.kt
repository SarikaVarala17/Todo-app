package org.example.domain.repository


import org.example.domain.entities.Task


interface TaskRepository {
    suspend fun allTasks(): List<Task>
    suspend fun taskByName(name: String): Task?
    suspend fun addTask(task: Task)
    suspend fun removeTask(name: String): Boolean
}