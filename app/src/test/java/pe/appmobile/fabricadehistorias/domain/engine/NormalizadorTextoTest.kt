package pe.appmobile.fabricadehistorias.domain.engine

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * El taller comprueba que el niño usó las piezas que le tocaron (el puente, la
 * neblina, el zorro). Si la comprobación fuera literal, escribir "los puentes"
 * contaría como error: un falso negativo le diría que se equivocó cuando escribió
 * bien. Estas pruebas fijan hasta dónde llega la tolerancia y dónde se corta.
 */
class NormalizadorTextoTest {

    @Test
    fun `normalizar quita tildes y pasa a minusculas`() {
        assertEquals("arbol", NormalizadorTexto.normalizar("Árbol"))
        assertEquals("neblina", NormalizadorTexto.normalizar("NEBLINA"))
        assertEquals("canon", NormalizadorTexto.normalizar("cañón"))
    }

    @Test
    fun `separa el texto en palabras ignorando la puntuacion`() {
        assertEquals(
            listOf("el", "zorro", "cruzo", "el", "puente"),
            NormalizadorTexto.palabras("¡El zorro cruzó el puente!")
        )
    }

    @Test
    fun `reconoce la palabra escrita tal cual`() {
        assertTrue(NormalizadorTexto.contienePalabra("El zorro miró el puente", "puente"))
    }

    @Test
    fun `reconoce la palabra aunque cambien tildes y mayusculas`() {
        assertTrue(NormalizadorTexto.contienePalabra("La NEBLINA tapaba todo", "neblina"))
        assertTrue(NormalizadorTexto.contienePalabra("Subio al camion", "camión"))
    }

    @Test
    fun `reconoce el plural en s y en es`() {
        assertTrue(NormalizadorTexto.contienePalabra("Cruzaron dos puentes", "puente"))
        assertTrue(NormalizadorTexto.contienePalabra("Los camiones pasaron", "camión"))
    }

    @Test
    fun `reconoce el diminutivo`() {
        assertTrue(NormalizadorTexto.contienePalabra("Vio un zorrito flaco", "zorro"))
        assertTrue(NormalizadorTexto.contienePalabra("Una casita de barro", "casa"))
    }

    @Test
    fun `reconoce una forma conjugada cuando la pieza es un verbo`() {
        assertTrue(NormalizadorTexto.contienePalabra("El cuy corrió hasta el cerro", "correr"))
        assertTrue(NormalizadorTexto.contienePalabra("La tortuga cruzaba el río", "cruzar"))
    }

    @Test
    fun `no cuenta una palabra que solo aparece dentro de otra`() {
        assertFalse(NormalizadorTexto.contienePalabra("Un paisaje hermoso", "oso"))
        assertFalse(NormalizadorTexto.contienePalabra("Se quedó solo", "sol"))
    }

    @Test
    fun `no cuenta una palabra que no esta`() {
        assertFalse(NormalizadorTexto.contienePalabra("El zorro miró el río", "puente"))
    }

    @Test
    fun `texto vacio no contiene ninguna palabra`() {
        assertFalse(NormalizadorTexto.contienePalabra("", "puente"))
        assertFalse(NormalizadorTexto.contienePalabra("   ", "puente"))
    }

    @Test
    fun `pieza vacia nunca cuenta como usada`() {
        assertFalse(NormalizadorTexto.contienePalabra("El zorro miró el puente", ""))
    }

    @Test
    fun `cuenta cuantas piezas de una lista se usaron`() {
        val usadas = NormalizadorTexto.piezasUsadas(
            texto = "El zorro cruzó los puentes con neblina",
            piezas = listOf("zorro", "puente", "neblina", "olla")
        )
        assertEquals(listOf("zorro", "puente", "neblina"), usadas)
    }

    @Test
    fun `una palabra muy corta se compara literal, sin tolerancia`() {
        // Con tres letras o menos, cualquier tolerancia genera falsos positivos.
        assertTrue(NormalizadorTexto.contienePalabra("Tomó el te caliente", "te"))
        assertFalse(NormalizadorTexto.contienePalabra("Tomó el tema", "te"))
    }
}
