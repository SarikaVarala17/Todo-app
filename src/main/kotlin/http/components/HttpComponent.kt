package org.example.http.components

import dagger.Component
import org.example.data.di.AppModule
import org.example.http.HttpModule
import org.example.http.tasks.*
import javax.inject.Singleton

@Component(modules = [HttpModule::class, AppModule::class])
interface HttpComponent {
    val createTaskHttpRequest: CreateTaskHttpRequest
    val deleteTaskHttpRequest: DeleteTaskHttpRequest
    val getAllTaskHttpRequest: GetAllTasksHttpRequest
    val getTaskByNameHttpRequest: GetTaskByNameHttpRequest
    val updateTaskHttpRequest: UpdateTaskHttpRequest
}
