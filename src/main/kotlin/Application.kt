package org.example


import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.example.http.HttpComponent
import org.example.api.TodoApi
import org.example.domain.di.DaggerAppComponent
import org.example.api.configureSerialization
import org.example.data.db.configureDatabases


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val appComponent = DaggerAppComponent.create()
    val taskRepository = appComponent.getTaskRepository()
    val todoApi = TodoApi(taskRepository)
    val httpComponent = HttpComponent(todoApi)
    configureSerialization()
    configureDatabases()

    routing {
        httpComponent.setupRoutes(this)
    }
}
