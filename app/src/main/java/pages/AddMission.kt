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
import components.createButton
import missionInformations


@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddMissions(navController: NavHostController) {

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

            Text(text = "Add New Mission", color = Color.White)

        }

        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(15.dp)
        ) {

            val nameState = remember {
                mutableStateOf("")
            }

            // Missions to do

            var toDo: SnapshotStateList<String> = remember {
                mutableStateListOf()
            }

            val toDoState = remember {
                mutableStateOf("")
            }

            var links: SnapshotStateList<String> = remember {
                mutableStateListOf()
            }

            val linksState = remember {
                mutableStateOf("")
            }

            missionInformations(nameState = nameState,toDo,toDoState,links,linksState)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                createButton(
                    baseText = "Create",
                    navController = navController,
                    missionName = nameState,
                    toDo = toDo,
                    links = links
                )

            }


        }
    }


}

