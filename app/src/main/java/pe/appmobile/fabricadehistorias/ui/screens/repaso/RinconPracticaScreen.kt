package pe.appmobile.fabricadehistorias.ui.screens.repaso

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pe.appmobile.fabricadehistorias.data.local.entity.FabulaEntity
import pe.appmobile.fabricadehistorias.ui.components.BotonGrande
import pe.appmobile.fabricadehistorias.ui.components.BurbujaAntuco
import pe.appmobile.fabricadehistorias.ui.theme.PapelEnvejecido
import pe.appmobile.fabricadehistorias.ui.theme.TintaProfunda

/**
 * El Rincón de Práctica: retoma fábulas que el niño empezó y dejó a medias,
 * con la misma mecánica donde las dejó. Sin diagnósticos ni etiquetas.
 */
@Composable
fun RinconPracticaScreen(fabulasAMedias: List<FabulaEntity>, onRetomar: (Long) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(PapelEnvejecido).verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("El Rincón de Práctica", style = MaterialTheme.typography.headlineMedium, color = TintaProfunda)

        if (fabulasAMedias.isEmpty()) {
            BurbujaAntuco("No hay ninguna fábula a medias. ¡Vas al día!")
        } else {
            BurbujaAntuco("Estas se quedaron esperando. Termínalas cuando quieras.")
            fabulasAMedias.forEach { fabula ->
                Card(modifier = Modifier.padding(vertical = 4.dp)) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Fábula sin terminar", style = MaterialTheme.typography.titleLarge, color = TintaProfunda)
                        BotonGrande("Retomarla", { onRetomar(fabula.id) }, Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }
}
