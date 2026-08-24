package pe.appmobile.fabricadehistorias.ui.components

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pe.appmobile.fabricadehistorias.ui.theme.VioletaTinta

/** Objetivo táctil principal, ≥120 dp de alto (sección 6 del prompt maestro). */
@Composable
fun BotonGrande(
    texto: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    habilitado: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = habilitado,
        modifier = modifier.defaultMinSize(minHeight = 120.dp),
        colors = ButtonDefaults.buttonColors(containerColor = VioletaTinta)
    ) {
        Text(texto, style = MaterialTheme.typography.titleLarge)
    }
}
