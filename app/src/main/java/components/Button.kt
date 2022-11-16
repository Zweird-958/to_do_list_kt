package components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todolist.ui.theme.Green
import com.example.todolist.ui.theme.Purple200
import kotlinclasses.MissionClass
import pages.Routes

@Composable
fun createButton(baseText: String, navController: NavHostController, missionName: MutableState<String>) {
    Button(
        //modifier = Modifier.width(15.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary, contentColor = MaterialTheme.colors.onSecondary),
        onClick = {
            MissionClass(missionName.value).addToList()
            navController.navigate(Routes.Missions.route)
            //MakeToast(resultValidation)
        },
        // Uses ButtonDefaults.ContentPadding by default
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 12.dp,
            end = 20.dp,
            bottom = 12.dp
        )
    ) {
        Text(baseText)
    }
}

@Composable
fun priorityButton(mission: MissionClass, increase: Boolean, icon: ImageVector){
    Button(onClick = { mission.changePriority(increase) },
        //enabled = false,
        border = BorderStroke(0.dp,Color.Transparent),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),

    ) {
        Image(
            imageVector = icon,
            contentDescription =" Cart button icon",
            modifier = Modifier
                .size(20.dp),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.secondaryVariant)
        )

    }
}