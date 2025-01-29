package org.example.http.tasks

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import org.example.api.usecases.DeleteTaskUseCase
import javax.inject.Inject

class DeleteTaskHttpRequest @Inject constructor(
    private val deleteTaskUseCase: DeleteTaskUseCase
) {
    suspend fun handleDeleteTask(call: ApplicationCall) {
        val name = call.parameters["taskName"]
        println(name)
        if (name.isNullOrEmpty()) {
            call.respond(HttpStatusCode.BadRequest, "Task name is required")
            return
        }

        val success = deleteTaskUseCase.invoke(name)
        if (success) {
            call.respond(HttpStatusCode.OK, "Task deleted successfully")
        } else {
            call.respond(HttpStatusCode.NotFound, "Task not found")
        }
    }
}
