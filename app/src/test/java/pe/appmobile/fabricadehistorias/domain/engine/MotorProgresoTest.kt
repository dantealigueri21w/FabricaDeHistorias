package pe.appmobile.fabricadehistorias.domain.engine

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import pe.appmobile.fabricadehistorias.domain.model.EstadoJugador
import pe.appmobile.fabricadehistorias.domain.model.Insignia
import java.time.LocalDate

class MotorProgresoTest {

    @Test
    fun `un jugador recien creado no tiene ninguna insignia`() {
        assertTrue(MotorProgreso.calcularInsigniasGanadas(EstadoJugador()).isEmpty())
    }

    @Test
    fun `primera fabula se gana con la primera fabula terminada`() {
        val ganadas = MotorProgreso.calcularInsigniasGanadas(EstadoJugador(fabulasTerminadas = 1))
        assertTrue(Insignia.PRIMERA_FABULA in ganadas)
    }

    @Test
    fun `fabulista de la casa exige diez fabulas, no una`() {
        assertTrue(Insignia.FABULISTA_DE_LA_CASA !in MotorProgreso.calcularInsigniasGanadas(EstadoJugador(fabulasTerminadas = 9)))
        assertTrue(Insignia.FABULISTA_DE_LA_CASA in MotorProgreso.calcularInsigniasGanadas(EstadoJugador(fabulasTerminadas = 10)))
    }

    @Test
    fun `puerta abierta exige los 12 visitantes, no menos`() {
        assertTrue(Insignia.PUERTA_ABIERTA !in MotorProgreso.calcularInsigniasGanadas(EstadoJugador(visitantesRecibidos = 11)))
        assertTrue(Insignia.PUERTA_ABIERTA in MotorProgreso.calcularInsigniasGanadas(EstadoJugador(visitantesRecibidos = 12)))
    }

    @Test
    fun `cuaderno lleno exige las 12 paginas`() {
        assertTrue(Insignia.CUADERNO_LLENO in MotorProgreso.calcularInsigniasGanadas(EstadoJugador(paginasCuadernoLlenas = 12)))
    }

    @Test
    fun `casa llena exige aforo de 20 en el auditorio`() {
        assertTrue(Insignia.CASA_LLENA !in MotorProgreso.calcularInsigniasGanadas(EstadoJugador(aforoAuditorio = 19)))
        assertTrue(Insignia.CASA_LLENA in MotorProgreso.calcularInsigniasGanadas(EstadoJugador(aforoAuditorio = 20)))
    }

    @Test
    fun `ojo de caballero exige la lente en 5 fabulas distintas`() {
        assertTrue(Insignia.OJO_DE_CABALLERO in MotorProgreso.calcularInsigniasGanadas(EstadoJugador(lenteUsadaEnFabulasDistintas = 5)))
    }

    @Test
    fun `buena prensa exige 10 fusiones confirmadas`() {
        assertTrue(Insignia.BUENA_PRENSA in MotorProgreso.calcularInsigniasGanadas(EstadoJugador(fusionesPrensaConfirmadas = 10)))
    }

    @Test
    fun `mano ligera exige 15 palabras quitadas con la criba`() {
        assertTrue(Insignia.MANO_LIGERA in MotorProgreso.calcularInsigniasGanadas(EstadoJugador(palabrasQuitadasConCriba = 15)))
    }

    @Test
    fun `aire fresco exige 10 expansiones confirmadas`() {
        assertTrue(Insignia.AIRE_FRESCO in MotorProgreso.calcularInsigniasGanadas(EstadoJugador(expansionesFuelleConfirmadas = 10)))
    }

    @Test
    fun `regla de tres y burlador burlado se ganan con una sola fabula que cumpla la condicion`() {
        val estado = EstadoJugador(fabulasConReglaDeTresCompleta = 1, fabulasBurladorBurlado = 1)
        val ganadas = MotorProgreso.calcularInsigniasGanadas(estado)
        assertTrue(Insignia.REGLA_DE_TRES in ganadas)
        assertTrue(Insignia.BURLADOR_BURLADO in ganadas)
    }

    @Test
    fun `espejo limpio exige 5 moralejas correctas al primer intento`() {
        assertTrue(Insignia.ESPEJO_LIMPIO in MotorProgreso.calcularInsigniasGanadas(EstadoJugador(moralejasCorrectasPrimerIntento = 5)))
    }

    @Test
    fun `un jugador con todos los umbrales gana las 12 insignias a la vez`() {
        val estado = EstadoJugador(
            fabulasTerminadas = 10,
            visitantesRecibidos = 12,
            paginasCuadernoLlenas = 12,
            aforoAuditorio = 20,
            lenteUsadaEnFabulasDistintas = 5,
            fusionesPrensaConfirmadas = 10,
            palabrasQuitadasConCriba = 15,
            expansionesFuelleConfirmadas = 10,
            fabulasConReglaDeTresCompleta = 1,
            fabulasBurladorBurlado = 1,
            moralejasCorrectasPrimerIntento = 5
        )
        assertEquals(Insignia.entries.toSet(), MotorProgreso.calcularInsigniasGanadas(estado))
    }

    @Test
    fun `la racha sube en uno si se jugo ayer`() {
        assertEquals(4, MotorProgreso.actualizarRacha(3, LocalDate.of(2026, 8, 23), LocalDate.of(2026, 8, 24)))
    }

    @Test
    fun `la racha se mantiene igual si ya se jugo hoy`() {
        assertEquals(3, MotorProgreso.actualizarRacha(3, LocalDate.of(2026, 8, 24), LocalDate.of(2026, 8, 24)))
    }

    @Test
    fun `la racha se reinicia en 1 si hay un salto de mas de un dia`() {
        assertEquals(1, MotorProgreso.actualizarRacha(5, LocalDate.of(2026, 8, 20), LocalDate.of(2026, 8, 24)))
    }

    @Test
    fun `la primera vez que se juega la racha empieza en 1`() {
        assertEquals(1, MotorProgreso.actualizarRacha(0, null, LocalDate.of(2026, 8, 24)))
    }
}
