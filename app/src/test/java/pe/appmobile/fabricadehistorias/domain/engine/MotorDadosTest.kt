package pe.appmobile.fabricadehistorias.domain.engine

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import pe.appmobile.fabricadehistorias.domain.model.CaraDado
import pe.appmobile.fabricadehistorias.domain.model.TipoDado
import pe.appmobile.fabricadehistorias.domain.model.Tirada
import kotlin.random.Random

/**
 * El molino reparte las piezas con las que hay que escribir. El azar reparte,
 * pero no resuelve: el niño puede fijar una pieza que le gustó y volver a lanzar
 * las otras una vez.
 */
class MotorDadosTest {

    private val caras = listOf(
        CaraDado(TipoDado.LUGAR, "el puente colgante", "puente"),
        CaraDado(TipoDado.LUGAR, "la chacra", "chacra"),
        CaraDado(TipoDado.LUGAR, "el mercado", "mercado"),
        CaraDado(TipoDado.OBJETO, "una olla", "olla"),
        CaraDado(TipoDado.OBJETO, "un sombrero", "sombrero"),
        CaraDado(TipoDado.OBJETO, "una soga", "soga"),
        CaraDado(TipoDado.PROBLEMA, "se levanto la neblina", "neblina"),
        CaraDado(TipoDado.PROBLEMA, "se acabo el agua", "agua"),
        CaraDado(TipoDado.PROBLEMA, "alguien llego tarde", "tarde")
    )

    @Test
    fun `una tirada trae una cara de cada tipo de dado`() {
        val tirada = MotorDados.lanzar(caras, Random(1))

        assertEquals(3, tirada.caras.size)
        assertEquals(
            setOf(TipoDado.LUGAR, TipoDado.OBJETO, TipoDado.PROBLEMA),
            tirada.caras.map { it.tipo }.toSet()
        )
    }

    @Test
    fun `la misma semilla da la misma tirada`() {
        assertEquals(
            MotorDados.lanzar(caras, Random(7)).caras,
            MotorDados.lanzar(caras, Random(7)).caras
        )
    }

    @Test
    fun `una tirada nueva empieza sin nada fijado`() {
        assertTrue(MotorDados.lanzar(caras, Random(3)).fijadas.isEmpty())
    }

    @Test
    fun `fijar marca solo ese tipo de dado`() {
        val tirada = MotorDados.lanzar(caras, Random(2))

        val conLugarFijado = MotorDados.fijar(tirada, TipoDado.LUGAR)

        assertEquals(setOf(TipoDado.LUGAR), conLugarFijado.fijadas)
    }

    @Test
    fun `quitarFijado le quita la fijacion a ese tipo de dado`() {
        val tirada = MotorDados.fijar(MotorDados.lanzar(caras, Random(2)), TipoDado.LUGAR)

        val sinFijar = MotorDados.quitarFijado(tirada, TipoDado.LUGAR)

        assertTrue(sinFijar.fijadas.isEmpty())
    }

    @Test
    fun `quitarFijado no afecta a otros tipos fijados`() {
        var tirada = MotorDados.lanzar(caras, Random(2))
        tirada = MotorDados.fijar(tirada, TipoDado.LUGAR)
        tirada = MotorDados.fijar(tirada, TipoDado.OBJETO)

        val sinLugar = MotorDados.quitarFijado(tirada, TipoDado.LUGAR)

        assertEquals(setOf(TipoDado.OBJETO), sinLugar.fijadas)
    }

    @Test
    fun `relanzar conserva la cara fijada`() {
        val tirada = MotorDados.fijar(MotorDados.lanzar(caras, Random(5)), TipoDado.OBJETO)
        val objetoOriginal = tirada.caras.first { it.tipo == TipoDado.OBJETO }

        val nueva = MotorDados.relanzarNoFijados(tirada, caras, Random(99))

        assertEquals(objetoOriginal, nueva.caras.first { it.tipo == TipoDado.OBJETO })
    }

    @Test
    fun `relanzar con todo fijado devuelve la misma tirada`() {
        var tirada = MotorDados.lanzar(caras, Random(4))
        TipoDado.entries.forEach { tirada = MotorDados.fijar(tirada, it) }

        val nueva = MotorDados.relanzarNoFijados(tirada, caras, Random(1234))

        assertEquals(tirada.caras.toSet(), nueva.caras.toSet())
    }

    @Test
    fun `las piezas que el nino no uso se reportan como faltantes`() {
        val tirada = Tirada(
            listOf(
                CaraDado(TipoDado.LUGAR, "el puente colgante", "puente"),
                CaraDado(TipoDado.OBJETO, "una olla", "olla"),
                CaraDado(TipoDado.PROBLEMA, "se levanto la neblina", "neblina")
            )
        )

        val faltantes = MotorDados.piezasFaltantes("El zorro cruzo el puente con su olla", tirada)

        assertEquals(listOf("neblina"), faltantes.map { it.palabraClave })
    }

    @Test
    fun `usar la pieza en plural cuenta como usada`() {
        val tirada = Tirada(listOf(CaraDado(TipoDado.LUGAR, "el puente colgante", "puente")))

        assertTrue(MotorDados.piezasFaltantes("Cruzaron los puentes", tirada).isEmpty())
    }

    @Test
    fun `si el texto esta vacio faltan todas las piezas`() {
        val tirada = MotorDados.lanzar(caras, Random(8))

        assertEquals(3, MotorDados.piezasFaltantes("", tirada).size)
    }

    @Test
    fun `dos semillas distintas pueden dar tiradas distintas`() {
        // No es determinismo lo que se prueba aquí, sino que el molino de verdad varía.
        val distintas = (1..20).map { MotorDados.lanzar(caras, Random(it.toLong())).caras }.toSet()

        assertNotEquals(1, distintas.size)
    }
}
