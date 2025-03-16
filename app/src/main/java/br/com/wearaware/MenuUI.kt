package br.com.wearaware

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import br.com.wearaware.ui.theme.RosaPink

@Composable
fun MenuUi(
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    onDismissRequest: () -> Unit,
    navController: NavController
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {
        DropdownMenuItem(
            text = { Text(text = "Início", color = RosaPink) },
            onClick = {
                navController.navigate("Home") { popUpTo("Home") { inclusive = true } }
                onDismissRequest()
            }
        )

        DropdownMenuItem(
            text = { Text(text = "Adicionar Item", color = RosaPink) },
            onClick = {
                navController.navigate("AddItem") { popUpTo("AddItem") { inclusive = true } }
                onDismissRequest()
            }
        )

        DropdownMenuItem(
            text = { Text(text = "Seu Catálogo", color = RosaPink) },
            onClick = {
                navController.navigate("Catalog") { popUpTo("Catalog") { inclusive = true } }
                onDismissRequest()
            }
        )

        DropdownMenuItem(
            text = { Text(text = "Comprinhas", color = RosaPink) },
            onClick = {
                navController.navigate("Shopping") { popUpTo("Shopping") { inclusive = true } }
                onDismissRequest()
            }
        )

        DropdownMenuItem(
            text = { Text(text = "Seu Impacto", color = RosaPink) },
            onClick = {
                navController.navigate("Impact") { popUpTo("Impact") { inclusive = true } }
                onDismissRequest()
            }
        )
    }
}
