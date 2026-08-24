package pe.appmobile.fabricadehistorias.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pe.appmobile.fabricadehistorias.data.repository.FabricaRepository

class FabricaViewModelFactory(private val repositorio: FabricaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return FabricaViewModel(repositorio) as T
    }
}
