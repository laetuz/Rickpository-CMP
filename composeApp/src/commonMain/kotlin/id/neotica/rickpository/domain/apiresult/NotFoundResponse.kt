package id.neotica.rickpository.domain.apiresult

import kotlinx.serialization.Serializable

@Serializable
data class NotFoundResponse(
    val error: String
)
