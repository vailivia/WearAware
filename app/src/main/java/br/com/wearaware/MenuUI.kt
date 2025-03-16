package br.com.wearaware

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.com.wearaware.ui.theme.RosaPink

@Composable
fun MenuUi (
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    onDismissRequest: () -> Unit
     ) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        content = {
            DropdownMenuItem(
                text = {
                    Text(text = "Inicio", color = RosaPink)
                },
                onClick = {}
            )

            DropdownMenuItem(
                text = {
                    Text(text = "Seu Catálogo", color = RosaPink)
                },
                onClick = {}
            )

            DropdownMenuItem(
                text = {
                    Text(text = "Comprinhas", color = RosaPink)
                },
                onClick = {}
            )
            DropdownMenuItem(
                text = {
                    Text(text = "Seu Impacto", color = RosaPink)
                },
                onClick = {}
            )
        }
        )
}