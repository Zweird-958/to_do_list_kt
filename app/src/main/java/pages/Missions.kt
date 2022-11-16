package pages

import android.os.Build
import android.widget.CheckBox
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import components.BottomBar
import components.priorityButton
import kotlinclasses.notDoneMissions

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MissionsScaffold(navController: NavHostController) {
    Scaffold(
        //topBar = { TopBar(navController, "Calendar") },
        content = {
            Missions()
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Routes.AddMission.route)
            }

            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "fab icon")
            }
        },
        bottomBar = { BottomBar(navController) }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Missions() {

    //MissionClass("TEST").addToList()

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

            Text(text = "Missions")

        }

        Column(Modifier.fillMaxSize()) {

            LazyColumn(
                modifier = Modifier
                    //.padding(top = 500.dp, bottom = 10.dp)
                    .fillMaxSize()
                    .background(MaterialTheme.colors.secondary),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                items(notDoneMissions, {it.priority}) { mission ->
                    //val mission = notDoneMissions[index]

                    // ITEM

                    Card(
                        elevation = 2.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),

                        ) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .background(Color.Transparent)
                                .padding(start = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            val state = remember { mutableStateOf(false) }
                            state.value = mission.done

                            Checkbox(
                                checked = state.value,
                                onCheckedChange = {
                                    mission.checkMission()
                                    state.value = mission.done
                                },
                                colors = CheckboxDefaults.colors(MaterialTheme.colors.primary)
                            )


                            //mission.checkBoxElement()


                            Text(text = "${mission.name}")

                            Column(
                                Modifier
                                    .fillMaxHeight()
                                    .padding(end = 20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceEvenly,
                            ) {

                                priorityButton(
                                    mission = mission,
                                    increase = true,
                                    icon = Icons.Filled.KeyboardArrowUp
                                )
                                priorityButton(
                                    mission = mission,
                                    increase = false,
                                    icon = Icons.Filled.KeyboardArrowDown
                                )

                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                }


            }


        }

    }
}