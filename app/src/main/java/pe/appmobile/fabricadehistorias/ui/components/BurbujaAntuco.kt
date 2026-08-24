package pe.appmobile.fabricadehistorias.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pe.appmobile.fabricadehistorias.ui.theme.PapelEnvejecido
import pe.appmobile.fabricadehistorias.ui.theme.TintaProfunda
import pe.appmobile.fabricadehistorias.ui.theme.VioletaTinta

/** El aviso o comentario de Antuco. Nunca aparece mientras el niño escribe. */
@Composable
fun BurbujaAntuco(mensaje: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(PapelEnvejecido, RoundedCornerShape(16.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MarcadorIlustracion(
            etiqueta = "Antuco",
            colorFondo = VioletaTinta,
            descripcionAccesible = null,
            modifier = Modifier.size(56.dp)
        )
        Text(
            text = mensaje,
            style = MaterialTheme.typography.bodyLarge,
            color = TintaProfunda,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}
