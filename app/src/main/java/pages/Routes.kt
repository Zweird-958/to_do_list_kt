package pages

sealed class Routes(val route: String) {
    object Missions : Routes("Missions")
    object Test : Routes("Test")
}