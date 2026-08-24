package pe.appmobile.fabricadehistorias.ui.screens.cuaderno

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
import pe.appmobile.fabricadehistorias.data.local.entity.PaginaCuadernoEntity
import pe.appmobile.fabricadehistorias.ui.components.BotonGrande
import pe.appmobile.fabricadehistorias.ui.theme.PapelEnvejecido
import pe.appmobile.fabricadehistorias.ui.theme.TintaProfunda
import pe.appmobile.fabricadehistorias.ui.theme.VerdeMusgo

/** Cada página guarda la frase que el propio niño eligió — la app nunca elige por él. */
@Composable
fun CuadernoAprendizScreen(paginas: List<PaginaCuadernoEntity>, onElegirFrase: (herramienta: String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(PapelEnvejecido).verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("El Cuaderno del Aprendiz", style = MaterialTheme.typography.headlineMedium, color = TintaProfunda)
        Text(
            "${paginas.count { !it.fraseElegida.isNullOrBlank() }} de ${paginas.size} páginas llenas",
            style = MaterialTheme.typography.bodyLarge,
            color = VerdeMusgo
        )

        paginas.forEach { pagina ->
            Card(modifier = Modifier.padding(vertical = 4.dp)) {
                Column(Modifier.padding(16.dp)) {
                    Text(pagina.herramienta, style = MaterialTheme.typography.titleLarge, color = TintaProfunda)
                    if (pagina.fraseElegida.isNullOrBlank()) {
                        BotonGrande("Elegir mi mejor frase", { onElegirFrase(pagina.herramienta) }, Modifier.fillMaxWidth())
                    } else {
                        Text(pagina.fraseElegida, style = MaterialTheme.typography.bodyLarge, color = TintaProfunda)
                    }
                }
            }
        }
    }
}
