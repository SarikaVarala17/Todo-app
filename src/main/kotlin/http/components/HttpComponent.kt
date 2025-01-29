package org.example.http.components

import org.example.http.tasks.*


interface HttpComponent {
    val createTaskHttpRequest : CreateTaskHttpRequest
    val deleteTaskHttpRequest : DeleteTaskHttpRequest
    val getAllTaskHttpRequest : GetAllTasksHttpRequest
    val getTaskByNameHttpRequest : GetTaskByNameHttpRequest
    val updateTaskHttpRequest : UpdateTaskHttpRequest

}
