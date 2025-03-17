@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import br.com.wearaware.CarbonFootprintItem
import br.com.wearaware.MenuUi
import br.com.wearaware.R
import br.com.wearaware.ui.theme.CarbonFootprintViewModel
import br.com.wearaware.ui.theme.RosaClaro
import br.com.wearaware.ui.theme.RosaPink

@Composable
fun ShoppingScreen(
    navController: NavController,
    viewModel: CarbonFootprintViewModel
) {
    var menuExpanded by remember { mutableStateOf(false) }

    val tipoCompraMap = mapOf(
        "Selecione" to 0.0,
        "Compra Presencial" to 1.25,
        "Envio Nacional" to 3.5,
        "Envio Internacional" to 20.0
    )

    val tiposCompra = tipoCompraMap.keys.toList()

    var tipoSelecionado by remember { mutableStateOf(tiposCompra[0]) }

    var quantidadeText by remember { mutableStateOf("") }

    fun atualizarQuantidade(novoTexto: String) {
        if (novoTexto.isEmpty()) {
            quantidadeText = ""
            return
        }
        val valor = novoTexto.toIntOrNull()
        if (valor != null && valor in 1..30) {
            quantidadeText = novoTexto
        }
    }

    val quantidade = quantidadeText.toIntOrNull() ?: 1
    val fator = tipoCompraMap[tipoSelecionado] ?: 0.0
    val pegadaCarbonoCalculada = quantidade * fator


    val isButtonEnabled = quantidadeText.isNotEmpty() && (tipoSelecionado != "Selecione")

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
                text = "Comprinhas",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = RosaPink,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.sacola),
                contentDescription = "Ícone de compras",
                modifier = Modifier.size(180.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Calcule o impacto para chegar até você",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${"%.1f".format(pegadaCarbonoCalculada)} Kg CO₂",
                fontSize = 34.sp,
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
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Selecione",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Quantidade de peças:",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = quantidadeText,
                        onValueChange = { atualizarQuantidade(it) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        singleLine = true,
                        textStyle = LocalTextStyle.current.copy(color = Color.Black),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    DropdownMenuSelectorCompra(
                        label = "Tipo de compra:",
                        options = tiposCompra,
                        selectedOption = tipoSelecionado,
                        onOptionSelected = { tipoSelecionado = it }
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    val pegadaDuasCasas = String.format("%.2f", pegadaCarbonoCalculada).toDouble()

                    viewModel.addItem(
                        CarbonFootprintItem(
                            description = "Compras: $quantidade pç(s) - $tipoSelecionado",
                            footprintValue = pegadaDuasCasas,
                            source = "Shopping",
                            quantity = quantidade
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
                Text(text = "Salvar Novo Item", color = Color.Black)
            }
        }
    }
}

@Composable
fun DropdownMenuSelectorCompra(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, fontWeight = FontWeight.Bold, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
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
