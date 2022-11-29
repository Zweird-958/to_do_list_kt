package pages

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
import components.updateButton
import kotlinclasses.toClass
import missionInformations


@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SelectMission(navController: NavHostController, missionSelected: String?) {

    if (missionSelected == null) {
        navController.navigate(Routes.Missions.route)
    }

    val mission = toClass(missionSelected)

    Column(
        modifier = Modifier
            .fillMaxSize()
    )
    {


        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            if (missionSelected != null && mission != null) {
                Text(text = mission.name, color = Color.White)
            }

        }

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

            missionInformations(nameState = nameState,toDo,toDoState,links,linksState)

            // Change Informations

            if (mission != null) {
                updateButton(mission,nameState,toDo,links,navController)
            }


        }


    }


}