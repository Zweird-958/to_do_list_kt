package pages

sealed class Routes(val route: String) {
    object Missions : Routes("Missions")
    object AddMission : Routes("AddMission")
    object MissionsDone : Routes("MissionsDone")
    object SelectMission : Routes("SelectMission")
}