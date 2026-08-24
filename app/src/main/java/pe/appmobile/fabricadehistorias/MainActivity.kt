package pe.appmobile.fabricadehistorias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import pe.appmobile.fabricadehistorias.di.AppContainer
import pe.appmobile.fabricadehistorias.ui.FabricaViewModel
import pe.appmobile.fabricadehistorias.ui.FabricaViewModelFactory
import pe.appmobile.fabricadehistorias.ui.navigation.FabricaNavHost
import pe.appmobile.fabricadehistorias.ui.theme.FabricaDeHistoriasTheme
import pe.appmobile.fabricadehistorias.ui.theme.PapelEnvejecido

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val contenedor = remember { AppContainer(applicationContext) }
            val viewModel: FabricaViewModel = viewModel(factory = FabricaViewModelFactory(contenedor.repositorio))

            FabricaDeHistoriasTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = PapelEnvejecido) {
                    FabricaNavHost(viewModel)
                }
            }
        }
    }
}
