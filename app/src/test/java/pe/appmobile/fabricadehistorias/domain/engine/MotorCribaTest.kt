package pe.appmobile.fabricadehistorias.domain.engine

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * La Criba: quitar lo repetido y lo que sobra. Solo marca palabras de
 * **contenido** repetidas de más — "el", "y", "que" se repiten todo el tiempo
 * en cualquier texto real y marcarlas sería ruido, no ayuda.
 */
class MotorCribaTest {

    @Test
    fun `detecta una palabra de contenido repetida tres veces o mas`() {
        val repetidas = MotorCriba.palabrasRepetidas("El zorro corrió, el zorro saltó y el zorro llegó")
        assertEquals(listOf("zorro"), repetidas)
    }

    @Test
    fun `las palabras vacias no cuentan como repetidas aunque se usen mucho`() {
        val repetidas = MotorCriba.palabrasRepetidas("El zorro y la tortuga y el cuy y el cóndor")
        assertTrue(repetidas.isEmpty())
    }

    @Test
    fun `una palabra usada solo dos veces no cuenta como repetida`() {
        val repetidas = MotorCriba.palabrasRepetidas("El zorro corrió. El zorro saltó.")
        assertTrue(repetidas.isEmpty())
    }

    @Test
    fun `tieneRepeticionExcesiva es verdadero si hay al menos una palabra repetida de mas`() {
        assertTrue(MotorCriba.tieneRepeticionExcesiva("El zorro corrió, el zorro saltó y el zorro llegó"))
    }

    @Test
    fun `tieneRepeticionExcesiva es falso en un texto variado`() {
        assertFalse(MotorCriba.tieneRepeticionExcesiva("El zorro cruzó el puente cubierto de neblina"))
    }

    @Test
    fun `agrupa singular y plural como la misma palabra al contar`() {
        // Verbos distintos a propósito (comió, jugaron, durmió), para aislar
        // que lo que se agrupa es "zorro" y no arrastrar también un verbo.
        val repetidas = MotorCriba.palabrasRepetidas("El zorro comió, los zorros jugaron y un zorro más durmió")
        assertEquals(listOf("zorro"), repetidas)
    }

    @Test
    fun `un texto vacio no tiene palabras repetidas`() {
        assertTrue(MotorCriba.palabrasRepetidas("").isEmpty())
        assertFalse(MotorCriba.tieneRepeticionExcesiva(""))
    }
}
