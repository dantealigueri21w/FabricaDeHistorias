package pe.appmobile.fabricadehistorias.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pe.appmobile.fabricadehistorias.R
import pe.appmobile.fabricadehistorias.ui.theme.PapelEnvejecido
import pe.appmobile.fabricadehistorias.ui.theme.TintaProfunda

enum class PoseAntuco(val drawableId: Int) {
    SALUDA(R.drawable.antuco_saluda),
    EXPLICA(R.drawable.antuco_explica),
    PIENSA(R.drawable.antuco_piensa),
    CELEBRA(R.drawable.antuco_celebra),
    CONFUNDIDO(R.drawable.antuco_confundido),
    SENTADO(R.drawable.antuco_sentado)
}

/** El aviso o comentario de Antuco. Nunca aparece mientras el niño escribe. */
@Composable
fun BurbujaAntuco(mensaje: String, modifier: Modifier = Modifier, pose: PoseAntuco = PoseAntuco.SALUDA) {
    Row(
        modifier = modifier
            .background(PapelEnvejecido, RoundedCornerShape(16.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(pose.drawableId),
            contentDescription = null,
            modifier = Modifier.size(56.dp)
        )
        Text(
            text = mensaje,
            style = MaterialTheme.typography.bodyLarge,
            color = TintaProfunda,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}
