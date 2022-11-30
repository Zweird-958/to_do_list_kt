package pages

import HeaderText
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import components.allMissions
import components.updateButton
import kotlinclasses.toClass
import missionInformations


@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SelectMission(navController: NavHostController, missionSelected: Int) {

    println("================================")

    println(missionSelected)




    if (missionSelected == null) {
        navController.navigate(Routes.Missions.route)
    }

    val mission = allMissions[missionSelected]

    Column(
        modifier = Modifier
            .fillMaxSize()
    )
    {

        HeaderText(mission.name)

        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(15.dp)
        ) {

            /*val nameState = remember {
                mutableStateOf("")
            }
            OutlineInput(
                yourLabel = "Name",
                icon = Icons.Default.Add,
                description = "",
                rememberText = nameState
            )*/

            val nameState = remember {
                mutableStateOf("")
            }
            if (mission != null) {
                nameState.value = mission.name
            }

            // Missions to do

            var toDo: SnapshotStateList<String> = remember {
                mission?.toDo ?: mutableStateListOf()
            }

            val toDoState = remember {
                mutableStateOf("")
            }

            var links: SnapshotStateList<String> = remember {
                mission?.links ?: mutableStateListOf()
            }

            val linksState = remember {
                mutableStateOf("")
            }

            missionInformations(nameState = nameState, toDo, toDoState, links, linksState)

            // Change Informations

            if (mission != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    updateButton(mission, nameState, toDo, links, navController)

                }
            }


        }


    }


}