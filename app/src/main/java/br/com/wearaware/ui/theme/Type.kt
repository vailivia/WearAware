package br.com.wearaware.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import br.com.wearaware.R // Importe seu arquivo R

// Define a fonte "QuickSand"
val QuickSand = FontFamily(
    Font(R.font.quicksand_regular, FontWeight.Normal), // Carrega a fonte regular
    Font(R.font.quicksand_bold, FontWeight.Bold)      // Carrega a fonte bold (se tiver)
    // Adicione outros estilos (Italic, etc.) se necessário
)

// Define os estilos de texto usando a fonte "QuickSand"
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = QuickSand, // Use a FontFamily definida acima
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = QuickSand,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = QuickSand,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)