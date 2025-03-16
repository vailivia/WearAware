package br.com.wearaware

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.wearaware.screens.*
import br.com.wearaware.ui.theme.WearAwareTheme

class MainActivity : ComponentActivity() {
    private val viewModel: CarbonFootprintViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearAwareTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "Home") {
                        composable(route = "Home") {
                            HomeScreen(navController)
                        }

                        composable(route = "AddItem") {
                            AddItemScreen(
                                navController = navController,
                                onBackToHome = { navController.navigate("Home") },
                                onAddItemClick = { value -> viewModel.addFootprint(value) }
                            )
                        }

                        composable(route = "Catalog") {
                            CatalogScreen(navController)
                        }

                        composable(route = "Shopping") {
                            ShoppingScreen(navController)
                        }

                        composable(route = "Impact") {
                            ImpactScreen(
                                navController = navController,
                                totalCarbonFootprint = viewModel.totalFootprint,
                                totalItems = 10 // Altere para o valor correto
                            )
                        }
                    }
                }
            }
        }
    }
}
