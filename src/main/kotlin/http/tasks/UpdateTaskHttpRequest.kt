package org.example.http.tasks

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.example.api.entities.TaskApiModel
import org.example.api.usecases.UpdateTaskUseCase
import javax.inject.Inject

class UpdateTaskHttpRequest @Inject constructor(
    private val updateTaskUseCase: UpdateTaskUseCase
) {
    suspend fun handleUpdateTask(call: ApplicationCall) {
        try {
            val apiTask = call.receive<TaskApiModel>()
            updateTaskUseCase.invoke(apiTask)
            call.respond(HttpStatusCode.OK, "Task updated successfully")
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, "Invalid task data: ${e.message}")
        }
    }
}
