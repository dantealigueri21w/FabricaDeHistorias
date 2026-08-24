package pe.appmobile.fabricadehistorias.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val EsquemaDelTaller = lightColorScheme(
    primary = VioletaTinta,
    onPrimary = TextoSobreVioleta,
    secondary = DoradoLaton,
    onSecondary = TintaProfunda,
    tertiary = VerdeMusgo,
    onTertiary = TextoSobreVioleta,
    background = PapelEnvejecido,
    onBackground = TintaProfunda,
    surface = PapelEnvejecido,
    onSurface = TintaProfunda
)

private val TipografiaDelTaller = Typography(
    headlineMedium = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp, lineHeight = 30.sp),
    titleLarge = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 20.sp, lineHeight = 26.sp),
    bodyLarge = TextStyle(fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 22.sp),
    bodyMedium = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp)
)

@Composable
fun FabricaDeHistoriasTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = EsquemaDelTaller,
        typography = TipografiaDelTaller,
        content = content
    )
}
