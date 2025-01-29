package org.example.http.tasks

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import org.example.api.usecases.GetAllTasksUseCase
import javax.inject.Inject

class GetAllTasksHttpRequest @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase
) {
    suspend fun handleGetAllTasks(call: ApplicationCall) {
        val tasks = getAllTasksUseCase.invoke()
        call.respond(HttpStatusCode.OK, tasks)
    }
}
