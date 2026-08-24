package pe.appmobile.fabricadehistorias.ui.screens.auditorio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pe.appmobile.fabricadehistorias.data.seed.ParteMoraleja
import pe.appmobile.fabricadehistorias.data.seed.PiezaMoraleja
import pe.appmobile.fabricadehistorias.domain.engine.MotorAuditorio
import pe.appmobile.fabricadehistorias.domain.engine.MotorEsqueleto
import pe.appmobile.fabricadehistorias.domain.engine.MotorMoraleja
import pe.appmobile.fabricadehistorias.domain.model.Caracter
import pe.appmobile.fabricadehistorias.domain.model.Tramo
import pe.appmobile.fabricadehistorias.ui.components.BotonGrande
import pe.appmobile.fabricadehistorias.ui.components.BurbujaAntuco
import pe.appmobile.fabricadehistorias.ui.components.CampoEscritura
import pe.appmobile.fabricadehistorias.ui.components.TarjetaSeleccionable
import pe.appmobile.fabricadehistorias.ui.theme.PapelEnvejecido
import pe.appmobile.fabricadehistorias.ui.theme.TintaProfunda

/**
 * El Auditorio: lee la fábula, arma la moraleja con piezas del Espejo (dos
 * caracteres protagonistas), ve el aforo real y termina. La moraleja va aquí,
 * como último paso, en vez de ser pantalla propia — simplificación
 * documentada en BUILD_REPORT.md.
 */
@Composable
fun AuditorioScreen(
    tramos: Map<Tramo, String>,
    caracterA: Caracter,
    caracterB: Caracter,
    aforoActual: Int,
    piezasDisponibles: List<PiezaMoraleja>,
    onTerminar: (titulo: String, moraleja: String) -> Unit
) {
    var titulo by remember { mutableStateOf("") }
    var inicioElegido by remember { mutableStateOf<PiezaMoraleja?>(null) }
    var finElegido by remember { mutableStateOf<PiezaMoraleja?>(null) }

    val observaciones = MotorAuditorio.observaciones(tramos)
    val piezasRelevantes = piezasDisponibles.filter { it.caracter == caracterA || it.caracter == caracterB }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PapelEnvejecido)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("El Auditorio", style = MaterialTheme.typography.headlineMedium, color = TintaProfunda)
        Text("$aforoActual animales ya vienen a escuchar", style = MaterialTheme.typography.bodyLarge, color = TintaProfunda)

        Text("Tu fábula", style = MaterialTheme.typography.titleLarge, color = TintaProfunda)
        MotorEsqueleto.ordenNarrativo().forEach { tramo ->
            val texto = tramos[tramo]
            if (!texto.isNullOrBlank()) {
                Text(texto, style = MaterialTheme.typography.bodyLarge, color = TintaProfunda)
            }
        }

        if (observaciones.isEmpty()) {
            BurbujaAntuco("¡Se aplaude fuerte! Esta historia camina sola.")
        } else {
            observaciones.forEach { BurbujaAntuco(it) }
        }

        CampoEscritura(titulo, { titulo = it }, "Ponle título a tu fábula")

        Text("Arma la moraleja: Seño Herminia quiere saber qué enseña", style = MaterialTheme.typography.titleLarge, color = TintaProfunda)

        piezasRelevantes.filter { it.parte == ParteMoraleja.INICIO }.forEach { pieza ->
            TarjetaSeleccionable(pieza.texto, seleccionada = inicioElegido == pieza, onClick = { inicioElegido = pieza }, modifier = Modifier.fillMaxWidth())
        }
        piezasRelevantes.filter { it.parte == ParteMoraleja.FIN }.forEach { pieza ->
            TarjetaSeleccionable(pieza.texto, seleccionada = finElegido == pieza, onClick = { finElegido = pieza }, modifier = Modifier.fillMaxWidth())
        }

        val moraleja = if (inicioElegido != null && finElegido != null) "${inicioElegido!!.texto} ${finElegido!!.texto}" else ""
        if (moraleja.isNotBlank()) {
            Text(moraleja, style = MaterialTheme.typography.bodyLarge, color = TintaProfunda)
        }

        val corresponde = moraleja.isNotBlank() &&
            MotorMoraleja.correspondeConLosHechos(moraleja, listOf(caracterA.name, caracterB.name))

        BotonGrande(
            texto = if (corresponde) "Terminar y guardar en el Fabulario" else "Arma una moraleja que corresponda",
            habilitado = corresponde && titulo.isNotBlank(),
            onClick = { onTerminar(titulo, moraleja) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
