package org.example.domain.usecases

import org.example.domain.entities.Task
import org.example.domain.repository.TaskRepository
import javax.inject.Inject

class CreateTaskService @Inject constructor(private val repository: TaskRepository) {
    suspend fun invoke(task: Task) = repository.addTask(task)
}
