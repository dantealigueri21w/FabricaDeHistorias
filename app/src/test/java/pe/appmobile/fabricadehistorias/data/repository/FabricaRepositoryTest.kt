package pe.appmobile.fabricadehistorias.data.repository

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import pe.appmobile.fabricadehistorias.data.local.AppDatabase
import pe.appmobile.fabricadehistorias.domain.model.Insignia
import pe.appmobile.fabricadehistorias.domain.model.Tramo

@RunWith(RobolectricTestRunner::class)
class FabricaRepositoryTest {

    private lateinit var db: AppDatabase
    private lateinit var repositorio: FabricaRepository

    @Before
    fun crearBaseEnMemoria() {
        db = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        repositorio = FabricaRepository(db)
    }

    @Test
    fun `sembrarSiHaceFalta carga los 18 animales`() = runTest {
        repositorio.sembrarSiHaceFalta()
        assertEquals(18, db.animalDao().contar())
    }

    @Test
    fun `sembrarSiHaceFalta carga los 12 visitantes`() = runTest {
        repositorio.sembrarSiHaceFalta()
        assertEquals(12, db.visitanteDao().contar())
    }

    @Test
    fun `sembrarSiHaceFalta carga los 24 encargos`() = runTest {
        repositorio.sembrarSiHaceFalta()
        assertEquals(24, db.encargoDao().contar())
    }

    @Test
    fun `sembrarSiHaceFalta carga las 12 insignias`() = runTest {
        repositorio.sembrarSiHaceFalta()
        assertEquals(12, db.insigniaDao().contar())
    }

    @Test
    fun `sembrarSiHaceFalta carga las 12 paginas del cuaderno, vacias`() = runTest {
        repositorio.sembrarSiHaceFalta()
        assertEquals(12, db.paginaCuadernoDao().contar())
        assertEquals(0, db.paginaCuadernoDao().contarLlenas())
    }

    @Test
    fun `sembrar dos veces no duplica los datos`() = runTest {
        repositorio.sembrarSiHaceFalta()
        repositorio.sembrarSiHaceFalta()
        assertEquals(18, db.animalDao().contar())
    }

    @Test
    fun `recibirSiguienteVisitante entrega en orden y lo marca recibido`() = runTest {
        repositorio.sembrarSiHaceFalta()
        val primero = repositorio.recibirSiguienteVisitante()
        assertEquals("no_isidro", primero?.id)

        val segundo = repositorio.recibirSiguienteVisitante()
        assertEquals("fortunato_buhonero", segundo?.id)
        assertEquals(2, db.visitanteDao().contarRecibidos())
    }

    @Test
    fun `crear una fabula y guardar sus tramos los deja recuperables`() = runTest {
        repositorio.sembrarSiHaceFalta()
        val fabulaId = repositorio.crearFabula("zorro_andino", "cuy", "F1")

        repositorio.guardarTramo(fabulaId, Tramo.ERASE_UNA_VEZ, "un zorro muy confiado")
        repositorio.guardarTramo(fabulaId, Tramo.TODOS_LOS_DIAS, "se paseaba por el puente")

        val tramos = repositorio.obtenerTramosDeFabula(fabulaId)
        assertEquals("un zorro muy confiado", tramos[Tramo.ERASE_UNA_VEZ])
        assertEquals("se paseaba por el puente", tramos[Tramo.TODOS_LOS_DIAS])
    }

    @Test
    fun `guardar el mismo tramo dos veces reemplaza el texto, no lo duplica`() = runTest {
        repositorio.sembrarSiHaceFalta()
        val fabulaId = repositorio.crearFabula("zorro_andino", "cuy", "F1")

        repositorio.guardarTramo(fabulaId, Tramo.ERASE_UNA_VEZ, "version uno")
        repositorio.guardarTramo(fabulaId, Tramo.ERASE_UNA_VEZ, "version dos, mejor")

        val tramos = repositorio.obtenerTramosDeFabula(fabulaId)
        assertEquals(1, tramos.size)
        assertEquals("version dos, mejor", tramos[Tramo.ERASE_UNA_VEZ])
    }

