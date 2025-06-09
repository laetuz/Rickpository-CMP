package id.neotica.rickpository.domain.model.apiresult

import kotlinx.serialization.Serializable

@Serializable
data class NotFoundResponse(
    val error: String
)
