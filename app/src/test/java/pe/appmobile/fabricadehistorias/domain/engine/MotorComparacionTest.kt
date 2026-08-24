package pe.appmobile.fabricadehistorias.domain.engine

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * La Lente enseña metáfora y comparación: el niño arrastra la lente sobre un
 * objeto de su escena, elige en qué se convierte, y escribe la frase. El motor
 * no juzga si la comparación es bonita — solo comprueba que nombre las dos
 * cosas y que use un conector real de comparación, no que las mencione sueltas.
 */
class MotorComparacionTest {

    @Test
    fun `reconoce el conector como`() {
        assertTrue(MotorComparacion.tieneConector("El puente era como una serpiente dormida"))
    }

    @Test
    fun `reconoce el conector parece, incluso conjugado`() {
        assertTrue(MotorComparacion.tieneConector("El puente parece una serpiente"))
        assertTrue(MotorComparacion.tieneConector("El puente parecía una serpiente"))
    }

    @Test
    fun `reconoce un conector de dos palabras`() {
        assertTrue(MotorComparacion.tieneConector("El puente, igual que una serpiente, se enroscaba"))
    }

    @Test
    fun `una frase sin conector no cuenta como comparacion`() {
        assertFalse(MotorComparacion.tieneConector("El puente y la serpiente estaban ahi"))
    }

    @Test
    fun `una comparacion valida nombra los dos terminos y usa un conector`() {
        assertTrue(
            MotorComparacion.esComparacionValida(
                texto = "El puente colgante parecía una serpiente dormida sobre el río",
                terminoA = "puente",
                terminoB = "serpiente"
            )
        )
    }

    @Test
    fun `no es valida si falta uno de los dos terminos aunque haya conector`() {
        assertFalse(
            MotorComparacion.esComparacionValida(
                texto = "El puente colgante parecía algo dormido sobre el río",
                terminoA = "puente",
                terminoB = "serpiente"
            )
        )
    }

    @Test
    fun `no es valida si nombra los dos terminos pero sin conector`() {
        assertFalse(
            MotorComparacion.esComparacionValida(
                texto = "El puente y la serpiente estaban los dos sobre el río",
                terminoA = "puente",
                terminoB = "serpiente"
            )
        )
    }

    @Test
    fun `tolera mayusculas, tildes y plural en los terminos, igual que el normalizador`() {
        assertTrue(
            MotorComparacion.esComparacionValida(
                texto = "Los PUENTES parecían serpientes dormidas",
                terminoA = "puente",
                terminoB = "serpiente"
            )
        )
    }

    @Test
    fun `un texto vacio nunca es una comparacion valida`() {
        assertFalse(MotorComparacion.esComparacionValida("", "puente", "serpiente"))
    }
}
