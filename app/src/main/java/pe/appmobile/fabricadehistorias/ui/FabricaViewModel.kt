package pe.appmobile.fabricadehistorias.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pe.appmobile.fabricadehistorias.data.local.entity.AnimalEntity
import pe.appmobile.fabricadehistorias.data.local.entity.FabulaEntity
import pe.appmobile.fabricadehistorias.data.local.entity.InsigniaEntity
import pe.appmobile.fabricadehistorias.data.local.entity.PaginaCuadernoEntity
import pe.appmobile.fabricadehistorias.data.local.entity.PerfilEntity
import pe.appmobile.fabricadehistorias.data.local.entity.RachaEntity
import pe.appmobile.fabricadehistorias.data.local.entity.VisitanteEntity
import pe.appmobile.fabricadehistorias.data.repository.FabricaRepository
import pe.appmobile.fabricadehistorias.domain.model.Insignia
import pe.appmobile.fabricadehistorias.domain.model.Tramo

/** Estado compartido del taller. Cada pantalla pide sus propios datos puntuales al repositorio. */
class FabricaViewModel(private val repositorio: FabricaRepository) : ViewModel() {

    val perfil: StateFlow<PerfilEntity?> = repositorio.observarPerfil()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val animales: StateFlow<List<AnimalEntity>> = repositorio.observarAnimales()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val visitantes: StateFlow<List<VisitanteEntity>> = repositorio.observarVisitantes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val insignias: StateFlow<List<InsigniaEntity>> = repositorio.observarInsignias()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val paginasCuaderno: StateFlow<List<PaginaCuadernoEntity>> = repositorio.observarPaginasCuaderno()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val fabulasTerminadas: StateFlow<List<FabulaEntity>> = repositorio.observarFabulasTerminadas()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val racha: StateFlow<RachaEntity?> = repositorio.observarRacha()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    init {
        viewModelScope.launch {
            repositorio.sembrarSiHaceFalta()
            repositorio.registrarSesionDeHoy()
        }
    }

    fun guardarPerfil(alias: String, avatarId: Int) {
        viewModelScope.launch { repositorio.guardarPerfil(alias, avatarId) }
    }

    suspend fun recibirSiguienteVisitante() = repositorio.recibirSiguienteVisitante()

    suspend fun obtenerEncargoNuevo() = repositorio.obtenerEncargoNuevo()

    suspend fun crearFabula(animalAId: String, animalBId: String, encargoId: String?) =
        repositorio.crearFabula(animalAId, animalBId, encargoId)

    suspend fun guardarTramo(fabulaId: Long, tramo: Tramo, texto: String) =
        repositorio.guardarTramo(fabulaId, tramo, texto)

    suspend fun obtenerTramosDeFabula(fabulaId: Long) = repositorio.obtenerTramosDeFabula(fabulaId)

    suspend fun terminarFabula(fabulaId: Long, titulo: String): Set<Insignia> =
        repositorio.terminarFabula(fabulaId, titulo)

    suspend fun marcarLenteUsada(fabulaId: Long) = repositorio.marcarLenteUsada(fabulaId)

    suspend fun marcarReglaDeTresCompleta(fabulaId: Long) = repositorio.marcarReglaDeTresCompleta(fabulaId)

    suspend fun marcarBurladorBurlado(fabulaId: Long) = repositorio.marcarBurladorBurlado(fabulaId)

    suspend fun registrarIntentoMoraleja(fabulaId: Long, esPrimerIntento: Boolean, fueCorrecto: Boolean) =
        repositorio.registrarIntentoMoraleja(fabulaId, esPrimerIntento, fueCorrecto)

    suspend fun registrarEjercicioPrensa(textoResultado: String, confirmado: Boolean) =
        repositorio.registrarEjercicioPrensa(textoResultado, confirmado)

    suspend fun registrarEjercicioFuelle(textoResultado: String, confirmado: Boolean) =
        repositorio.registrarEjercicioFuelle(textoResultado, confirmado)

    suspend fun registrarEjercicioCriba(textoResultado: String, palabrasQuitadas: Int) =
        repositorio.registrarEjercicioCriba(textoResultado, palabrasQuitadas)

    suspend fun guardarFraseEnPagina(herramienta: String, frase: String) =
        repositorio.guardarFraseEnPagina(herramienta, frase)

    suspend fun obtenerAforoAuditorio() = repositorio.obtenerAforoAuditorio()

    suspend fun obtenerFabulasSinTerminar() = repositorio.obtenerFabulasSinTerminar()
}
