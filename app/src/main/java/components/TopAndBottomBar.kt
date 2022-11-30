package components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.todolist.ui.theme.Gray200
import pages.Routes


@Composable
fun TopBar(navController: NavHostController, label:String) {
    TopAppBar(
            title = {
                Text(text = label)
            },
            navigationIcon = {
                IconButton(onClick = {navController.navigateUp() }) {
                    Icon(Icons.Filled.ArrowBack, "backIcon")
                }
            },
            backgroundColor = colors.background,
            /*contentColor = Teal200,*/
            //elevation = 5.dp,
            //modifier = Modifier.padding(30.dp)
    )
}

fun getColor(currentRoute: String?, selectRoute: String): Color {
    return if (currentRoute == selectRoute) Gray200 else Color.White
}

@Composable
fun BottomBar(navController: NavHostController) {
    val currentRoute = navController.currentDestination?.route
    val test = navController.graph
    val test2 = navController.backQueue
    BottomAppBar (backgroundColor = MaterialTheme.colors.primary) {

        BottomNavigationItem(icon = {Column(
                verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = Icons.Default.Close,"", tint = getColor(currentRoute,Routes.Missions.route))
        }
        },
            //label = { /*Text(text = "Account") */},
            selected = (currentRoute == Routes.Missions.route),
            onClick = {
                println("=================NAV===========")
                println(test)
                println(test2)
                navController.navigate(Routes.Missions.route)
                println("=================NAV===========")
                println(test)
                println(test2)
            })

        BottomNavigationItem(icon = {Column(
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = Icons.Default.Check,"", tint = getColor(currentRoute,Routes.MissionsDone.route))
        }
        },
            //label = { /*Text(text = "Account") */},
            selected = (currentRoute == Routes.MissionsDone.route),
            onClick = {
                navController.navigate(Routes.MissionsDone.route)
            })



    }
}

