package id.neotica.rickpository.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    @SerialName("main")
    data object MainScreen : Screen()
    @Serializable
    @SerialName("second")
    data object SecondScreen: Screen()
    @Serializable
    data object CharactersScreen: Screen()
}