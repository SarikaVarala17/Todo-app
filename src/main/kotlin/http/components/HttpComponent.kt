package org.example.http.components

import dagger.Component
import org.example.data.di.AppModule
import org.example.http.tasks.*


@Component(modules = [AppModule::class])
interface HttpComponent {
    val createTaskHttpRequest: CreateTaskHttpRequest
    val deleteTaskHttpRequest: DeleteTaskHttpRequest
    val getAllTaskHttpRequest: GetAllTasksHttpRequest
    val getTaskByNameHttpRequest: GetTaskByNameHttpRequest
    val updateTaskHttpRequest: UpdateTaskHttpRequest
}
