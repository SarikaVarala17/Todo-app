package org.example.api

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.http.*
import org.example.domain.entities.Task
import org.example.domain.repository.TaskRepository

class TodoApi(
    private val taskRepository: TaskRepository
) {
    suspend fun getAllTasks(call: ApplicationCall) {
        val tasks = taskRepository.allTasks()
        call.respond(HttpStatusCode.OK, tasks)
    }

    suspend fun getTaskByName(call: ApplicationCall) {
        val name = call.parameters["name"]
        if (name.isNullOrEmpty()) {
            call.respond(HttpStatusCode.BadRequest, "Task name is required")
            return
        }
        val task = taskRepository.taskByName(name)
        if (task == null) {
            call.respond(HttpStatusCode.NotFound, "Task not found")
        } else {
            call.respond(HttpStatusCode.OK, task)
        }
    }

    suspend fun createTask(call: ApplicationCall) {
        try {
            val task = call.receive<Task>()
            taskRepository.addTask(task)
            call.respond(HttpStatusCode.Created, "Task created successfully")
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, "Invalid task data: ${e.message}")
        }
    }

    suspend fun updateTask(call: ApplicationCall) {
        val id = call.parameters["id"]
        if (id.isNullOrEmpty()) {
            call.respond(HttpStatusCode.BadRequest, "Task ID is required")
            return
        }
        try {
            val task = call.receive<Task>()
            taskRepository.removeTask(id)
            taskRepository.addTask(task)
            call.respond(HttpStatusCode.OK, "Task updated successfully")
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, "Invalid task data: ${e.message}")
        }
    }

    suspend fun deleteTask(call: ApplicationCall) {
        val id = call.parameters["id"]
        if (id.isNullOrEmpty()) {
            call.respond(HttpStatusCode.BadRequest, "Task ID is required")
            return
        }
        val success = taskRepository.removeTask(id)
        if (success) {
            call.respond(HttpStatusCode.OK, "Task deleted successfully")
        } else {
            call.respond(HttpStatusCode.NotFound, "Task not found")
        }
    }

    suspend fun addTask(call: ApplicationCall) {
        try {
            val task = call.receive<Task>()
            taskRepository.addTask(task)
            call.respond(HttpStatusCode.OK, "Task added successfully")
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, "Invalid task data: ${e.message}")
        }
    }
}
