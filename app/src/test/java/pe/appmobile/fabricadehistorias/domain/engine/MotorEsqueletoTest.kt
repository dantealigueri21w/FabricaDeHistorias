package pe.appmobile.fabricadehistorias.domain.engine

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import pe.appmobile.fabricadehistorias.domain.model.Tramo

/**
 * El esqueleto convierte "escribe una fábula" en seis frases cortas. El motor
 * comprueba que los seis tramos estén escritos y en su orden; nunca opina sobre
 * lo que dicen.
 */
class MotorEsqueletoTest {

    private fun fabulaCompleta() = mapOf(
        Tramo.ERASE_UNA_VEZ to "un zorro muy presumido",
        Tramo.TODOS_LOS_DIAS to "se paseaba por el puente",
        Tramo.HASTA_QUE_UN_DIA to "llego una neblina espesa",
        Tramo.POR_ESO to "el zorro se perdio dos veces",
        Tramo.HASTA_QUE_POR_FIN to "el cuy le mostro el camino",
        Tramo.Y_DESDE_ENTONCES to "el zorro saluda al cuy"
    )

    @Test
    fun `el orden narrativo es el de los seis tramos`() {
        assertEquals(
            listOf(
                Tramo.ERASE_UNA_VEZ,
                Tramo.TODOS_LOS_DIAS,
                Tramo.HASTA_QUE_UN_DIA,
                Tramo.POR_ESO,
                Tramo.HASTA_QUE_POR_FIN,
                Tramo.Y_DESDE_ENTONCES
            ),
            MotorEsqueleto.ordenNarrativo()
        )
    }

    @Test
    fun `una fabula con los seis tramos escritos esta completa`() {
        assertTrue(MotorEsqueleto.estaCompleto(fabulaCompleta()))
        assertTrue(MotorEsqueleto.tramosFaltantes(fabulaCompleta()).isEmpty())
    }

    @Test
    fun `un tramo que falta se reporta como faltante`() {
        val sinFinal = fabulaCompleta() - Tramo.Y_DESDE_ENTONCES

        assertEquals(listOf(Tramo.Y_DESDE_ENTONCES), MotorEsqueleto.tramosFaltantes(sinFinal))
        assertFalse(MotorEsqueleto.estaCompleto(sinFinal))
    }

    @Test
    fun `un tramo en blanco cuenta como faltante`() {
        val enBlanco = fabulaCompleta() + (Tramo.POR_ESO to "    ")

        assertEquals(listOf(Tramo.POR_ESO), MotorEsqueleto.tramosFaltantes(enBlanco))
    }

    @Test
    fun `un tramo con menos de tres palabras cuenta como faltante`() {
        val muyCorto = fabulaCompleta() + (Tramo.HASTA_QUE_UN_DIA to "llovio")

        assertEquals(listOf(Tramo.HASTA_QUE_UN_DIA), MotorEsqueleto.tramosFaltantes(muyCorto))
    }

    @Test
    fun `los faltantes se devuelven en orden narrativo, no en el orden del mapa`() {
        val casiVacia = mapOf(Tramo.POR_ESO to "el zorro se perdio dos veces")

        assertEquals(
            listOf(
                Tramo.ERASE_UNA_VEZ,
                Tramo.TODOS_LOS_DIAS,
                Tramo.HASTA_QUE_UN_DIA,
                Tramo.HASTA_QUE_POR_FIN,
                Tramo.Y_DESDE_ENTONCES
            ),
            MotorEsqueleto.tramosFaltantes(casiVacia)
        )
    }

    @Test
    fun `una fabula vacia tiene los seis tramos faltantes`() {
        assertEquals(6, MotorEsqueleto.tramosFaltantes(emptyMap()).size)
        assertFalse(MotorEsqueleto.estaCompleto(emptyMap()))
    }

    @Test
    fun `reconoce cuando los tramos estan puestos en su orden`() {
        assertTrue(MotorEsqueleto.estaEnOrden(MotorEsqueleto.ordenNarrativo()))
    }

    @Test
    fun `detecta los tramos puestos en desorden`() {
        val alReves = MotorEsqueleto.ordenNarrativo().reversed()

        assertFalse(MotorEsqueleto.estaEnOrden(alReves))
    }

    @Test
    fun `una lista incompleta no cuenta como ordenada`() {
        val faltaUno = MotorEsqueleto.ordenNarrativo().dropLast(1)

        assertFalse(MotorEsqueleto.estaEnOrden(faltaUno))
    }
}
