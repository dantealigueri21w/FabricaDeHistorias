package pe.appmobile.fabricadehistorias.ui.screens.pulido

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
import pe.appmobile.fabricadehistorias.data.seed.SemillaEjercicios
import pe.appmobile.fabricadehistorias.domain.engine.MotorCriba
import pe.appmobile.fabricadehistorias.domain.engine.MotorFuelle
import pe.appmobile.fabricadehistorias.domain.engine.MotorPrensa
import pe.appmobile.fabricadehistorias.ui.components.BotonGrande
import pe.appmobile.fabricadehistorias.ui.components.BurbujaAntuco
import pe.appmobile.fabricadehistorias.ui.components.CampoEscritura
import pe.appmobile.fabricadehistorias.ui.components.EncabezadoDeEstacion
import pe.appmobile.fabricadehistorias.ui.theme.Arte
import pe.appmobile.fabricadehistorias.ui.theme.PapelEnvejecido
import pe.appmobile.fabricadehistorias.ui.theme.TintaProfunda

enum class MaquinaPulido { PRENSA, FUELLE, CRIBA }

/** La Sala de Pulido: tres máquinas sobre frases reales. Una a la vez. */
@Composable
fun SalaPulidoScreen(
    onConfirmarPrensa: (resultado: String) -> Unit,
    onConfirmarFuelle: (resultado: String) -> Unit,
    onConfirmarCriba: (resultado: String, palabrasQuitadas: Int) -> Unit
) {
    var maquina by remember { mutableStateOf(MaquinaPulido.PRENSA) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PapelEnvejecido)
            .verticalScroll(rememberScrollState())
    ) {
        EncabezadoDeEstacion(Arte.fondoDeEstacion("pulido"), "La Sala de Pulido")
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            BotonGrande("Prensa", { maquina = MaquinaPulido.PRENSA }, Modifier.weight(1f))
            BotonGrande("Fuelle", { maquina = MaquinaPulido.FUELLE }, Modifier.weight(1f))
            BotonGrande("Criba", { maquina = MaquinaPulido.CRIBA }, Modifier.weight(1f))
        }

            when (maquina) {
                MaquinaPulido.PRENSA -> MaquinaPrensa(onConfirmarPrensa)
                MaquinaPulido.FUELLE -> MaquinaFuelle(onConfirmarFuelle)
                MaquinaPulido.CRIBA -> MaquinaCriba(onConfirmarCriba)
            }
        }
    }
}

@Composable
private fun MaquinaPrensa(onConfirmar: (String) -> Unit) {
    val par = remember { SemillaEjercicios.paresPrensa.random() }
    var fusion by remember { mutableStateOf("") }

    BurbujaAntuco("Doña Puntada dice: une estas dos sin que se note la costura.")
    Text("\"${par.fraseA}\"", style = MaterialTheme.typography.bodyLarge, color = TintaProfunda)
    Text("\"${par.fraseB}\"", style = MaterialTheme.typography.bodyLarge, color = TintaProfunda)
    CampoEscritura(fusion, { fusion = it }, "Tu frase fundida")

    val valida = MotorPrensa.esFusionValida(par.fraseA, par.fraseB, fusion)
    BotonGrande(
        if (valida) "Confirmar fusión" else "Aún no funde las dos",
        habilitado = valida,
        onClick = { onConfirmar(fusion) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun MaquinaFuelle(onConfirmar: (String) -> Unit) {
    val original = remember { SemillaEjercicios.frasesFuelle.random() }
    var expandida by remember { mutableStateOf(original) }

    BurbujaAntuco("Doña Eusebia dice: métele un detalle. Dónde, cuándo o cómo.")
    Text("\"$original\"", style = MaterialTheme.typography.bodyLarge, color = TintaProfunda)
    CampoEscritura(expandida, { expandida = it }, "Tu frase expandida")

    val valida = MotorFuelle.esExpansionValida(original, expandida)
    BotonGrande(
        if (valida) "Confirmar expansión" else "Agrega un detalle nuevo de verdad",
        habilitado = valida,
        onClick = { onConfirmar(expandida) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun MaquinaCriba(onConfirmar: (String, Int) -> Unit) {
    var textoOriginal by remember { mutableStateOf("") }
    var textoLimpio by remember { mutableStateOf("") }

    BurbujaAntuco("Don Lacónico dice: te cobro por palabra. Quita lo que sobra.")
    CampoEscritura(textoOriginal, { textoOriginal = it }, "Pega o escribe una frase tuya con repeticiones")
    CampoEscritura(textoLimpio, { textoLimpio = it }, "Tu versión sin repetir")

    val repetidasAntes = MotorCriba.palabrasRepetidas(textoOriginal).size
    val repetidasDespues = MotorCriba.palabrasRepetidas(textoLimpio).size
    val palabrasQuitadas = (repetidasAntes - repetidasDespues).coerceAtLeast(0)
    val mejoro = textoLimpio.isNotBlank() && repetidasDespues < repetidasAntes

    BotonGrande(
        if (mejoro) "Confirmar limpieza" else "Todavía se repite algo",
        habilitado = mejoro,
        onClick = { onConfirmar(textoLimpio, palabrasQuitadas.coerceAtLeast(1)) },
        modifier = Modifier.fillMaxWidth()
    )
}
