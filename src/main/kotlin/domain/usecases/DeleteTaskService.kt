package org.example.domain.usecases

import org.example.domain.repository.TaskRepository
import javax.inject.Inject

class DeleteTaskService @Inject constructor(
    private val repository: TaskRepository
) {
    suspend fun invoke(name: String): Boolean = repository.removeTask(name)
}
