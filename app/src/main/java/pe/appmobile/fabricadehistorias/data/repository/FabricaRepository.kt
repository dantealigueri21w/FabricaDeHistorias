package pe.appmobile.fabricadehistorias.data.repository

import kotlinx.coroutines.flow.Flow
import pe.appmobile.fabricadehistorias.data.local.AppDatabase
import pe.appmobile.fabricadehistorias.data.local.entity.AnimalEntity
import pe.appmobile.fabricadehistorias.data.local.entity.EjercicioEntity
import pe.appmobile.fabricadehistorias.data.local.entity.EncargoEntity
import pe.appmobile.fabricadehistorias.data.local.entity.FabulaEntity
import pe.appmobile.fabricadehistorias.data.local.entity.InsigniaEntity
import pe.appmobile.fabricadehistorias.data.local.entity.IntentoEntity
import pe.appmobile.fabricadehistorias.data.local.entity.PaginaCuadernoEntity
import pe.appmobile.fabricadehistorias.data.local.entity.PerfilEntity
import pe.appmobile.fabricadehistorias.data.local.entity.RachaEntity
import pe.appmobile.fabricadehistorias.data.local.entity.TramoEntity
import pe.appmobile.fabricadehistorias.data.local.entity.VisitanteEntity
import pe.appmobile.fabricadehistorias.data.seed.SemillaAnimales
import pe.appmobile.fabricadehistorias.data.seed.SemillaEncargos
import pe.appmobile.fabricadehistorias.data.seed.SemillaInsignias
import pe.appmobile.fabricadehistorias.data.seed.SemillaVisitantes
import pe.appmobile.fabricadehistorias.domain.engine.MotorAuditorio
import pe.appmobile.fabricadehistorias.domain.engine.MotorProgreso
import pe.appmobile.fabricadehistorias.domain.model.EstadoJugador
import pe.appmobile.fabricadehistorias.domain.model.Insignia
import pe.appmobile.fabricadehistorias.domain.model.Tramo
import java.time.LocalDate

/**
 * Punto único de acceso a los datos. Orquesta los DAOs y llama a los motores
 * de dominio para decidir desbloqueos, aforo e insignias.
 */
class FabricaRepository(private val db: AppDatabase) {

    // ---------- Semilla ----------

    suspend fun sembrarSiHaceFalta() {
        if (db.animalDao().contar() == 0) db.animalDao().insertarTodos(SemillaAnimales.animales)
        if (db.visitanteDao().contar() == 0) db.visitanteDao().insertarTodos(SemillaVisitantes.visitantes)
        if (db.encargoDao().contar() == 0) db.encargoDao().insertarTodos(SemillaEncargos.encargos)
        if (db.insigniaDao().contar() == 0) db.insigniaDao().insertarTodas(SemillaInsignias.insignias)
        if (db.paginaCuadernoDao().contar() == 0) {
            val paginas = SemillaVisitantes.visitantes.map { PaginaCuadernoEntity(herramienta = it.herramienta) }
            db.paginaCuadernoDao().insertarTodas(paginas)
        }
    }

    // ---------- Perfil ----------

    fun observarPerfil(): Flow<PerfilEntity?> = db.perfilDao().observar()

    suspend fun guardarPerfil(alias: String, avatarId: Int) {
        db.perfilDao().guardar(PerfilEntity(alias = alias, avatarId = avatarId))
    }

    // ---------- Animales ----------

    fun observarAnimales(): Flow<List<AnimalEntity>> = db.animalDao().observarTodos()

    // ---------- Visitantes ----------

    fun observarVisitantes(): Flow<List<VisitanteEntity>> = db.visitanteDao().observarTodos()

    /** Se llama al llegar a una estación nueva (primera fábula) o al terminar una fábula. */
    suspend fun recibirSiguienteVisitante(): VisitanteEntity? {
        val siguiente = db.visitanteDao().obtenerSiguientePorRecibir() ?: return null
        db.visitanteDao().actualizar(
            siguiente.copy(recibido = true, fechaRecibidoEpochMillis = System.currentTimeMillis())
        )
        return siguiente
    }

    // ---------- Fábulas ----------

    suspend fun obtenerEncargoNuevo(): EncargoEntity? =
        db.encargoDao().obtenerUnoSinUsar() ?: db.encargoDao().obtenerUnoAlAzar()

    suspend fun crearFabula(animalAId: String, animalBId: String, encargoId: String?): Long =
        db.fabulaDao().crear(
            FabulaEntity(
                animalAId = animalAId,
                animalBId = animalBId,
                encargoId = encargoId,
                fechaCreacionEpochMillis = System.currentTimeMillis()
            )
        )

    suspend fun guardarTramo(fabulaId: Long, tramo: Tramo, texto: String) {
        db.tramoDao().guardar(TramoEntity(fabulaId = fabulaId, tipoTramo = tramo.name, texto = texto))
    }

    suspend fun obtenerTramosDeFabula(fabulaId: Long): Map<Tramo, String> =
        db.tramoDao().obtenerDeFabula(fabulaId).associate { Tramo.valueOf(it.tipoTramo) to it.texto }

    fun observarFabulasTerminadas(): Flow<List<FabulaEntity>> = db.fabulaDao().observarTerminadas()

    /** Marca la fábula como terminada, evalúa insignias nuevas y devuelve las ganadas en esta jugada. */
    suspend fun terminarFabula(fabulaId: Long, titulo: String): Set<Insignia> {
        val fabula = db.fabulaDao().obtenerPorId(fabulaId) ?: return emptySet()
        db.fabulaDao().actualizar(
            fabula.copy(titulo = titulo, terminada = true, fechaTerminadaEpochMillis = System.currentTimeMillis())
        )
        return evaluarInsignias()
    }

