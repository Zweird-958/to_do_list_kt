package pages

import HeaderText
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todolist.MainActivity
import components.BottomBar
import components.allMissions
import components.notDoneMissions
import components.priorityButton
import kotlinclasses.MissionClass
import kotlinclasses.toJson


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MissionsScaffold(navController: NavHostController) {
    Scaffold(
        //topBar = { TopBar(navController, "Calendar") },
        content = {
            Missions(navController)
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Routes.AddMission.route)
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,

                ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "fab icon")
            }
        },
        bottomBar = { BottomBar(navController) }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Missions(navController: NavHostController) {

    //MissionClass("TEST").addToList()

    Column(
        modifier = Modifier
            .fillMaxSize()
    )
    {

        HeaderText("Missions")

        Column(Modifier.fillMaxSize()) {

            showNotDoneMission(navController)

        }


    }

}

@ExperimentalMaterialApi
@Composable
fun showNotDoneMission(navController: NavHostController) {

    LazyColumn(
        modifier = Modifier
            .padding(bottom = 60.dp)
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(notDoneMissions) { mission ->
            val targetEnd = DismissValue.DismissedToEnd
            val targetStart = DismissValue.DismissedToStart
            val dismissState =
                rememberDismissState(
                    initialValue = DismissValue.Default,
                    confirmStateChange = {
                        // Left To Right

                        if (it == targetEnd) {
                            mission.checkMission()
                        }
                        // Right To Left
                        else if (it == targetStart) {
                            mission.deleteToList()
                        }

                        true
                    }
                )
            SwipeToDismiss(
                state = dismissState,
                dismissThresholds = { FractionalThreshold(0.5f) },
                /***  create dismiss alert Background */
                background = {
                    val color = when (dismissState.dismissDirection) {
                        DismissDirection.StartToEnd -> Color.Green
                        DismissDirection.EndToStart -> Color.Red
                        null -> Color.Transparent
                    }
                    val boxModifier =
                        Modifier
                            .fillMaxSize()
                            .padding(start = 20.dp, end = 20.dp, bottom = 7.dp, top = 7.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(color)
                    var direction = dismissState.dismissDirection

                    if (direction == DismissDirection.StartToEnd) {
                        Box(
                            modifier = boxModifier
                        ) {
                            Column(
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(start = 10.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )

                            }

                        }
                        println("===================test")
                        val isSwiped = dismissState.progress.fraction >= 0.3
                        //dismissState.animateTo(targetEnd,tween(1000))
                        //dismissState.reset()
                        println(isSwiped)
                        if (isSwiped) {
                            LaunchedEffect(Unit) {
                                dismissState.reset()
                            }
                            //mission.checkMission()
                        }
                        println()
                    } else {
                        Box(
                            modifier = boxModifier
                        ) {
                            Column(
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(end = 10.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )

                            }
                        }
                    }
                },
                /**** Dismiss Content */
                dismissContent = {


                    Card(
                        elevation = 2.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, bottom = 7.dp, top = 7.dp)
                            .background(Color.Transparent)
                            .clickable { navController.navigate("${Routes.SelectMission.route}/${allMissions.indexOf(mission)}") },

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



                            /*Checkbox(
                                checked = state.value,
                                onCheckedChange = {
                                    mission.checkMission()
                                    //mission.done = it
                                    state.value = mission.done
                                },
                                colors = CheckboxDefaults.colors(MaterialTheme.colors.primary)
                            )*/

                            Text(text = "${mission.name}", modifier = Modifier.width(200.dp))

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
                },
                directions = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd),
            )
        }

    }
    /*** Set Direction to dismiss */


}