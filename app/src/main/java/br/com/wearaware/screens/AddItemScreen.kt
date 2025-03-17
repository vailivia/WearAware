package br.com.wearaware.screens

import androidx.compose.foundation.BorderStroke
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
import br.com.wearaware.CarbonFootprintItem
import br.com.wearaware.MenuUi
import br.com.wearaware.R
import br.com.wearaware.ui.theme.CarbonFootprintViewModel
import br.com.wearaware.ui.theme.RosaClaro
import br.com.wearaware.ui.theme.RosaPink
import androidx.compose.foundation.Image

@Composable
fun DropdownMenuSelector(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(24.dp))
                .clickable { expanded = true }
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = selectedOption,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option, color = Color.Black) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun AddItemScreen(
    navController: NavController,
    viewModel: CarbonFootprintViewModel
) {
    var menuExpanded by remember { mutableStateOf(false) }

    val tiposPecaMap = mapOf(
        "Selecione" to 0,
        "Calça Jeans" to 33,
        "Camiseta" to 5,
        "Vestido" to 12,
        "Jaqueta" to 20,
        "Shorts" to 8,
        "Blusa de Moletom" to 18,
        "Saia" to 10,
        "Suéter" to 15,
        "Bermuda" to 9,
        "Macacão" to 14
    )

    val materiaisMap = mapOf(
        "Selecione" to 0.0,
        "Algodão" to 1.0,
        "Poliéster" to 1.5,
        "Lã" to 1.2,
        "Nylon" to 1.3,
        "Couro" to 2.0,
        "Viscose" to 1.1
    )

    val tiposPeca = tiposPecaMap.keys.toList()
    val materiais = materiaisMap.keys.toList()

    var tipoSelecionado by remember { mutableStateOf(tiposPeca[0]) }
    var materialSelecionado by remember { mutableStateOf(materiais[0]) }

    val pegadaCarbonoCalculada =
        (tiposPecaMap[tipoSelecionado] ?: 0) * (materiaisMap[materialSelecionado] ?: 0.0)

    val isButtonEnabled = (tipoSelecionado != "Selecione") && (materialSelecionado != "Selecione")

    Scaffold(
        topBar = {
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
        },
        containerColor = RosaClaro
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Adicionar Item",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = RosaPink,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Image(
                painter = painterResource(id = R.drawable.dress_on_hanger),
                contentDescription = "Imagem do item",
                modifier = Modifier.size(180.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Pegada de Carbono do Item",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "${pegadaCarbonoCalculada.toInt()} Kg CO₂",
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color.Black, RoundedCornerShape(24.dp)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFDDE255)),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    Text(
                        text = "Selecione",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    DropdownMenuSelector(
                        label = "Tipo de Peça:",
                        options = tiposPeca,
                        selectedOption = tipoSelecionado,
                        onOptionSelected = { tipoSelecionado = it }
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    DropdownMenuSelector(
                        label = "Material:",
                        options = materiais,
                        selectedOption = materialSelecionado,
                        onOptionSelected = { materialSelecionado = it }
                    )
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            Button(
                onClick = {
                    viewModel.addItem(
                        CarbonFootprintItem(
                            description = "$tipoSelecionado - $materialSelecionado",
                            footprintValue = pegadaCarbonoCalculada,
                            source = "AddItem",
                            quantity = 1
                        )
                    )
                    navController.navigate("Home")
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                border = BorderStroke(2.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF35695)
                ),
                enabled = isButtonEnabled
            ) {
                Text(
                    text = "Salvar Novo Item",
                    color = Color.Black
                )
            }
        }
    }
}
