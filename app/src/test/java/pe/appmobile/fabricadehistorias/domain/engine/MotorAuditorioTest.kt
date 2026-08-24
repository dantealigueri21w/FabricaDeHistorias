package pe.appmobile.fabricadehistorias.domain.engine

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import pe.appmobile.fabricadehistorias.domain.model.Tramo

/**
 * El Auditorio: aforo y reacciones, calculados desde lo que el niño ya
 * escribió y guardó — nunca un contador suelto. No opina de la prosa, apoya en
 * MotorEsqueleto (completo) y MotorCriba (repetición) para observaciones
 * mecánicas y verificables.
 */
class MotorAuditorioTest {

    private fun fabulaCompleta() = mapOf(
        Tramo.ERASE_UNA_VEZ to "un zorro muy presumido",
        Tramo.TODOS_LOS_DIAS to "se paseaba por el puente",
        Tramo.HASTA_QUE_UN_DIA to "llego una neblina espesa",
        Tramo.POR_ESO to "el zorro se perdio dos veces",
        Tramo.HASTA_QUE_POR_FIN to "el cuy le mostro el camino",
        Tramo.Y_DESDE_ENTONCES to "el zorro saluda al cuy siempre"
    )

    @Test
    fun `el aforo crece con las fabulas terminadas, desde cero`() {
        assertEquals(0, MotorAuditorio.aforo(0))
        assertEquals(3, MotorAuditorio.aforo(1))
        assertEquals(21, MotorAuditorio.aforo(7))
    }

    @Test
    fun `una fabula completa y sin repeticiones no genera observaciones`() {
        assertTrue(MotorAuditorio.observaciones(fabulaCompleta()).isEmpty())
    }

    @Test
    fun `un tramo vacio genera una observacion sobre tramos sin terminar`() {
        val incompleta = fabulaCompleta() - Tramo.Y_DESDE_ENTONCES
        val obs = MotorAuditorio.observaciones(incompleta)
        assertTrue(obs.any { it.contains("tramo", ignoreCase = true) })
    }

    @Test
    fun `palabras repetidas generan una observacion que las nombra`() {
        val conRepeticion = fabulaCompleta() +
            (Tramo.Y_DESDE_ENTONCES to "el zorro el zorro el zorro se fue")
        val obs = MotorAuditorio.observaciones(conRepeticion)
        assertTrue(obs.any { it.contains("zorro", ignoreCase = true) })
    }

    @Test
    fun `aplaudeFuerte es verdadero solo cuando no hay ninguna observacion`() {
        assertTrue(MotorAuditorio.aplaudeFuerte(fabulaCompleta()))
    }

    @Test
    fun `aplaudeFuerte es falso si la fabula esta incompleta`() {
        assertFalse(MotorAuditorio.aplaudeFuerte(fabulaCompleta() - Tramo.POR_ESO))
    }

    @Test
    fun `una fabula vacia nunca aplaude fuerte`() {
        assertFalse(MotorAuditorio.aplaudeFuerte(emptyMap()))
    }
}
