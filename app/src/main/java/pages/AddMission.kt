package pages

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import components.OutlineInput
import components.createButton
import components.toDoButton


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
            OutlineInput(
                yourLabel = "Name",
                icon = Icons.Default.Add,
                description = "",
                rememberText = nameState
            )

            var toDo: SnapshotStateList<String> = remember {
                mutableStateListOf()
            }

            val toDoState = remember {
                mutableStateOf("")
            }
            OutlineInput(
                yourLabel = "To Do",
                icon = Icons.Default.Check,
                description = "",
                rememberText = toDoState
            )

            toDoButton(baseText = "Add To Do", toDoName = toDoState, toDo = toDo)



            LazyColumn(modifier = Modifier.height(50.dp)) {
                items(toDo) { itemDo ->
                    Text(itemDo, color = MaterialTheme.colors.secondaryVariant)
                }

            }

            createButton(
                baseText = "Create",
                navController = navController,
                missionName = nameState,
                toDo = toDo
            )
        }


    }


}