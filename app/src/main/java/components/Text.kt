import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun listText(label: String) {
    val uriHandler = LocalUriHandler.current
    Text(
        label,
        color = MaterialTheme.colors.secondaryVariant,
        modifier = Modifier
            .width(300.dp)
            .border(BorderStroke(2.dp, MaterialTheme.colors.primary))
            .padding(10.dp)
            .clickable {
                uriHandler.openUri(label)
            },
        textAlign = TextAlign.Center,
    )
}