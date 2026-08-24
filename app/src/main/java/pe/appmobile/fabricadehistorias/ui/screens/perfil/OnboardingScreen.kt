package pe.appmobile.fabricadehistorias.ui.screens.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import pe.appmobile.fabricadehistorias.ui.components.BotonGrande
import pe.appmobile.fabricadehistorias.ui.components.CampoEscritura
import pe.appmobile.fabricadehistorias.ui.components.TarjetaSeleccionable
import pe.appmobile.fabricadehistorias.ui.theme.PapelEnvejecido
import pe.appmobile.fabricadehistorias.ui.theme.TintaProfunda

/**
 * Primer arranque: elegir alias (nunca el nombre real) y avatar. Grilla armada
 * a mano con `chunked`, no LazyVerticalGrid — sección 7.1 del prompt maestro:
 * anidar un componente perezoso dentro de un padre que ya hace scroll revienta
 * la app apenas se mide la pantalla.
 */
@Composable
fun OnboardingScreen(onListo: (alias: String, avatarId: Int) -> Unit) {
    var alias by remember { mutableStateOf("") }
    var avatarElegido by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PapelEnvejecido)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            "Bienvenido al taller de Antuco",
            style = MaterialTheme.typography.headlineMedium,
            color = TintaProfunda
        )
        Text(
            "Ponte un nombre de fabulista y elige tu avatar. Nunca tu nombre real.",
            style = MaterialTheme.typography.bodyLarge,
            color = TintaProfunda
        )

        CampoEscritura(
            valor = alias,
            onValorCambia = { if (it.length <= 20) alias = it },
            etiqueta = "Tu nombre de fabulista"
        )

        Text("Elige tu avatar", style = MaterialTheme.typography.titleLarge, color = TintaProfunda)

        Avatares.lista.chunked(4).forEach { fila ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                fila.forEach { avatar ->
                    TarjetaSeleccionable(
                        titulo = avatar.descripcion,
                        seleccionada = avatarElegido == avatar.id,
                        onClick = { avatarElegido = avatar.id },
                        modifier = Modifier.weight(1f).size(80.dp)
                    )
                }
            }
        }

        BotonGrande(
            texto = "Entrar al taller",
            habilitado = alias.isNotBlank() && avatarElegido != null,
            onClick = { onListo(alias.trim(), avatarElegido ?: 0) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
