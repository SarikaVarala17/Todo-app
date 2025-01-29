package routing

import io.ktor.server.routing.*
import io.ktor.server.application.*
import org.example.http.components.HttpComponent

fun Application.configureRouting(httpComponent: HttpComponent) {
    routing {
        // Get all tasks
        get("/tasks") {
            httpComponent.getAllTaskHttpRequest.handleGetAllTasks(call)
        }

        // Get task by name
        get("/tasks/{taskName}"){
            httpComponent.getTaskByNameHttpRequest.handleGetTaskByName(call)
        }

        // Delete task by name
        delete("/tasks/{taskName}") {
            httpComponent.deleteTaskHttpRequest.handleDeleteTask(call)
        }

        // Update task by name
        put("/tasks/{taskName}") {
            httpComponent.updateTaskHttpRequest.handleUpdateTask(call)
        }

        // Create a new task
        post("/tasks") {
            httpComponent.createTaskHttpRequest.handleCreateTask(call)
        }
    }
}
