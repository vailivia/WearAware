package br.com.wearaware.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


private val WearAwareColorScheme = lightColorScheme(
    background = RosaClaro,
    primary = RosaPink,
    secondary = Verde,
    tertiary = Laranja
)


@Composable
fun WearAwareTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = WearAwareColorScheme,
        typography = Typography, // Certifique-se de que Typography está configurado
        content = content
    )
}