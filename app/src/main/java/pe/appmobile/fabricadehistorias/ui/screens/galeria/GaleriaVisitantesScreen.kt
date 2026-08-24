package pe.appmobile.fabricadehistorias.ui.screens.galeria

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
import pe.appmobile.fabricadehistorias.data.local.entity.VisitanteEntity
import pe.appmobile.fabricadehistorias.ui.theme.PapelEnvejecido
import pe.appmobile.fabricadehistorias.ui.theme.TintaProfunda
import pe.appmobile.fabricadehistorias.ui.theme.VerdeMusgo

@Composable
fun GaleriaVisitantesScreen(visitantes: List<VisitanteEntity>) {
    Column(
        modifier = Modifier.fillMaxSize().background(PapelEnvejecido).verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Galería de Visitantes", style = MaterialTheme.typography.headlineMedium, color = TintaProfunda)
        Text(
            "${visitantes.count { it.recibido }} de ${visitantes.size} recibidos",
            style = MaterialTheme.typography.bodyLarge,
            color = VerdeMusgo
        )

        visitantes.sortedBy { it.orden }.forEach { visitante ->
            Card(modifier = Modifier.padding(vertical = 4.dp)) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        if (visitante.recibido) visitante.nombre else "¿ ? ? ?",
                        style = MaterialTheme.typography.titleLarge,
                        color = TintaProfunda
                    )
                    if (visitante.recibido) {
                        Text(visitante.descripcion, style = MaterialTheme.typography.bodyMedium, color = TintaProfunda)
                        Text("Trajo: ${visitante.herramienta}", style = MaterialTheme.typography.bodyMedium, color = VerdeMusgo)
                    } else {
                        Text("Todavía no llega al taller", style = MaterialTheme.typography.bodyMedium, color = TintaProfunda)
                    }
                }
            }
        }
    }
}
