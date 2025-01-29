package org.example.api.usecases

import org.example.api.mappers.TaskMapper
import org.example.domain.usecases.GetAllTasksService
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val getAllTasksService: GetAllTasksService,
    private val taskMapper: TaskMapper
) {
    suspend fun invoke() = getAllTasksService.invoke().map {
        taskMapper.toApi(it)
    }
}
