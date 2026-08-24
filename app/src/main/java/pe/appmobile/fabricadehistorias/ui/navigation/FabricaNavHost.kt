package pe.appmobile.fabricadehistorias.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.launch
import pe.appmobile.fabricadehistorias.data.local.entity.EncargoEntity
import pe.appmobile.fabricadehistorias.data.seed.SemillaMoralejas
import pe.appmobile.fabricadehistorias.domain.model.Caracter
import pe.appmobile.fabricadehistorias.domain.model.Tramo
import pe.appmobile.fabricadehistorias.ui.FabricaViewModel
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

@Composable
fun FabricaNavHost(viewModel: FabricaViewModel) {
    val navController = rememberNavController()
    val alcance = rememberCoroutineScope()
    val perfil by viewModel.perfil.collectAsState(null)

    NavHost(navController = navController, startDestination = Rutas.ONBOARDING) {

        composable(Rutas.ONBOARDING) {
            if (perfil != null) {
                LaunchedEffect(Unit) {
                    navController.navigate(Rutas.TALLER) { popUpTo(Rutas.ONBOARDING) { inclusive = true } }
                }
            } else {
                OnboardingScreen { alias, avatarId ->
                    viewModel.guardarPerfil(alias, avatarId)
                    navController.navigate(Rutas.TALLER) { popUpTo(Rutas.ONBOARDING) { inclusive = true } }
                }
            }
        }

        composable(Rutas.TALLER) {
            val animales by viewModel.animales.collectAsState(emptyList())
            val visitantes by viewModel.visitantes.collectAsState(emptyList())
            val racha by viewModel.racha.collectAsState(null)
            var aforo by remember { mutableStateOf(0) }
            LaunchedEffect(animales) { aforo = viewModel.obtenerAforoAuditorio() }

            TallerScreen(
                alias = perfil?.alias ?: "",
                racha = racha?.rachaActual ?: 0,
                aforoAuditorio = aforo,
                visitantesRecibidos = visitantes.count { it.recibido },
                onNuevaFabula = { navController.navigate(Rutas.RUEDA_ANIMALES) },
                onSalaDePulido = { navController.navigate(Rutas.SALA_PULIDO) },
                onFabulario = { navController.navigate(Rutas.FABULARIO) },
                onGaleriaVisitantes = { navController.navigate(Rutas.GALERIA_VISITANTES) },
                onCuadernoAprendiz = { navController.navigate(Rutas.CUADERNO_APRENDIZ) },
                onRinconPractica = { navController.navigate(Rutas.RINCON_PRACTICA) },
                onAjustes = { navController.navigate(Rutas.AJUSTES) }
            )
        }

        composable(Rutas.RUEDA_ANIMALES) {
            val animales by viewModel.animales.collectAsState(emptyList())
            var encargo by remember { mutableStateOf<EncargoEntity?>(null) }
            LaunchedEffect(Unit) { encargo = viewModel.obtenerEncargoNuevo() }

            if (animales.isNotEmpty()) {
                RuedaAnimalesScreen(
                    animales = animales,
                    encargoTexto = encargo?.textoAntuco ?: "Antuco está pensando en un encargo…",
                    onConfirmar = { animalA, animalB ->
                        alcance.launch {
                            val fabulaId = viewModel.crearFabula(animalA.id, animalB.id, encargo?.id)
                            navController.navigate(Rutas.molinoIdeas(fabulaId))
                        }
                    }
                )
            }
        }

        composable(
            Rutas.MOLINO_IDEAS,
            arguments = listOf(navArgument("fabulaId") { type = NavType.LongType })
        ) { entrada ->
            val fabulaId = entrada.arguments?.getLong("fabulaId") ?: 0L
            MolinoIdeasScreen { navController.navigate(Rutas.mesaEsqueleto(fabulaId)) }
        }

        composable(
            Rutas.MESA_ESQUELETO,
            arguments = listOf(navArgument("fabulaId") { type = NavType.LongType })
        ) { entrada ->
            val fabulaId = entrada.arguments?.getLong("fabulaId") ?: 0L
            var tramos by remember { mutableStateOf<Map<Tramo, String>>(emptyMap()) }
            LaunchedEffect(fabulaId) {
                tramos = viewModel.obtenerTramosDeFabula(fabulaId)
            }
            val listaVisitantes by viewModel.visitantes.collectAsState(emptyList())
            val lenteDesbloqueada = listaVisitantes.any { it.herramienta == "La Lente" && it.recibido }

            MesaEsqueletoScreen(
                tramosGuardados = tramos,
                lenteDesbloqueada = lenteDesbloqueada,
                onGuardarTramo = { tramo, texto ->
                    alcance.launch { viewModel.guardarTramo(fabulaId, tramo, texto) }
                },
                onUsarLente = { navController.navigate(Rutas.lente(fabulaId)) },
                onContinuar = { navController.navigate(Rutas.auditorio(fabulaId)) }
            )
        }

        composable(
            Rutas.LENTE,
            arguments = listOf(navArgument("fabulaId") { type = NavType.LongType })
        ) { entrada ->
            val fabulaId = entrada.arguments?.getLong("fabulaId") ?: 0L
            LenteScreen(terminoA = "tu historia") { comparacion ->
                alcance.launch {
                    viewModel.marcarLenteUsada(fabulaId)
                    viewModel.guardarTramo(fabulaId, Tramo.HASTA_QUE_UN_DIA, comparacion)
                    navController.popBackStack()
                }
            }
        }

        composable(Rutas.SALA_PULIDO) {
            SalaPulidoScreen(
                onConfirmarPrensa = { resultado -> alcance.launch { viewModel.registrarEjercicioPrensa(resultado, true) } },
                onConfirmarFuelle = { resultado -> alcance.launch { viewModel.registrarEjercicioFuelle(resultado, true) } },
                onConfirmarCriba = { resultado, quitadas -> alcance.launch { viewModel.registrarEjercicioCriba(resultado, quitadas) } }
            )
        }

        composable(
            Rutas.AUDITORIO,
            arguments = listOf(navArgument("fabulaId") { type = NavType.LongType })
        ) { entrada ->
            val fabulaId = entrada.arguments?.getLong("fabulaId") ?: 0L
            val animales by viewModel.animales.collectAsState(emptyList())
            var tramos by remember { mutableStateOf<Map<Tramo, String>>(emptyMap()) }
            var aforo by remember { mutableStateOf(0) }
            LaunchedEffect(fabulaId) {
                tramos = viewModel.obtenerTramosDeFabula(fabulaId)
                aforo = viewModel.obtenerAforoAuditorio()
            }

            // Simplificación de esta sesión: sin volver a leer la FabulaEntity aquí,
            // se asume un caracter neutro si no hay animales cargados todavía.
            val caracterA = animales.firstOrNull()?.caracter?.let { Caracter.valueOf(it) } ?: Caracter.ASTUTO
            val caracterB = animales.getOrNull(1)?.caracter?.let { Caracter.valueOf(it) } ?: Caracter.CONFIADO

            if (tramos.isNotEmpty()) {
                AuditorioScreen(
                    tramos = tramos,
                    caracterA = caracterA,
                    caracterB = caracterB,
                    aforoActual = aforo,
                    piezasDisponibles = SemillaMoralejas.piezas,
                    onTerminar = { titulo, _ ->
                        alcance.launch {
                            viewModel.terminarFabula(fabulaId, titulo)
                            navController.navigate(Rutas.TALLER) { popUpTo(Rutas.TALLER) { inclusive = true } }
                        }
                    }
                )
            }
        }

        composable(Rutas.FABULARIO) {
            val fabulas by viewModel.fabulasTerminadas.collectAsState(emptyList())
            FabularioScreen(fabulas = fabulas, onAbrir = {})
        }

        composable(Rutas.GALERIA_VISITANTES) {
            val visitantes by viewModel.visitantes.collectAsState(emptyList())
            GaleriaVisitantesScreen(visitantes = visitantes)
        }

        composable(Rutas.CUADERNO_APRENDIZ) {
            val paginas by viewModel.paginasCuaderno.collectAsState(emptyList())
            CuadernoAprendizScreen(paginas = paginas, onElegirFrase = {})
        }

        composable(Rutas.RINCON_PRACTICA) {
            var sinTerminar by remember { mutableStateOf(listOf<pe.appmobile.fabricadehistorias.data.local.entity.FabulaEntity>()) }
            LaunchedEffect(Unit) { sinTerminar = viewModel.obtenerFabulasSinTerminar() }
            RinconPracticaScreen(
                fabulasAMedias = sinTerminar,
                onRetomar = { fabulaId -> navController.navigate(Rutas.mesaEsqueleto(fabulaId)) }
            )
        }

        composable(Rutas.AJUSTES) {
            AjustesScreen()
        }
    }
}
