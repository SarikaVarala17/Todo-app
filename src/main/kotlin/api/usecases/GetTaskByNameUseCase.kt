package org.example.api.usecases

import org.example.api.mappers.TaskMapper
import org.example.domain.usecases.GetTaskByNameService
import javax.inject.Inject

class GetTaskByNameUseCase @Inject constructor(
    private val getTaskByNameService: GetTaskByNameService,
    private val taskMapper: TaskMapper
) {
    suspend fun invoke(name: String) = getTaskByNameService.invoke(name)?.let {
        taskMapper.toApi(it)
    }
}
