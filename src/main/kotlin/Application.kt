package org.example

import org.example.domain.di.DaggerAppComponent
import io.ktor.server.application.*
import org.example.api.configureSerialization
import org.example.api.configureRouting
import org.example.data.db.configureDatabases
import org.example.domain.di.AppComponent

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val appComponent : AppComponent= DaggerAppComponent.create()
    val repository = appComponent.getTaskRepository()

    configureSerialization()
    configureDatabases()
    configureRouting(repository)
}