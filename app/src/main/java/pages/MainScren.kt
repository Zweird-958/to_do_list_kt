package com.example.pepalapp.pages

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pages.MissionsScaffold
import pages.Routes

// TODO afficher uniquement quand c'est une page différente

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenMain() {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = Routes.Missions.route) {


        composable(Routes.Missions.route) {
            MissionsScaffold(navController)
            BackHandler(true) {} // Bloquer le retour en arrière
        }


    }
}
