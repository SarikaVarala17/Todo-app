package org.example.api.mappers

import org.example.api.entities.TaskApiModel
import org.example.domain.entities.Task
import org.example.api.entities.Priority as ApiPriority
import org.example.domain.entities.Priority as DomainPriority
import javax.inject.Inject

class TaskMapper @Inject constructor() {
    fun toDomain(apiModel: TaskApiModel) = Task(
        name = apiModel.name,
        description = apiModel.description,
        priority = apiPriorityToDomain(apiModel.priority)
    )

    fun toApi(domainModel: Task) = TaskApiModel(
        name = domainModel.name,
        description = domainModel.description,
        priority = domainPriorityToApi(domainModel.priority)
    )

    private fun apiPriorityToDomain(apiPriority: ApiPriority): DomainPriority {
        return when (apiPriority) {
            ApiPriority.Low -> DomainPriority.Low
            ApiPriority.Medium -> DomainPriority.Medium
            ApiPriority.High -> DomainPriority.High
            ApiPriority.Vital -> DomainPriority.Vital
        }
    }

    private fun domainPriorityToApi(domainPriority: DomainPriority): ApiPriority {
        return when (domainPriority) {
            DomainPriority.Low -> ApiPriority.Low
            DomainPriority.Medium -> ApiPriority.Medium
            DomainPriority.High -> ApiPriority.High
            DomainPriority.Vital -> ApiPriority.Vital
        }
    }
}
