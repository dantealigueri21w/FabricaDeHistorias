package pe.appmobile.fabricadehistorias.ui.screens.mesa

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
import pe.appmobile.fabricadehistorias.domain.engine.MotorEsqueleto
import pe.appmobile.fabricadehistorias.domain.model.Tramo
import pe.appmobile.fabricadehistorias.ui.components.BotonGrande
import pe.appmobile.fabricadehistorias.ui.components.BurbujaAntuco
import pe.appmobile.fabricadehistorias.ui.components.CampoEscritura
import pe.appmobile.fabricadehistorias.ui.components.EncabezadoDeEstacion
import pe.appmobile.fabricadehistorias.ui.theme.Arte
import pe.appmobile.fabricadehistorias.ui.theme.PapelEnvejecido
import pe.appmobile.fabricadehistorias.ui.theme.TintaProfunda

private val ETIQUETAS = mapOf(
    Tramo.ERASE_UNA_VEZ to "Érase una vez…",
    Tramo.TODOS_LOS_DIAS to "Todos los días…",
    Tramo.HASTA_QUE_UN_DIA to "Hasta que un día…",
    Tramo.POR_ESO to "Por eso…",
    Tramo.HASTA_QUE_POR_FIN to "Hasta que por fin…",
    Tramo.Y_DESDE_ENTONCES to "Y desde entonces…"
)

/**
 * La Mesa del Esqueleto: una frase corta por tramo, en su orden narrativo fijo
 * (los seis inicios de Kenn Adams). El botón de Usar la Lente es opcional —
 * simplificación documentada en BUILD_REPORT.md: la Lente vive aquí como
 * acción dentro de la Mesa, no como pantalla propia.
 */
@Composable
fun MesaEsqueletoScreen(
    tramosGuardados: Map<Tramo, String>,
    lenteDesbloqueada: Boolean,
    onGuardarTramo: (Tramo, String) -> Unit,
    onUsarLente: () -> Unit,
    onContinuar: () -> Unit
) {
    val textos = remember { mutableStateOf(tramosGuardados) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PapelEnvejecido)
            .verticalScroll(rememberScrollState())
    ) {
        EncabezadoDeEstacion(Arte.fondoDeEstacion("mesa"), "La Mesa del Esqueleto")

        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            BurbujaAntuco("El Bululú te presta su orden: sigue los seis tramos, uno por uno.")

            MotorEsqueleto.ordenNarrativo().forEach { tramo ->
                CampoEscritura(
                    valor = textos.value[tramo] ?: "",
                    onValorCambia = { nuevo ->
                        textos.value = textos.value + (tramo to nuevo)
                        onGuardarTramo(tramo, nuevo)
                    },
                    etiqueta = ETIQUETAS.getValue(tramo),
                    ayuda = "Al menos ${MotorEsqueleto.PALABRAS_MINIMAS} palabras"
                )
            }

            if (lenteDesbloqueada) {
                BotonGrande("Usar la Lente (comparar algo)", onUsarLente, Modifier.fillMaxWidth())
            }

            val completo = MotorEsqueleto.estaCompleto(textos.value)
            BotonGrande(
                texto = if (completo) "Llevarla al Auditorio" else "Faltan tramos por escribir",
                habilitado = completo,
                onClick = onContinuar,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
