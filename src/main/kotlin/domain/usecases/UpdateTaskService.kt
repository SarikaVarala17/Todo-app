package org.example.domain.usecases

import org.example.domain.entities.Task
import org.example.domain.repository.TaskRepository
import javax.inject.Inject

class UpdateTaskService @Inject constructor(
    private val repository: TaskRepository
) {
    suspend fun invoke(task: Task) {
        repository.removeTask(task.name)
        repository.addTask(task)
    }
}