    @Test
    fun `terminarFabula marca terminada y otorga la insignia de primera fabula`() = runTest {
        repositorio.sembrarSiHaceFalta()
        val fabulaId = repositorio.crearFabula("zorro_andino", "cuy", "F1")

        val ganadas = repositorio.terminarFabula(fabulaId, "El zorro y el cuy")

        assertTrue(Insignia.PRIMERA_FABULA in ganadas)
        val fabula = db.fabulaDao().obtenerPorId(fabulaId)
        assertTrue(fabula!!.terminada)
        assertNotNull(fabula.fechaTerminadaEpochMillis)
    }

    @Test
    fun `una insignia ya ganada no se vuelve a otorgar en la siguiente fabula`() = runTest {
        repositorio.sembrarSiHaceFalta()
        val primera = repositorio.crearFabula("zorro_andino", "cuy", "F1")
        val gananciasPrimera = repositorio.terminarFabula(primera, "Uno")
        assertTrue(Insignia.PRIMERA_FABULA in gananciasPrimera)

        val segunda = repositorio.crearFabula("cuy", "zorro_andino", "F2")
        val gananciasSegunda = repositorio.terminarFabula(segunda, "Dos")
        assertTrue(Insignia.PRIMERA_FABULA !in gananciasSegunda)
    }

    @Test
    fun `marcarLenteUsada actualiza la fabula, y solo cuenta para la insignia si la fabula termina`() = runTest {
        repositorio.sembrarSiHaceFalta()
        val fabulaId = repositorio.crearFabula("zorro_andino", "cuy", "F1")

        repositorio.marcarLenteUsada(fabulaId)

        val fabula = db.fabulaDao().obtenerPorId(fabulaId)
        assertTrue(fabula!!.usoLente)
        // Todavia no termino: no cuenta para Ojo de Caballero, igual que
        // Regla de Tres y Burlador Burlado exigen una fabula COMPLETA, no a medias.
        assertEquals(0, db.fabulaDao().contarConLenteUsada())

        repositorio.terminarFabula(fabulaId, "El zorro y el cuy")
        assertEquals(1, db.fabulaDao().contarConLenteUsada())
    }

    @Test
    fun `registrar un ejercicio de prensa confirmado suma a la cantidad de la insignia`() = runTest {
        repositorio.sembrarSiHaceFalta()
        repeat(10) { repositorio.registrarEjercicioPrensa("una frase fundida", confirmado = true) }
        repositorio.registrarEjercicioPrensa("otra sin confirmar", confirmado = false)

        assertEquals(10, db.ejercicioDao().sumarCantidadConfirmadaDeTipo("PRENSA"))
    }

    @Test
    fun `registrar ejercicios de criba suma las palabras quitadas, no los usos`() = runTest {
        repositorio.sembrarSiHaceFalta()
        repositorio.registrarEjercicioCriba("texto limpio", palabrasQuitadas = 8)
        repositorio.registrarEjercicioCriba("otro texto limpio", palabrasQuitadas = 7)

        assertEquals(15, db.ejercicioDao().sumarCantidadConfirmadaDeTipo("CRIBA"))
    }

    @Test
    fun `guardarFraseEnPagina llena esa pagina del cuaderno`() = runTest {
        repositorio.sembrarSiHaceFalta()
        repositorio.guardarFraseEnPagina("La Rueda de Animales", "El cuy fue más astuto que el zorro")

        assertEquals(1, db.paginaCuadernoDao().contarLlenas())
    }

    @Test
    fun `el aforo del auditorio crece con las fabulas terminadas reales`() = runTest {
        repositorio.sembrarSiHaceFalta()
        assertEquals(0, repositorio.obtenerAforoAuditorio())

        val fabulaId = repositorio.crearFabula("zorro_andino", "cuy", "F1")
        repositorio.terminarFabula(fabulaId, "Uno")

        assertEquals(3, repositorio.obtenerAforoAuditorio())
    }

    @Test
    fun `registrarSesionDeHoy crea la racha en 1 la primera vez`() = runTest {
        repositorio.registrarSesionDeHoy()
        assertEquals(1, db.rachaDao().obtener()?.rachaActual)
    }

    @Test
    fun `obtenerEncargoNuevo devuelve uno de los 24 sembrados`() = runTest {
        repositorio.sembrarSiHaceFalta()
        val encargo = repositorio.obtenerEncargoNuevo()
        assertNotNull(encargo)
        assertTrue(encargo!!.id.isNotBlank())
    }
}