    suspend fun marcarLenteUsada(fabulaId: Long) {
        db.fabulaDao().obtenerPorId(fabulaId)?.let { db.fabulaDao().actualizar(it.copy(usoLente = true)) }
        db.intentoDao().registrar(
            IntentoEntity(fabulaId = fabulaId, tipo = "LENTE", fueCorrecto = true, fechaEpochMillis = System.currentTimeMillis())
        )
    }

    suspend fun marcarReglaDeTresCompleta(fabulaId: Long) {
        db.fabulaDao().obtenerPorId(fabulaId)?.let { db.fabulaDao().actualizar(it.copy(cumplioReglaDeTres = true)) }
    }

    suspend fun marcarBurladorBurlado(fabulaId: Long) {
        db.fabulaDao().obtenerPorId(fabulaId)?.let { db.fabulaDao().actualizar(it.copy(esBurladorBurlado = true)) }
    }

    suspend fun registrarIntentoMoraleja(fabulaId: Long, esPrimerIntento: Boolean, fueCorrecto: Boolean) {
        if (!esPrimerIntento) return
        db.intentoDao().registrar(
            IntentoEntity(fabulaId = fabulaId, tipo = "MORALEJA", fueCorrecto = fueCorrecto, fechaEpochMillis = System.currentTimeMillis())
        )
    }

    // ---------- Sala de Pulido ----------

    suspend fun registrarEjercicioPrensa(textoResultado: String, confirmado: Boolean) {
        db.ejercicioDao().registrar(
            EjercicioEntity(tipo = "PRENSA", textoResultado = textoResultado, confirmado = confirmado, cantidad = 1, fechaEpochMillis = System.currentTimeMillis())
        )
    }

    suspend fun registrarEjercicioFuelle(textoResultado: String, confirmado: Boolean) {
        db.ejercicioDao().registrar(
            EjercicioEntity(tipo = "FUELLE", textoResultado = textoResultado, confirmado = confirmado, cantidad = 1, fechaEpochMillis = System.currentTimeMillis())
        )
    }

    suspend fun registrarEjercicioCriba(textoResultado: String, palabrasQuitadas: Int) {
        db.ejercicioDao().registrar(
            EjercicioEntity(tipo = "CRIBA", textoResultado = textoResultado, confirmado = true, cantidad = palabrasQuitadas, fechaEpochMillis = System.currentTimeMillis())
        )
    }

    // ---------- Cuaderno del Aprendiz ----------

    fun observarPaginasCuaderno(): Flow<List<PaginaCuadernoEntity>> = db.paginaCuadernoDao().observarTodas()

    suspend fun guardarFraseEnPagina(herramienta: String, frase: String) {
        db.paginaCuadernoDao().actualizar(
            PaginaCuadernoEntity(herramienta = herramienta, fraseElegida = frase, fechaGuardadaEpochMillis = System.currentTimeMillis())
        )
    }

    // ---------- Auditorio, insignias y progreso ----------

    suspend fun obtenerAforoAuditorio(): Int = MotorAuditorio.aforo(db.fabulaDao().contarTerminadas())

    fun observarInsignias(): Flow<List<InsigniaEntity>> = db.insigniaDao().observarTodas()

    private suspend fun evaluarInsignias(): Set<Insignia> {
        val estado = EstadoJugador(
            fabulasTerminadas = db.fabulaDao().contarTerminadas(),
            visitantesRecibidos = db.visitanteDao().contarRecibidos(),
            paginasCuadernoLlenas = db.paginaCuadernoDao().contarLlenas(),
            aforoAuditorio = obtenerAforoAuditorio(),
            lenteUsadaEnFabulasDistintas = db.fabulaDao().contarConLenteUsada(),
            fusionesPrensaConfirmadas = db.ejercicioDao().sumarCantidadConfirmadaDeTipo("PRENSA"),
            palabrasQuitadasConCriba = db.ejercicioDao().sumarCantidadConfirmadaDeTipo("CRIBA"),
            expansionesFuelleConfirmadas = db.ejercicioDao().sumarCantidadConfirmadaDeTipo("FUELLE"),
            fabulasConReglaDeTresCompleta = db.fabulaDao().contarConReglaDeTres(),
            fabulasBurladorBurlado = db.fabulaDao().contarBurladorBurlado(),
            moralejasCorrectasPrimerIntento = db.intentoDao().contarCorrectosDeTipo("MORALEJA")
        )

        val ganadas = MotorProgreso.calcularInsigniasGanadas(estado)
        val yaObtenidas = db.insigniaDao().obtenerIdsGanadas().toSet()
        val nuevas = ganadas.filter { it.name !in yaObtenidas }

        nuevas.forEach { insignia ->
            val definicion = SemillaInsignias.insignias.first { it.id == insignia.name }
            db.insigniaDao().actualizar(
                definicion.copy(obtenida = true, fechaObtencionEpochMillis = System.currentTimeMillis())
            )
        }
        return nuevas.toSet()
    }

    // ---------- Racha ----------

    fun observarRacha(): Flow<RachaEntity?> = db.rachaDao().observar()

    suspend fun registrarSesionDeHoy() {
        val actual = db.rachaDao().obtener()
        val hoy = LocalDate.now()
        val ultima = actual?.ultimaFechaJuegoEpochDay?.let { LocalDate.ofEpochDay(it) }
        val nuevaRacha = MotorProgreso.actualizarRacha(actual?.rachaActual ?: 0, ultima, hoy)
        db.rachaDao().guardar(RachaEntity(rachaActual = nuevaRacha, ultimaFechaJuegoEpochDay = hoy.toEpochDay()))
    }
}
