package org.example.api.entities

import kotlinx.serialization.Serializable

enum class Priority {
    Low, Medium, High, Vital
}

@Serializable
data class TaskApiModel(
    val name: String,
    val description: String,
    val priority: Priority
)