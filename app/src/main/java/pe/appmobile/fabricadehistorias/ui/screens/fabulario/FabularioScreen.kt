package pe.appmobile.fabricadehistorias.ui.screens.fabulario

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import pe.appmobile.fabricadehistorias.ui.components.BurbujaAntuco
import pe.appmobile.fabricadehistorias.ui.theme.PapelEnvejecido
import pe.appmobile.fabricadehistorias.ui.theme.TintaProfunda

@Composable
fun FabularioScreen(fabulas: List<FabulaEntity>, onAbrir: (Long) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(PapelEnvejecido).verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("El Fabulario", style = MaterialTheme.typography.headlineMedium, color = TintaProfunda)

        if (fabulas.isEmpty()) {
            BurbujaAntuco("Todavía no hay ninguna fábula guardada. ¡Fabrica la primera!")
        }

        fabulas.forEach { fabula ->
            Card(
                onClick = { onAbrir(fabula.id) },
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(fabula.titulo.ifBlank { "Sin título" }, style = MaterialTheme.typography.titleLarge, color = TintaProfunda)
                }
            }
        }
    }
}
