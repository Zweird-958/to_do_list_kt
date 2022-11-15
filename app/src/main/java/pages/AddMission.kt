package pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import components.OutlineInput
import components.createButton


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddMissions(navController: NavHostController){

    Column(modifier = Modifier
        .fillMaxSize())
    {


        Column(modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(top = 40.dp, bottom = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(text = "Add New Mission")
            
        }

        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(15.dp)) {

            val nameState = remember {
                mutableStateOf("")
            }
            OutlineInput(yourLabel = "Name", icon = Icons.Default.Add, description = "", rememberText = nameState)

            createButton(baseText = "Create", navController = navController, missionName = nameState)
            }





    }



}