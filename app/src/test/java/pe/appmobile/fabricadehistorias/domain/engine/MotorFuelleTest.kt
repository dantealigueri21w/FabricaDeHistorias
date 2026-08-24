package pe.appmobile.fabricadehistorias.domain.engine

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * El Fuelle: expandir una frase plana con un detalle (dónde, cuándo, cómo). El
 * motor comprueba dos cosas mecánicas: que el contenido original siga ahí (no
 * que se haya reemplazado por otra cosa) y que de verdad se haya agregado algo
 * nuevo — no solo reordenado las mismas palabras.
 */
class MotorFuelleTest {

    @Test
    fun `una expansion valida conserva lo original y agrega un detalle nuevo`() {
        assertTrue(
            MotorFuelle.esExpansionValida(
                fraseOriginal = "El zorro cruzó el puente",
                fraseExpandida = "El zorro cruzó el puente de madera al anochecer"
            )
        )
    }

    @Test
    fun `no es valida si repite la frase original sin agregar nada`() {
        assertFalse(
            MotorFuelle.esExpansionValida(
                fraseOriginal = "El zorro cruzó el puente",
                fraseExpandida = "El zorro cruzó el puente"
            )
        )
    }

    @Test
    fun `no es valida si pierde contenido original en vez de expandirlo`() {
        assertFalse(
            MotorFuelle.esExpansionValida(
                fraseOriginal = "El zorro cruzó el puente",
                fraseExpandida = "El zorro cruzó el río al anochecer"
            )
        )
    }

    @Test
    fun `no cuenta como expansion solo reordenar las mismas palabras`() {
        assertFalse(
            MotorFuelle.esExpansionValida(
                fraseOriginal = "El zorro cruzó el puente",
                fraseExpandida = "El puente lo cruzó el zorro"
            )
        )
    }

    @Test
    fun `tolera plural y conjugacion distinta mientras agregue contenido real`() {
        assertTrue(
            MotorFuelle.esExpansionValida(
                fraseOriginal = "La tortuga cruzó el río",
                fraseExpandida = "La tortuga cruzó el río lentamente, antes del amanecer"
            )
        )
    }

    @Test
    fun `tieneDetalleNuevo detecta contenido que no estaba en la frase original`() {
        assertTrue(
            MotorFuelle.tieneDetalleNuevo("El zorro cruzó el puente", "El zorro cruzó el puente de piedra")
        )
        assertFalse(
            MotorFuelle.tieneDetalleNuevo("El zorro cruzó el puente", "El puente lo cruzó el zorro")
        )
    }

    @Test
    fun `una expansion vacia nunca es valida`() {
        assertFalse(MotorFuelle.esExpansionValida("El zorro cruzó el puente", ""))
    }
}
