package org.example.api

import org.example.domain.entities.Task
import org.example.domain.repository.TaskRepository
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureRouting(taskrepository: TaskRepository) {
    routing {
        route("/tasks") {
            get {
                val tasks = taskrepository.allTasks()
                call.respond(tasks)
            }

            get("/byName/{taskName}") {
                val name = call.parameters["taskName"]
                if (name == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
                val task = taskrepository.taskByName(name)
                if (task == null) {
                    call.respond(HttpStatusCode.NotFound)
                    return@get
                }
                call.respond(task)
            }
            put("/{taskName}") {
                val name = call.parameters["taskName"]
                if (name == null) {
                    call.respond(HttpStatusCode.BadRequest, "Task name is required")
                    return@put
                }
                val task = taskrepository.taskByName(name)
                if (task == null) {
                    call.respond(HttpStatusCode.NotFound, "Task not found")
                    return@put
                }
                try {
                    val updatedTask = call.receive<Task>()
                    if (updatedTask.name != name) {
                        call.respond(HttpStatusCode.BadRequest, "Task name mismatch")
                        return@put
                    }
                    taskrepository.removeTask(name)
                    taskrepository.addTask(updatedTask)
                    call.respond(HttpStatusCode.OK, "Task updated successfully")
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid request: ${e.message}")
                }
            }


            post {
                try {
                    val task = call.receive<Task>()
                    taskrepository.addTask(task)
                    call.respond(HttpStatusCode.OK,"Task added successfully")
                } catch (ex: IllegalStateException) {
                    call.respond(HttpStatusCode.BadRequest)
                } catch (ex: JsonConvertException) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            delete("/{taskName}") {
                val name = call.parameters["taskName"]
                if (name == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@delete
                }
                if (taskrepository.removeTask(name)) {
                    call.respond(HttpStatusCode.OK,"Task deleted successfully")
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }
    }
}
