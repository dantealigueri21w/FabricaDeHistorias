package pe.appmobile.fabricadehistorias.ui.screens.rueda

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pe.appmobile.fabricadehistorias.data.local.entity.AnimalEntity
import pe.appmobile.fabricadehistorias.domain.engine.MotorPersonajes
import pe.appmobile.fabricadehistorias.domain.model.Caracter
import pe.appmobile.fabricadehistorias.domain.model.FuerzaChoque
import pe.appmobile.fabricadehistorias.ui.components.BotonGrande
import pe.appmobile.fabricadehistorias.ui.components.BurbujaAntuco
import pe.appmobile.fabricadehistorias.ui.components.EncabezadoDeEstacion
import pe.appmobile.fabricadehistorias.ui.components.TarjetaSeleccionable
import pe.appmobile.fabricadehistorias.ui.theme.Arte
import pe.appmobile.fabricadehistorias.ui.theme.PapelEnvejecido
import pe.appmobile.fabricadehistorias.ui.theme.TintaProfunda

/**
 * La Rueda de Animales: elegir los dos personajes que van a chocar. Grilla
 * armada con `chunked`, nunca LazyVerticalGrid (sección 7.1, punto 6).
 */
@Composable
fun RuedaAnimalesScreen(
    animales: List<AnimalEntity>,
    encargoTexto: String,
    onConfirmar: (animalA: AnimalEntity, animalB: AnimalEntity) -> Unit
) {
    var animalA by remember { mutableStateOf<AnimalEntity?>(null) }
    var animalB by remember { mutableStateOf<AnimalEntity?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PapelEnvejecido)
            .verticalScroll(rememberScrollState())
    ) {
        EncabezadoDeEstacion(Arte.fondoDeEstacion("rueda"), "La Rueda de Animales")

        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            BurbujaAntuco(encargoTexto)

            Text("Toca dos animales que choquen", style = MaterialTheme.typography.titleLarge, color = TintaProfunda)

            animales.chunked(3).forEach { fila ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    fila.forEach { animal ->
                        val imagen = Arte.animalONull(animal.id)
                        TarjetaSeleccionable(
                            titulo = animal.nombre,
                            subtitulo = animal.region,
                            seleccionada = animal.id == animalA?.id || animal.id == animalB?.id,
                            onClick = {
                                when {
                                    animalA?.id == animal.id -> animalA = null
                                    animalB?.id == animal.id -> animalB = null
                                    animalA == null -> animalA = animal
                                    animalB == null -> animalB = animal
                                    else -> { animalA = animalB; animalB = animal }
                                }
                            },
                            modifier = Modifier.weight(1f),
                            imagenId = imagen
                        )
                    }
                }
            }

            val a = animalA
            val b = animalB
            if (a != null && b != null) {
                val choque = MotorPersonajes.choqueEntre(Caracter.valueOf(a.caracter), Caracter.valueOf(b.caracter))
                Text(
                    texto(choque.fuerza, a.nombre, b.nombre),
                    style = MaterialTheme.typography.bodyLarge,
                    color = TintaProfunda
                )
                BotonGrande("Fabricar con estos dos", { onConfirmar(a, b) }, Modifier.fillMaxWidth())
            }
        }
    }
}

private fun texto(fuerza: FuerzaChoque, nombreA: String, nombreB: String): String = when (fuerza) {
    FuerzaChoque.FUERTE -> "¡$nombreA y $nombreB chocan de verdad! Esta historia casi se escribe sola."
    FuerzaChoque.MEDIA -> "$nombreA y $nombreB compiten por lo mismo. Hay historia ahí."
    FuerzaChoque.DEBIL -> "$nombreA y $nombreB no chocan solos. Tú decides por qué se cruzan."
}
