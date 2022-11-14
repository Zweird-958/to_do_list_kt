package pages

import android.os.Build
import android.widget.CheckBox
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.navigation.NavHostController
import com.example.todolist.ui.theme.Purple200
import components.BottomBar
import kotlinclasses.MissionClass
import kotlinclasses.allMissions

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MissionsScaffold(navController: NavHostController){
    Scaffold(
        //topBar = { TopBar(navController, "Calendar") },
        content = {
            Missions()
        },
        bottomBar = { BottomBar(navController) }
    )
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Missions(){

    Column(modifier = Modifier
        .fillMaxSize())
    {


        Column(modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(top = 20.dp, end = 20.dp)
        ) {

            Text(text = "Hello")
            
        }

        Column(Modifier.fillMaxSize()) {

            Button(modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary),
                onClick = {
                val newMission = MissionClass("Todo ${allMissions.size}")
                newMission.addToList()
            }) {
                Text(text = "New Mission")
            }
            LazyColumn(modifier = Modifier
                //.padding(top = 500.dp, bottom = 10.dp)
                .fillMaxSize()
                .background(MaterialTheme.colors.secondary),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {



                items(allMissions.size) { index ->
                    val mission = allMissions[index]
                    Card(
                        elevation = 10.dp,
                        modifier = Modifier.fillMaxWidth(),
                        border = BorderStroke(10.dp, Purple200),
                    ){
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colors.primaryVariant),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {

                            val checkedState = remember { mutableStateOf(false) }
                            Checkbox(checked = checkedState.value,
                                onCheckedChange = {
                                    mission.checkMission()
                                    checkedState.value = it
                                                  },
                                colors = CheckboxDefaults.colors(MaterialTheme.colors.primary))


                                
                            Text(text = "Mission ${mission.name}  Priority : ${mission.priority}")

                            Column(Modifier.fillMaxHeight(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center) {

                                Button(onClick = {
                                    mission.changePriority(true)
                                }) {
                                    Text(text = "UP")
                                }
                                Button(onClick = {
                                    mission.changePriority(false)
                                }) {
                                    Text(text = "DOWN")
                                }

                            }
                        }
                    }
                }



            }



        }

    }
}