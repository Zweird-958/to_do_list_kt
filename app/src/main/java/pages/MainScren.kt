package com.example.pepalapp.pages

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinclasses.initMission
import pages.AddMissions
import pages.MissionsScaffold
import pages.Routes
import pages.ScaffoldSample

// TODO afficher uniquement quand c'est une page différente

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenMain() {
    initMission()
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = Routes.Missions.route) {


        composable(Routes.Missions.route) {
            MissionsScaffold(navController)
            BackHandler(true) {} // Bloquer le retour en arrière
        }

        composable(Routes.AddMission.route) {
            AddMissions(navController)
            BackHandler(false) {} // Autoriqer le retour en arrière
        }

        composable(Routes.Test.route) {
            ScaffoldSample()
            BackHandler(true) {} // Bloquer le retour en arrière
        }


    }
}
