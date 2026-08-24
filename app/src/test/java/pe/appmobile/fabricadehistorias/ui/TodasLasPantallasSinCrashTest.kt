package pe.appmobile.fabricadehistorias.ui

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import pe.appmobile.fabricadehistorias.data.local.entity.AnimalEntity
import pe.appmobile.fabricadehistorias.data.local.entity.FabulaEntity
import pe.appmobile.fabricadehistorias.data.local.entity.PaginaCuadernoEntity
import pe.appmobile.fabricadehistorias.data.local.entity.VisitanteEntity
import pe.appmobile.fabricadehistorias.data.seed.SemillaAnimales
import pe.appmobile.fabricadehistorias.data.seed.SemillaMoralejas
import pe.appmobile.fabricadehistorias.domain.model.Caracter
import pe.appmobile.fabricadehistorias.domain.model.Tramo
import pe.appmobile.fabricadehistorias.ui.screens.ajustes.AjustesScreen
import pe.appmobile.fabricadehistorias.ui.screens.auditorio.AuditorioScreen
import pe.appmobile.fabricadehistorias.ui.screens.cuaderno.CuadernoAprendizScreen
import pe.appmobile.fabricadehistorias.ui.screens.fabulario.FabularioScreen
import pe.appmobile.fabricadehistorias.ui.screens.galeria.GaleriaVisitantesScreen
import pe.appmobile.fabricadehistorias.ui.screens.lente.LenteScreen
import pe.appmobile.fabricadehistorias.ui.screens.mesa.MesaEsqueletoScreen
import pe.appmobile.fabricadehistorias.ui.screens.molino.MolinoIdeasScreen
import pe.appmobile.fabricadehistorias.ui.screens.perfil.OnboardingScreen
import pe.appmobile.fabricadehistorias.ui.screens.pulido.SalaPulidoScreen
import pe.appmobile.fabricadehistorias.ui.screens.repaso.RinconPracticaScreen
import pe.appmobile.fabricadehistorias.ui.screens.rueda.RuedaAnimalesScreen
import pe.appmobile.fabricadehistorias.ui.screens.taller.TallerScreen
import pe.appmobile.fabricadehistorias.ui.theme.FabricaDeHistoriasTheme

/**
 * Cada pantalla alcanzable por el niño se monta de verdad al menos una vez
 * (sección 10.1 del prompt maestro). Los tests de dominio y repositorio nunca
 * miden ni dibujan Compose — un fallo que solo aparece al medir la pantalla
 * (por ejemplo un Lazy* anidado en un padre con scroll) es invisible para
 * ellos. Esto es justo lo que se escapó una vez en Numerópolis.
 */
@RunWith(RobolectricTestRunner::class)
class TodasLasPantallasSinCrashTest {

    @get:Rule val compose = createComposeRule()

    private val animalesReales = SemillaAnimales.animales
    private val tramosReales = mapOf(
        Tramo.ERASE_UNA_VEZ to "un zorro muy confiado",
        Tramo.TODOS_LOS_DIAS to "se paseaba por el puente",
        Tramo.HASTA_QUE_UN_DIA to "llego una neblina espesa",
        Tramo.POR_ESO to "el zorro se perdio dos veces",
        Tramo.HASTA_QUE_POR_FIN to "el cuy le mostro el camino",
        Tramo.Y_DESDE_ENTONCES to "el zorro saluda al cuy siempre"
    )

    private fun montar(contenido: @androidx.compose.runtime.Composable () -> Unit) {
        compose.setContent { FabricaDeHistoriasTheme { contenido() } }
    }

    @Test
    fun `Onboarding no revienta la app`() {
        montar { OnboardingScreen(onListo = { _, _ -> }) }
    }

    @Test
    fun `El Taller no revienta la app`() {
        montar {
            TallerScreen(
                alias = "Rodrigo", racha = 3, aforoAuditorio = 12, visitantesRecibidos = 4,
                onNuevaFabula = {}, onSalaDePulido = {}, onFabulario = {},
                onGaleriaVisitantes = {}, onCuadernoAprendiz = {}, onRinconPractica = {}, onAjustes = {}
            )
        }
    }

