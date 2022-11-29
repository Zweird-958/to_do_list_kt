package pages

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinclasses.toClass


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


            LazyColumn(modifier = Modifier.height(50.dp)) {
                if (mission != null) {
                    items(mission.toDo) { itemDo ->
                        Text(itemDo, color = MaterialTheme.colors.secondaryVariant)
                    }
                }

            }

            // Change Informations


        }


    }


}