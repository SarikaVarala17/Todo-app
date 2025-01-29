package org.example.api.usecases

import org.example.api.entities.TaskApiModel
import org.example.api.mappers.TaskMapper
import org.example.domain.usecases.UpdateTaskService
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val updateTaskService: UpdateTaskService,
    private val taskMapper: TaskMapper
) {
    suspend fun invoke(apiTask: TaskApiModel) {
        val domainTask = taskMapper.toDomain(apiTask)
        updateTaskService.invoke(domainTask)
    }
}
