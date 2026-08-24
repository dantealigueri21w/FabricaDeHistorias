package pe.appmobile.fabricadehistorias.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pe.appmobile.fabricadehistorias.ui.theme.DoradoLaton
import pe.appmobile.fabricadehistorias.ui.theme.VioletaTinta

/**
 * Ocupa el lugar de una ilustración que todavía no existe. El arte final se
 * genera aparte y se procesa a `drawable-nodpi/`; mientras tanto
 * la app tiene que compilar, verse coherente y probarse de verdad — así que
 * este marcador usa la paleta real del taller, no un gris genérico, y nunca se
 * hace pasar por la ilustración final.
 *
 * Cuando el arte llegue, cada uso de este componente se reemplaza por
 * `Image(painterResource(R.drawable.<nombre>), contentDescription = ...)`.
 */
@Composable
fun MarcadorIlustracion(
    etiqueta: String,
    modifier: Modifier = Modifier,
    colorFondo: Color = VioletaTinta,
    descripcionAccesible: String? = etiqueta
) {
    val modificadorSemantico = if (descripcionAccesible != null) {
        Modifier.semantics { contentDescription = descripcionAccesible }
    } else {
        Modifier.semantics { contentDescription = "" }
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(colorFondo)
            .border(2.dp, DoradoLaton, RoundedCornerShape(16.dp))
            .then(modificadorSemantico),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = etiqueta,
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(12.dp)
        )
    }
}
