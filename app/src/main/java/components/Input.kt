package components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillNode
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalAutofill
import androidx.compose.ui.platform.LocalAutofillTree
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.todolist.ui.theme.Purple200

@Composable
fun ClassicInput() {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    OutlinedTextField(
        modifier = Modifier.background(Color.Transparent)
            .border(
                BorderStroke(10.dp, Purple200)),
        value = textState.value,
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
        //trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
        onValueChange = {
            textState.value = it
        },
        label = { Text(text = "Mission name") },
        placeholder = { Text(text = "Mission name") },
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OutlineInput(yourLabel: String, icon: ImageVector, description: String,
                  rememberText: MutableState<String>) {

    TextField(
        value = rememberText.value,
        onValueChange = {
            rememberText.value = it
        },
        leadingIcon = { Icon(imageVector = icon, contentDescription = description) },
        label = { Text(text = yourLabel, color = MaterialTheme.colors.primary) },
        colors = TextFieldDefaults.textFieldColors(textColor = Color.White)

        )
}