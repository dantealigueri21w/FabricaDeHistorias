package pe.appmobile.fabricadehistorias.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pe.appmobile.fabricadehistorias.ui.theme.TintaProfunda

/**
 * La franja de imagen con la que abre cada estación, con el título en un chip
 * legible sobre la esquina inferior. El texto va debajo de la imagen, nunca
 * en un padre que ya hace scroll con un Lazy* dentro — sección 7.1.
 */
@Composable
fun EncabezadoDeEstacion(@DrawableRes fondoId: Int, titulo: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth().height(180.dp)) {
        Image(
            painter = painterResource(fondoId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Text(
            text = titulo,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
                .background(TintaProfunda.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                .padding(horizontal = 10.dp, vertical = 6.dp)
        )
    }
}
