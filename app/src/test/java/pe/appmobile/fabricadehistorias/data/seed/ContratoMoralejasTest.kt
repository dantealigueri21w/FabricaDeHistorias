package pe.appmobile.fabricadehistorias.data.seed

import org.junit.Assert.assertTrue
import org.junit.Test
import pe.appmobile.fabricadehistorias.domain.engine.MotorMoraleja

/**
 * Contrato entre las 24 piezas del Espejo y MotorMoraleja: cada INICIO tiene
 * que contener literalmente la palabra de su propio carácter, o el motor
 * rechazaría una moraleja armada correctamente por el niño. Esto ya atrapó un
 * error real antes de escribir la pantalla: varias piezas usaban una forma
 * verbal ("confía") en vez del adjetivo ("confiado"), y el motor no las
 * reconocía.
 */
class ContratoMoralejasTest {

    @Test
    fun `cada inicio corresponde de verdad con su propio caracter`() {
        SemillaMoralejas.piezas
            .filter { it.parte == ParteMoraleja.INICIO }
            .forEach { pieza ->
                assertTrue(
                    "\"${pieza.texto}\" no contiene la palabra de ${pieza.caracter}",
                    MotorMoraleja.correspondeConLosHechos(pieza.texto, listOf(pieza.caracter.name))
                )
            }
    }

    @Test
    fun `hay exactamente un inicio y un fin por cada uno de los 12 caracteres`() {
        val porCaracter = SemillaMoralejas.piezas.groupBy { it.caracter }
        assertTrue(porCaracter.size == 12)
        porCaracter.values.forEach { piezasDelCaracter ->
            assertTrue(piezasDelCaracter.count { it.parte == ParteMoraleja.INICIO } == 1)
            assertTrue(piezasDelCaracter.count { it.parte == ParteMoraleja.FIN } == 1)
        }
    }
}
