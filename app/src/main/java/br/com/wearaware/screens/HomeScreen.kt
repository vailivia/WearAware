package br.com.wearaware.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.wearaware.MenuUi
import br.com.wearaware.R
import br.com.wearaware.ui.theme.Laranja
import br.com.wearaware.ui.theme.QuickSand
import br.com.wearaware.ui.theme.RosaClaro
import br.com.wearaware.ui.theme.RosaPink
import br.com.wearaware.ui.theme.Verde

@Composable
fun HomeScreen(navController: NavController,
               totalCarbonFootprint: Int,
               totalItems: Int,
               onAddItemClick: () -> Unit) {
    var menuExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RosaClaro)
    ) {
        Column {
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
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "WearAware",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = RosaPink,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = "Porque Consumo consciente nunca sai de moda.",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.Black
            )

// Imagem
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.online_closet), // Altere para o nome do seu PNG
                    contentDescription = "Imagem principal",
                    modifier = Modifier.size(250.dp),
                    tint = Color.Unspecified // Mantém a cor original da imagem
                )
            }

// Box Pegada de Carbono
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color.Black, shape = RoundedCornerShape(24.dp))
                    .background(Verde, shape = RoundedCornerShape(24.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Pegada de carbono total",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(10.dp).padding(5.dp))

                    Text(
                        text = "$totalCarbonFootprint kg CO₂",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

// Box Peças Cadastradas
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color.Black, shape = RoundedCornerShape(24.dp))
                    .background(Laranja, shape = RoundedCornerShape(24.dp))
                    .padding(16.dp),// Correção: use padding em vez de margin
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Peças cadastradas",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(10.dp).padding(5.dp))

                    Text(
                        text = "$totalItems",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }

// Botão Adicionar Item
            Button(
                onClick = onAddItemClick,
                colors = ButtonDefaults.buttonColors(containerColor = RosaPink), // Correção: use containerColor
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .border(2.dp, Color.Black, shape = RoundedCornerShape(24.dp))
                    .background(RosaPink, shape = RoundedCornerShape(24.dp))
            ) {
                Text(
                    text = "Adicionar Item",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
        }
    }
}