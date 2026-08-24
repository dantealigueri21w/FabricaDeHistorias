package pe.appmobile.fabricadehistorias.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import pe.appmobile.fabricadehistorias.ui.theme.DoradoLaton
import pe.appmobile.fabricadehistorias.ui.theme.PapelEnvejecido
import pe.appmobile.fabricadehistorias.ui.theme.TintaProfunda
import pe.appmobile.fabricadehistorias.ui.theme.VioletaTinta

/**
 * Una tarjeta que se toca para elegir (un animal, una pieza de moraleja, un
 * tramo). El borde dorado y el color de fondo son la única señal, más el
 * propio texto — nunca solo el color, para accesibilidad (sección 6).
 */
@Composable
fun TarjetaSeleccionable(
    titulo: String,
    subtitulo: String? = null,
    seleccionada: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .defaultMinSize(minHeight = 120.dp)
            .semantics {
                contentDescription = if (subtitulo != null) "$titulo, $subtitulo" else titulo
                selected = seleccionada
            },
        colors = CardDefaults.cardColors(
            containerColor = if (seleccionada) VioletaTinta else PapelEnvejecido
        ),
        border = BorderStroke(if (seleccionada) 3.dp else 1.dp, DoradoLaton),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp).background(if (seleccionada) VioletaTinta else PapelEnvejecido)) {
            Text(
                text = (if (seleccionada) "✓ " else "") + titulo,
                style = MaterialTheme.typography.titleLarge,
                color = if (seleccionada) androidx.compose.ui.graphics.Color.White else TintaProfunda
            )
            if (subtitulo != null) {
                Text(
                    text = subtitulo,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (seleccionada) androidx.compose.ui.graphics.Color.White else TintaProfunda
                )
            }
        }
    }
}
