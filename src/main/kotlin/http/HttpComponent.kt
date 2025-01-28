package org.example.http

import io.ktor.server.routing.*
import org.example.api.TodoApi
class HttpComponent(
    private val todoApi: TodoApi
) {
    fun setupRoutes(routing: Routing) {
        routing.apply {
            get("/tasks") {
                todoApi.getAllTasks(call)
            }

            get("/tasks/{name}") {
                todoApi.getTaskByName(call)
            }

            post("/tasks") {
                todoApi.createTask(call)
            }

            put("/tasks/{id}") {
                todoApi.updateTask(call)
            }

            delete("/tasks/{id}") {
                todoApi.deleteTask(call)
            }

            post("/tasks/add") {
                todoApi.addTask(call)
            }
        }
    }
}
