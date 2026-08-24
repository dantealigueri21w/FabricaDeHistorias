package pe.appmobile.fabricadehistorias.domain.engine

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import pe.appmobile.fabricadehistorias.domain.model.Caracter
import pe.appmobile.fabricadehistorias.domain.model.FuerzaChoque
import pe.appmobile.fabricadehistorias.domain.model.TipoChoque

/**
 * La Rueda de Animales enseña que el conflicto sale del contraste. El motor nunca
 * rechaza una pareja —eso sería decirle al niño que eligió mal—: dice qué tan
 * sola se va a contar la historia con esos dos.
 */
class MotorPersonajesTest {

    @Test
    fun `dos caracteres opuestos dan el choque mas fuerte`() {
        val choque = MotorPersonajes.choqueEntre(Caracter.ASTUTO, Caracter.CONFIADO)

        assertEquals(TipoChoque.OPUESTOS, choque.tipo)
        assertEquals(FuerzaChoque.FUERTE, choque.fuerza)
    }

    @Test
    fun `el orden de los dos animales no cambia el choque`() {
        assertEquals(
            MotorPersonajes.choqueEntre(Caracter.PRESUMIDO, Caracter.HUMILDE),
            MotorPersonajes.choqueEntre(Caracter.HUMILDE, Caracter.PRESUMIDO)
        )
    }

    @Test
    fun `el mismo caracter en los dos es rivalidad, no falta de conflicto`() {
        val choque = MotorPersonajes.choqueEntre(Caracter.APURADO, Caracter.APURADO)

        assertEquals(TipoChoque.IGUALES, choque.tipo)
        assertEquals(FuerzaChoque.MEDIA, choque.fuerza)
    }

    @Test
    fun `dos caracteres sin relacion siguen sirviendo, con menos fuerza`() {
        val choque = MotorPersonajes.choqueEntre(Caracter.ASTUTO, Caracter.TRABAJADOR)

        assertEquals(TipoChoque.DISTINTOS, choque.tipo)
        assertEquals(FuerzaChoque.DEBIL, choque.fuerza)
    }

    @Test
    fun `todos los caracteres tienen su opuesto y es reciproco`() {
        Caracter.entries.forEach { caracter ->
            assertTrue(
                "$caracter no es opuesto reciproco de ${caracter.opuesto}",
                caracter.opuesto.esOpuestoDe(caracter)
            )
        }
    }

    @Test
    fun `ningun caracter es opuesto de si mismo`() {
        Caracter.entries.forEach { caracter ->
            assertFalse(caracter.esOpuestoDe(caracter))
        }
    }

    @Test
    fun `cualquier pareja del taller produce algun choque`() {
        // Nunca puede haber una pareja que deje al niño sin salida.
        Caracter.entries.forEach { uno ->
            Caracter.entries.forEach { otro ->
                val choque = MotorPersonajes.choqueEntre(uno, otro)
                assertTrue(choque.fuerza in FuerzaChoque.entries)
            }
        }
    }
}
