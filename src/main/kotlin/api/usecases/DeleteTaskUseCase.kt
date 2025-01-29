package org.example.api.usecases

import org.example.domain.usecases.DeleteTaskService
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val deleteTaskService: DeleteTaskService
) {
    suspend fun invoke(name: String) = deleteTaskService.invoke(name)
}
