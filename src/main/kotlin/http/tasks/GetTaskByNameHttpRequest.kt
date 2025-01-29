package org.example.http.tasks

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import org.example.api.usecases.GetTaskByNameUseCase
import javax.inject.Inject

class GetTaskByNameHttpRequest @Inject constructor(
    private val getTaskByNameUseCase: GetTaskByNameUseCase
) {
    suspend fun handleGetTaskByName(call: ApplicationCall) {
        val name = call.parameters["taskName"]
        if (name.isNullOrEmpty()) {
            call.respond(HttpStatusCode.BadRequest, "Task name is required")
            return
        }

        val task = getTaskByNameUseCase.invoke(name)
        if (task == null) {
            call.respond(HttpStatusCode.NotFound, "Task not found")
        } else {
            call.respond(HttpStatusCode.OK, task)
        }
    }
}
