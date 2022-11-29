import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.OutlineInput
import components.toDoButton

@Composable
fun missionInformations(
    nameState: MutableState<String>,
    toDo: SnapshotStateList<String>,
    toDoState: MutableState<String>,
    links: SnapshotStateList<String>,
    linksState: MutableState<String>
) {

    OutlineInput(
        yourLabel = "Name",
        icon = Icons.Default.Add,
        description = "",
        rememberText = nameState
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp)
    ) {

        OutlineInput(
            yourLabel = "To Do",
            icon = Icons.Default.Check,
            description = "",
            rememberText = toDoState,
            function = {
                toDo.add(toDoState.value)
                toDoState.value = ""
            }
        )

        Spacer(modifier = Modifier.width(20.dp))

        toDoButton(baseText = "Add To Do", toDoName = toDoState, toDo = toDo)
    }

    LazyColumn(
        modifier = Modifier.height(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(toDo) { itemDo ->
            listText(label = itemDo)
            Spacer(modifier = Modifier.height(10.dp))
        }

    }

    // Missions links


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp)
    ) {

        OutlineInput(
            yourLabel = "Links",
            icon = Icons.Default.Search,
            description = "",
            rememberText = linksState,
            function = {
                toDo.add(toDoState.value)
                linksState.value = ""
            }
        )

        Spacer(modifier = Modifier.width(20.dp))

        toDoButton(baseText = "Add Links", toDoName = linksState, toDo = links)
    }

    LazyColumn(
        modifier = Modifier.height(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(links) { itemDo ->
            listText(label = itemDo)

            Spacer(modifier = Modifier.height(10.dp))
        }

    }
}