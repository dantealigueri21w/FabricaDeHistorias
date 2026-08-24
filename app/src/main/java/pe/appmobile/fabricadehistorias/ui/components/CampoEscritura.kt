package pe.appmobile.fabricadehistorias.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import pe.appmobile.fabricadehistorias.ui.theme.VioletaTinta

/**
 * El campo donde el niño escribe de verdad. Es la mecánica central de toda la
 * app y el riesgo técnico nº 1 (ficha, "Requisitos especiales"): el estado
 * vive en el llamador vía [valor]/[onValorCambia] —normalmente respaldado por
 * `rememberSaveable` o, mejor, guardado ya en Room por cada tramo— para que no
 * se pierda al rotar la pantalla ni al salir de la estación a medio escribir.
 */
@Composable
fun CampoEscritura(
    valor: String,
    onValorCambia: (String) -> Unit,
    etiqueta: String,
    modifier: Modifier = Modifier,
    ayuda: String? = null
) {
    OutlinedTextField(
        value = valor,
        onValueChange = onValorCambia,
        label = { Text(etiqueta) },
        supportingText = ayuda?.let { { Text(it, style = MaterialTheme.typography.bodyMedium) } },
        modifier = modifier
            .fillMaxWidth()
            .semantics { contentDescription = etiqueta },
        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = VioletaTinta)
    )
}
