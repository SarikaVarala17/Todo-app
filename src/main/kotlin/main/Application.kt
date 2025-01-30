package org.example.main

import io.ktor.server.application.*
import org.example.api.configureSerialization
import org.example.data.db.configureDatabases
import org.example.di.DaggerAppComponent
import org.example.http.components.DaggerHttpComponent

import routing.configureRouting

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
        val appComponent = DaggerAppComponent.create()

        val httpComponent = DaggerHttpComponent.create()
        configureSerialization()
        configureDatabases()
        configureRouting(httpComponent)
}
