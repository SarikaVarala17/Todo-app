package org.example.api.usecases

import org.example.domain.usecases.CreateTaskService
import org.example.api.mappers.TaskMapper
import org.example.api.entities.TaskApiModel
import javax.inject.Inject

class CreateTaskUseCase @Inject constructor(
    private val createTaskService: CreateTaskService,
    private val taskMapper: TaskMapper
) {
    suspend fun invoke(apiModel: TaskApiModel) {
        val domainTask = taskMapper.toDomain(apiModel)
        createTaskService.invoke(domainTask)
    }
}
