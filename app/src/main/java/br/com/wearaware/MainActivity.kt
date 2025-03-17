package br.com.wearaware

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.wearaware.screens.AddItemScreen
import br.com.wearaware.screens.CatalogScreen
import br.com.wearaware.screens.HomeScreen
import br.com.wearaware.screens.ImpactScreen
import br.com.wearaware.screens.ShoppingScreen
import br.com.wearaware.ui.theme.CarbonFootprintViewModel
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

                    // Observa os fluxos do ViewModel
                    val totalFootprint = viewModel.totalFootprint.collectAsState().value
                    val totalItems = viewModel.totalItems.collectAsState().value

                    NavHost(navController = navController, startDestination = "Home") {
                        composable(route = "Home") {
                            HomeScreen(
                                navController = navController,
                                totalCarbonFootprint = totalFootprint.toInt(),
                                totalItems = totalItems,
                                onAddItemClick = { navController.navigate("AddItem") }
                            )
                        }

                        composable(route = "AddItem") {
                            AddItemScreen(
                                navController = navController,
                                viewModel = viewModel
                            )
                        }

                        composable(route = "Catalog") {
                            CatalogScreen(
                                navController = navController,
                                totalItems = totalItems,
                                viewModel = viewModel
                            )
                        }

                        composable(route = "Shopping") {
                            ShoppingScreen(
                                navController = navController,
                                viewModel = viewModel
                            )
                        }

                        // Agora passamos o viewModel para a tela de Impact
                        composable(route = "Impact") {
                            ImpactScreen(
                                navController = navController,
                                viewModel = viewModel
                            )
                        }
                    }
                }
            }
        }
    }
}
