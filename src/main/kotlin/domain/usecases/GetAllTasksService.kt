package org.example.domain.usecases

import org.example.domain.entities.Task
import org.example.domain.repository.TaskRepository
import javax.inject.Inject

class GetAllTasksService @Inject constructor(
    private val repository: TaskRepository
) {
    suspend fun invoke(): List<Task> = repository.allTasks()
}
