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

    // Mapeamento do tipo de compra para um fator médio
    val tipoCompraMap = mapOf(
        "Selecione" to 0.0,
        "Compra Presencial" to 1.25,     // média ~ (0.5 a 2)
        "Envio Nacional" to 3.5,        // média ~ (2 a 5)
        "Envio Internacional" to 20.0   // média ~ (10 a 30)
    )

    val tiposCompra = tipoCompraMap.keys.toList()

    // Estado para o dropdown
    var tipoSelecionado by remember { mutableStateOf(tiposCompra[0]) }

    // Estado para o campo de quantidade (TextField)
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

    // Cálculo da pegada de carbono
    val quantidade = quantidadeText.toIntOrNull() ?: 1
    val fator = tipoCompraMap[tipoSelecionado] ?: 0.0
    val pegadaCarbonoCalculada = quantidade * fator

    // Só habilita o botão se a quantidade não estiver vazia e o tipo de compra for diferente de "Selecione"
    val isButtonEnabled = quantidadeText.isNotEmpty() && (tipoSelecionado != "Selecione")

    Scaffold(
        topBar = {
            // Barra superior com menu (igual às outras telas)
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
            // Título "Comprinhas"
            Text(
                text = "Comprinhas",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = RosaPink,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Imagem do carrinho/compras
            Image(
                painter = painterResource(id = R.drawable.sacola), // Ajuste para o seu ícone
                contentDescription = "Ícone de compras",
                modifier = Modifier.size(180.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Texto "Calcule o impacto para chegar até você"
            Text(
                text = "Calcule o impacto para chegar até você",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Exibe a pegada de carbono calculada (sem arredondar aqui)
            Text(
                text = "${"%.1f".format(pegadaCarbonoCalculada)} Kg CO₂",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Card principal
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
                    // Texto "Selecione"
                    Text(
                        text = "Selecione",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Campo: Quantidade de peças
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

                    // Dropdown: Tipo de compra
                    DropdownMenuSelectorCompra(
                        label = "Tipo de compra:",
                        options = tiposCompra,
                        selectedOption = tipoSelecionado,
                        onOptionSelected = { tipoSelecionado = it }
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botão para salvar novo item
            Button(
                onClick = {
                    // Aqui formatamos com 2 casas após a vírgula ANTES de salvar
                    val pegadaDuasCasas = String.format("%.2f", pegadaCarbonoCalculada).toDouble()

                    viewModel.addItem(
                        CarbonFootprintItem(
                            description = "Compras: $quantidade pç(s) - $tipoSelecionado",
                            footprintValue = pegadaDuasCasas, // <-- Armazena com 2 casas
                            source = "Shopping",
                            quantity = quantidade
                        )
                    )
                    // Navega para HomeScreen (ou onde desejar)
                    navController.navigate("Home")
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                border = BorderStroke(2.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF35695) // Rosa #F35695
                ),
                enabled = isButtonEnabled
            ) {
                Text(text = "Salvar Novo Item", color = Color.Black)
            }
        }
    }
}

// Dropdown específico para o "Tipo de compra"
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