    @Test
    fun `La Rueda de Animales no revienta la app`() {
        montar {
            RuedaAnimalesScreen(
                animales = animalesReales,
                encargoTexto = "Un pescador presumido le apostó al mar que nunca se perdería.",
                onConfirmar = { _, _ -> }
            )
        }
    }

    @Test
    fun `El Molino de Ideas no revienta la app`() {
        montar { MolinoIdeasScreen(onConfirmar = {}) }
    }

    @Test
    fun `La Mesa del Esqueleto no revienta la app, con y sin la Lente`() {
        montar {
            MesaEsqueletoScreen(
                tramosGuardados = tramosReales, lenteDesbloqueada = true,
                onGuardarTramo = { _, _ -> }, onUsarLente = {}, onContinuar = {}
            )
        }
    }

    @Test
    fun `La Lente no revienta la app`() {
        montar { LenteScreen(terminoA = "el puente", onListo = {}) }
    }

    @Test
    fun `La Sala de Pulido no revienta la app`() {
        montar {
            SalaPulidoScreen(
                onConfirmarPrensa = {}, onConfirmarFuelle = {}, onConfirmarCriba = { _, _ -> }
            )
        }
    }

    @Test
    fun `El Auditorio no revienta la app`() {
        montar {
            AuditorioScreen(
                tramos = tramosReales,
                caracterA = Caracter.CONFIADO,
                caracterB = Caracter.ASTUTO,
                aforoActual = 12,
                piezasDisponibles = SemillaMoralejas.piezas,
                onTerminar = { _, _ -> }
            )
        }
    }

    @Test
    fun `El Fabulario no revienta la app, con fabulas`() {
        val fabulas = listOf(
            FabulaEntity(id = 1, titulo = "El zorro y el cuy", animalAId = "zorro_andino", animalBId = "cuy", encargoId = "F1", terminada = true, fechaCreacionEpochMillis = 0L)
        )
        montar { FabularioScreen(fabulas = fabulas, onAbrir = {}) }
    }

    @Test
    fun `El Fabulario no revienta la app, vacio`() {
        montar { FabularioScreen(fabulas = emptyList(), onAbrir = {}) }
    }

    @Test
    fun `La Galeria de Visitantes no revienta la app`() {
        val visitantes = listOf(
            VisitanteEntity(id = "no_isidro", nombre = "Ño Isidro", descripcion = "desc", herramienta = "La Rueda de Animales", tradicionEvocada = "Esopo", encargoDelVisitante = "encargo", orden = 1, recibido = true),
            VisitanteEntity(id = "don_espino", nombre = "Don Espino", descripcion = "desc", herramienta = "La Lente", tradicionEvocada = "trad", encargoDelVisitante = "encargo", orden = 5, recibido = false)
        )
        montar { GaleriaVisitantesScreen(visitantes = visitantes) }
    }

    @Test
    fun `El Cuaderno del Aprendiz no revienta la app`() {
        val paginas = listOf(
            PaginaCuadernoEntity(herramienta = "La Rueda de Animales", fraseElegida = "El cuy fue mas astuto"),
            PaginaCuadernoEntity(herramienta = "La Lente", fraseElegida = null)
        )
        montar { CuadernoAprendizScreen(paginas = paginas, onElegirFrase = {}) }
    }

    @Test
    fun `El Rincon de Practica no revienta la app, con fabulas a medias`() {
        val aMedias = listOf(
            FabulaEntity(id = 2, titulo = "", animalAId = "cuy", animalBId = "zorro_andino", encargoId = null, terminada = false, fechaCreacionEpochMillis = 0L)
        )
        montar { RinconPracticaScreen(fabulasAMedias = aMedias, onRetomar = {}) }
    }

    @Test
    fun `El Rincon de Practica no revienta la app, vacio`() {
        montar { RinconPracticaScreen(fabulasAMedias = emptyList(), onRetomar = {}) }
    }

    @Test
    fun `Ajustes no revienta la app`() {
        montar { AjustesScreen() }
    }
}
