package components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
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

@Composable
fun BottomBar(navController: NavHostController) {
    val currentRoute = navController.currentDestination?.route
    val test = navController.graph
    val test2 = navController.backQueue
    BottomAppBar () {

        BottomNavigationItem(icon = {Column(
                verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = Icons.Default.AccountBox,"")
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
            Icon(imageVector = Icons.Default.AccountBox,"")
        }
        },
            //label = { /*Text(text = "Account") */},
            selected = (currentRoute == Routes.Swipe.route),
            onClick = {
                navController.navigate(Routes.Swipe.route)
            })



    }
}

