package pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import components.BottomBar
import components.priorityButton
import kotlinclasses.notDoneMissions

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScaffoldSample(navController: NavHostController) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(
        scaffoldState = scaffoldState,

        content = {
            swipeToDismiss()
        },
        bottomBar = { BottomBar(navController) }
    )
}

@ExperimentalMaterialApi
@Composable
fun swipeToDismiss() {

    LazyColumn(
        modifier = Modifier
            //.padding(top = 500.dp, bottom = 10.dp)
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(notDoneMissions, { it.priority }) { mission ->
            //val mission = notDoneMissions[index]
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
                            notDoneMissions.remove(mission)
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
                            .padding(20.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(color)
                    var direction = dismissState.dismissDirection
                    val target = dismissState.targetValue

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
                                /*
                                Text(
                                    text = "Move to Archive", fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    color = Color.White
                                )*/

                            }

                        }
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
                                /*
                                Text(
                                    text = "Move to Bin",
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.LightGray
                                )*/

                            }
                        }
                    }

                    if (target == targetEnd) {
                        /*
                        println("==================ARCHIVE")
                        println(mission.name)
                        if (!mission.done) {
                            mission.checkMission()
                            println(notDoneMissions.size)
                            println("MAKETOARCHIVE===================")
                        }*/
                        //notDoneMissions.remove(mission)

                    } else if (target == targetStart) {
                        println("DELETEIIIIT")
                    }

                },
                /**** Dismiss Content */
                dismissContent = {


                    Card(
                        elevation = 2.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
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

                            /*Checkbox(
                                checked = state.value,
                                onCheckedChange = {
                                    mission.checkMission()
                                    //mission.done = it
                                    state.value = mission.done
                                },
                                colors = CheckboxDefaults.colors(MaterialTheme.colors.primary)
                            )*/



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
                },
                directions = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd),
            )
        }

    }
    /*** Set Direction to dismiss */

}