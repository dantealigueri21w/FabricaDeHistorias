package pe.appmobile.fabricadehistorias.ui.screens.taller

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import pe.appmobile.fabricadehistorias.ui.components.BotonGrande
import pe.appmobile.fabricadehistorias.ui.theme.PapelEnvejecido
import pe.appmobile.fabricadehistorias.ui.theme.TintaProfunda
import pe.appmobile.fabricadehistorias.ui.theme.VerdeMusgo

@Composable
fun TallerScreen(
    alias: String,
    racha: Int,
    aforoAuditorio: Int,
    visitantesRecibidos: Int,
    onNuevaFabula: () -> Unit,
    onSalaDePulido: () -> Unit,
    onFabulario: () -> Unit,
    onGaleriaVisitantes: () -> Unit,
    onCuadernoAprendiz: () -> Unit,
    onRinconPractica: () -> Unit,
    onAjustes: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PapelEnvejecido)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("El taller de Antuco", style = MaterialTheme.typography.headlineMedium, color = TintaProfunda)
        Text(
            "Hola, $alias. Racha de $racha día(s).",
            style = MaterialTheme.typography.bodyLarge,
            color = TintaProfunda,
            modifier = Modifier.semantics { contentDescription = "Hola $alias, racha de $racha dias" }
        )
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            InfoCorta("Auditorio", "$aforoAuditorio animales")
            InfoCorta("Visitantes", "$visitantesRecibidos de 12")
        }

        BotonGrande("Fabricar una nueva historia", onNuevaFabula, Modifier.fillMaxWidth())
        BotonGrande("Sala de Pulido", onSalaDePulido, Modifier.fillMaxWidth())
        BotonGrande("El Fabulario", onFabulario, Modifier.fillMaxWidth())
        BotonGrande("Galería de Visitantes", onGaleriaVisitantes, Modifier.fillMaxWidth())
        BotonGrande("Cuaderno del Aprendiz", onCuadernoAprendiz, Modifier.fillMaxWidth())
        BotonGrande("Rincón de Práctica", onRinconPractica, Modifier.fillMaxWidth())
        BotonGrande("Ajustes", onAjustes, Modifier.fillMaxWidth())
    }
}

@Composable
private fun InfoCorta(etiqueta: String, valor: String) {
    Card(modifier = Modifier.padding(4.dp)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(etiqueta, style = MaterialTheme.typography.bodyMedium, color = TintaProfunda)
            Text(valor, style = MaterialTheme.typography.titleLarge, color = VerdeMusgo)
        }
    }
}
