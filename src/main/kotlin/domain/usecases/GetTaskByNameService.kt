package org.example.domain.usecases

import org.example.domain.entities.Task
import org.example.domain.repository.TaskRepository
import javax.inject.Inject

class GetTaskByNameService @Inject constructor(
    private val repository: TaskRepository
) {
    suspend fun invoke(name: String): Task? = repository.taskByName(name)
}
