package pe.appmobile.fabricadehistorias.domain.engine

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * El Espejo de la Moraleja: que la enseñanza corresponda a lo que pasó, no que
 * sea una frase bonita suelta. El motor comprueba que la moraleja mencione de
 * verdad los rasgos o personajes de la fábula — nunca su calidad literaria.
 */
class MotorMoralejaTest {

    @Test
    fun `corresponde si menciona al menos una palabra clave de los hechos`() {
        assertTrue(
            MotorMoraleja.correspondeConLosHechos(
                moraleja = "Ser muy confiado puede salir caro",
                palabrasClave = listOf("confiado", "zorro")
            )
        )
    }

    @Test
    fun `no corresponde si no menciona ninguna palabra clave`() {
        assertFalse(
            MotorMoraleja.correspondeConLosHechos(
                moraleja = "La vida a veces es bonita",
                palabrasClave = listOf("confiado", "zorro")
            )
        )
    }

    @Test
    fun `tolera plural y mayusculas en las palabras clave`() {
        assertTrue(
            MotorMoraleja.correspondeConLosHechos(
                moraleja = "Los CONFIADOS a veces pierden",
                palabrasClave = listOf("confiado")
            )
        )
    }

    @Test
    fun `una moraleja vacia nunca corresponde`() {
        assertFalse(
            MotorMoraleja.correspondeConLosHechos(
                moraleja = "",
                palabrasClave = listOf("confiado")
            )
        )
    }

    @Test
    fun `exige el minimo de coincidencias pedido, no solo una`() {
        val clave = listOf("zorro", "confiado", "puente")

        assertFalse(
            MotorMoraleja.correspondeConLosHechos(
                moraleja = "El zorro se fue a casa",
                palabrasClave = clave,
                minimoCoincidencias = 2
            )
        )
        assertTrue(
            MotorMoraleja.correspondeConLosHechos(
                moraleja = "El zorro confiado se fue a casa",
                palabrasClave = clave,
                minimoCoincidencias = 2
            )
        )
    }

    @Test
    fun `sin palabras clave que pedir, cualquier moraleja no vacia corresponde`() {
        assertTrue(MotorMoraleja.correspondeConLosHechos("Cualquier cosa", emptyList()))
    }
}
