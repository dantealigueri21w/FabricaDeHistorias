package pe.appmobile.fabricadehistorias.ui.screens.molino

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
import pe.appmobile.fabricadehistorias.data.seed.SemillaDados
import pe.appmobile.fabricadehistorias.domain.engine.MotorDados
import pe.appmobile.fabricadehistorias.domain.model.TipoDado
import pe.appmobile.fabricadehistorias.domain.model.Tirada
import pe.appmobile.fabricadehistorias.ui.components.BotonGrande
import pe.appmobile.fabricadehistorias.ui.components.BurbujaAntuco
import pe.appmobile.fabricadehistorias.ui.components.TarjetaSeleccionable
import pe.appmobile.fabricadehistorias.ui.theme.PapelEnvejecido
import pe.appmobile.fabricadehistorias.ui.theme.TintaProfunda
import kotlin.random.Random

/** El Molino de Ideas: tres piezas al azar, con una que se puede fijar antes de relanzar una vez. */
@Composable
fun MolinoIdeasScreen(onConfirmar: (Tirada) -> Unit) {
    var tirada by remember { mutableStateOf(MotorDados.lanzar(SemillaDados.caras, Random.Default)) }
    var yaRelanzo by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PapelEnvejecido)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("El Molino de Ideas", style = MaterialTheme.typography.headlineMedium, color = TintaProfunda)
        BurbujaAntuco("Fortunato echó las suertes. Estas son tus piezas.")

        tirada.caras.forEach { cara ->
            TarjetaSeleccionable(
                titulo = cara.texto,
                subtitulo = if (cara.tipo in tirada.fijadas) "Fijada" else "Toca para fijar",
                seleccionada = cara.tipo in tirada.fijadas,
                onClick = { tirada = MotorDados.fijar(tirada, cara.tipo) },
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (!yaRelanzo) {
            BotonGrande(
                "Relanzar lo que no fijé",
                {
                    tirada = MotorDados.relanzarNoFijados(tirada, SemillaDados.caras, Random.Default)
                    yaRelanzo = true
                },
                Modifier.fillMaxWidth()
            )
        }

        BotonGrande("Usar estas piezas", { onConfirmar(tirada) }, Modifier.fillMaxWidth())
    }
}
