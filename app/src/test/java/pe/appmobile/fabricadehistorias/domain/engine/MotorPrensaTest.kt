package pe.appmobile.fabricadehistorias.domain.engine

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * La Prensa: combinar dos oraciones cortas en una sola (sentence combining,
 * Graham & Perin 2007, tamaño de efecto 0.50). El motor no evalúa estilo —
 * comprueba que la frase fundida conserve el contenido real de las dos
 * originales y que las una con un solo conector, no que las deje pegadas con
 * un punto en medio.
 */
class MotorPrensaTest {

    @Test
    fun `una fusion valida conserva el contenido de las dos frases originales`() {
        assertTrue(
            MotorPrensa.esFusionValida(
                fraseA = "El zorro cruzó el puente",
                fraseB = "El puente estaba cubierto de neblina",
                fusion = "El zorro cruzó el puente cubierto de neblina"
            )
        )
    }

    @Test
    fun `usa un conector real de union`() {
        assertTrue(MotorPrensa.tieneConector("El zorro cruzó el puente porque tenía prisa"))
        assertTrue(MotorPrensa.tieneConector("El zorro, que tenía prisa, cruzó el puente"))
        assertTrue(MotorPrensa.tieneConector("El zorro cruzó el puente y llegó tarde igual"))
    }

    @Test
    fun `dos frases sueltas con un punto en medio no tienen conector`() {
        assertFalse(MotorPrensa.tieneConector("El zorro cruzó el puente. Tenía prisa"))
    }

    @Test
    fun `no es fusion valida si le falta el contenido de una de las dos frases`() {
        assertFalse(
            MotorPrensa.esFusionValida(
                fraseA = "El zorro cruzó el puente",
                fraseB = "El puente estaba cubierto de neblina",
                fusion = "El zorro cruzó el puente porque tenía prisa"
            )
        )
    }

    @Test
    fun `no es fusion valida si solo pega las dos frases con un punto`() {
        assertFalse(
            MotorPrensa.esFusionValida(
                fraseA = "El zorro cruzó el puente",
                fraseB = "El puente estaba cubierto de neblina",
                fusion = "El zorro cruzó el puente. El puente estaba cubierto de neblina"
            )
        )
    }

    @Test
    fun `tolera que la fusion use plural o conjugue distinto a las frases originales`() {
        assertTrue(
            MotorPrensa.esFusionValida(
                fraseA = "El zorro cruzó el puente",
                fraseB = "Los puentes tenían neblina",
                fusion = "Los zorros cruzaron los puentes con neblina"
            )
        )
    }

    @Test
    fun `una fusion vacia nunca es valida`() {
        assertFalse(
            MotorPrensa.esFusionValida(
                fraseA = "El zorro cruzó el puente",
                fraseB = "El puente estaba cubierto de neblina",
                fusion = ""
            )
        )
    }
}
