package org.example.http.tasks

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.example.api.entities.TaskApiModel
import org.example.api.usecases.CreateTaskUseCase
import javax.inject.Inject

class CreateTaskHttpRequest @Inject constructor(
    private val createTaskUseCase: CreateTaskUseCase
) {
    suspend fun handleCreateTask(call: ApplicationCall) {
        try {
            val apiTask = call.receive<TaskApiModel>()
            createTaskUseCase.invoke(apiTask)
            call.respond(HttpStatusCode.Created, "Task created successfully")
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, "Invalid task data: ${e.message}")
        }
    }
}
