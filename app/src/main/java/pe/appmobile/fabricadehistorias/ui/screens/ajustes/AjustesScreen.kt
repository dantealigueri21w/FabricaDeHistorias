package pe.appmobile.fabricadehistorias.ui.screens.ajustes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pe.appmobile.fabricadehistorias.ui.theme.PapelEnvejecido
import pe.appmobile.fabricadehistorias.ui.theme.TintaProfunda

/**
 * Sonido y vibración se guardan en memoria por ahora, no en disco —
 * simplificación documentada en BUILD_REPORT.md: no hay efectos de sonido
 * todavía, así que persistir la preferencia no tiene nada real que controlar.
 */
@Composable
fun AjustesScreen() {
    var sonidoActivo by remember { mutableStateOf(true) }
    var vibracionActiva by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize().background(PapelEnvejecido).verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("Ajustes", style = MaterialTheme.typography.headlineMedium, color = TintaProfunda)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Sonido", style = MaterialTheme.typography.bodyLarge, color = TintaProfunda)
            Switch(checked = sonidoActivo, onCheckedChange = { sonidoActivo = it })
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Vibración", style = MaterialTheme.typography.bodyLarge, color = TintaProfunda)
            Switch(checked = vibracionActiva, onCheckedChange = { vibracionActiva = it })
        }
    }
}
