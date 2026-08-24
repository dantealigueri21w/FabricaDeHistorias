package pe.appmobile.fabricadehistorias.ui.screens.lente

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
import pe.appmobile.fabricadehistorias.domain.engine.MotorComparacion
import pe.appmobile.fabricadehistorias.ui.components.BotonGrande
import pe.appmobile.fabricadehistorias.ui.components.BurbujaAntuco
import pe.appmobile.fabricadehistorias.ui.components.CampoEscritura
import pe.appmobile.fabricadehistorias.ui.theme.PapelEnvejecido
import pe.appmobile.fabricadehistorias.ui.theme.TintaProfunda

/** La Lente: comparar algo real de la historia con otra cosa. */
@Composable
fun LenteScreen(terminoA: String, onListo: (comparacion: String) -> Unit) {
    var terminoB by remember { mutableStateOf("") }
    var frase by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PapelEnvejecido)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("La Lente de Don Espino", style = MaterialTheme.typography.headlineMedium, color = TintaProfunda)
        BurbujaAntuco("\"$terminoA\" se parece a algo. ¿A qué?")

        CampoEscritura(terminoB, { terminoB = it }, "¿A qué se parece?")
        CampoEscritura(frase, { frase = it }, "Escribe la comparación completa",
            ayuda = "Usa \"$terminoA\", lo que dijiste, y una palabra como \"parece\" o \"como\"")

        val valida = terminoB.isNotBlank() && MotorComparacion.esComparacionValida(frase, terminoA, terminoB)
        BotonGrande(
            texto = if (valida) "Llevarla a la historia" else "Falta que compares de verdad",
            habilitado = valida,
            onClick = { onListo(frase) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
