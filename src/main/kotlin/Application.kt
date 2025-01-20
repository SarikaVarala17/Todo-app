package com.example

import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.routing.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import io.ktor.server.response.respondText


val todos = mutableListOf<TodoItem>()
var nextId = 1

@Serializable
data class TodoItem(val id: Int = 0, val title: String, val completed: Boolean)

fun Application.module() {

    install(ContentNegotiation) {
        json()
    }

    routing {
        get("/") {
            call.respondText("To-Do List API")
        }


        route("/todos") {


            get {
                call.respond(HttpStatusCode.OK, todos)
            }


            post {
                val todo = call.receive<TodoItem>()
                val newTodo = todo.copy(id = nextId++)
                todos.add(newTodo)
                call.respond(HttpStatusCode.Created, newTodo)
            }
        }


        route("/todos/{id}") {


            get {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id != null) {
                    val todo = todos.find { it.id == id }
                    if (todo != null) {
                        call.respond(HttpStatusCode.OK, todo)
                    } else {
                        call.respond(HttpStatusCode.NotFound, "Todo not found")
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Invalid ID format")
                }
            }


            put {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id != null) {
                    val updatedTodo = call.receive<TodoItem>()
                    val index = todos.indexOfFirst { it.id == id }
                    if (index != -1) {
                        todos[index] = updatedTodo.copy(id = id)
                        call.respond(HttpStatusCode.OK, todos[index])
                    } else {
                        call.respond(HttpStatusCode.NotFound, "Todo not found")
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Invalid ID format")
                }
            }


            delete {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id != null) {
                    val index = todos.indexOfFirst { it.id == id }
                    if (index != -1) {
                        todos.removeAt(index)
                        call.respond(HttpStatusCode.NoContent)
                    } else {
                        call.respond(HttpStatusCode.NotFound, "Todo not found")
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Invalid ID format")
                }
            }
        }
    }
}

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}
