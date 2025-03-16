package br.com.wearaware.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.wearaware.CarbonFootprintViewModel
import br.com.wearaware.MenuUi
import br.com.wearaware.R
import br.com.wearaware.ui.theme.RosaClaro
import br.com.wearaware.ui.theme.RosaPink
import br.com.wearaware.ui.theme.Verde

@Composable
fun AddItemScreen(
    navController: NavController,
    onBackToHome: () -> Unit,
    onAddItemClick: (Double) -> Unit,
    viewModel: CarbonFootprintViewModel
) {
    var menuExpanded by remember { mutableStateOf(false) }
    var tipoSelecionado by remember { mutableStateOf("Selecione") }
    var materialSelecionado by remember { mutableStateOf("Selecione") }

    val tiposPeca = mapOf(
        "Selecione" to 0,
        "Calça Jeans" to 33,
        "Camiseta" to 5,
        "Vestido" to 12
    )

    val materiais = mapOf(
        "Selecione" to 0.0,
        "Algodão" to 1.0,
        "Poliéster" to 1.5
    )

    val pegadaCarbono = tiposPeca[tipoSelecionado]!! * materiais[materialSelecionado]!!

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RosaClaro)
    ) {
        Column {
            // Menu
            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = { menuExpanded = !menuExpanded },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.menu),
                        contentDescription = "Menu",
                        tint = Color.Black,
                        modifier = Modifier.size(32.dp)
                    )
                }

                MenuUi(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false },
                    navController = navController
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Título
                Text(
                    text = "Adicionar Item",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = RosaPink,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Imagem
                Image(
                    painter = painterResource(id = R.drawable.dress_on_hanger),
                    contentDescription = "Imagem do item",
                    modifier = Modifier.size(180.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Texto Pegada de Carbono
                Text(
                    text = "Pegada de Carbono do Item",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Box Pegada de Carbono
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = String.format("%.1f kg CO₂", pegadaCarbono),
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Box Seleção
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, Color.Black, shape = RoundedCornerShape(24.dp))
                        .background(Verde, shape = RoundedCornerShape(20.dp))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Selecione",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Dropdown Tipo de Peça
                        DropdownMenuSelector(
                            label = "Tipo de Peça",
                            options = tiposPeca.keys.toList(),
                            selectedOption = tipoSelecionado,
                            onOptionSelected = { tipoSelecionado = it }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Dropdown Material
                        DropdownMenuSelector(
                            label = "Material",
                            options = materiais.keys.toList(),
                            selectedOption = materialSelecionado,
                            onOptionSelected = { materialSelecionado = it }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Botão Salvar Novo Item
                Button(
                    onClick = {
                        viewModel.addFootprint(pegadaCarbono)
                        onBackToHome()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = RosaPink),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, Color.Black, shape = RoundedCornerShape(24.dp))
                        .background(RosaPink, shape = RoundedCornerShape(24.dp))
                ) {
                    Text(text = "Salvar Novo Item", color = Color.Black)
                }
            }
        }
    }
}

// Componente reutilizável para seleção Dropdown
@Composable
fun DropdownMenuSelector(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(10.dp))
                .padding(8.dp)
                .clickable { expanded = true },
            contentAlignment = Alignment.Center
        ) {
            Text(text = selectedOption, fontSize = 16.sp, color = Color.Black)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
