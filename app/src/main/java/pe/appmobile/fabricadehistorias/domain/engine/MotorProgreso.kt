package pe.appmobile.fabricadehistorias.domain.engine

import pe.appmobile.fabricadehistorias.domain.model.EstadoJugador
import pe.appmobile.fabricadehistorias.domain.model.Insignia
import java.time.LocalDate
import java.time.temporal.ChronoUnit

/** Desbloqueos, insignias y racha diaria. Es el motor que ata a los otros nueve entre sí. */
object MotorProgreso {

    fun calcularInsigniasGanadas(estado: EstadoJugador): Set<Insignia> {
        val ganadas = mutableSetOf<Insignia>()

        if (estado.fabulasTerminadas >= 1) ganadas += Insignia.PRIMERA_FABULA
        if (estado.visitantesRecibidos >= 12) ganadas += Insignia.PUERTA_ABIERTA
        if (estado.paginasCuadernoLlenas >= 12) ganadas += Insignia.CUADERNO_LLENO
        if (estado.aforoAuditorio >= 20) ganadas += Insignia.CASA_LLENA
        if (estado.lenteUsadaEnFabulasDistintas >= 5) ganadas += Insignia.OJO_DE_CABALLERO
        if (estado.fusionesPrensaConfirmadas >= 10) ganadas += Insignia.BUENA_PRENSA
        if (estado.palabrasQuitadasConCriba >= 15) ganadas += Insignia.MANO_LIGERA
        if (estado.expansionesFuelleConfirmadas >= 10) ganadas += Insignia.AIRE_FRESCO
        if (estado.fabulasConReglaDeTresCompleta >= 1) ganadas += Insignia.REGLA_DE_TRES
        if (estado.fabulasBurladorBurlado >= 1) ganadas += Insignia.BURLADOR_BURLADO
        if (estado.moralejasCorrectasPrimerIntento >= 5) ganadas += Insignia.ESPEJO_LIMPIO
        if (estado.fabulasTerminadas >= 10) ganadas += Insignia.FABULISTA_DE_LA_CASA

        return ganadas
    }

    /**
     * Si ya se jugó hoy, la racha se mantiene. Si se jugó ayer, sube en uno.
     * Si hay un salto de más de un día (o es la primera vez), se reinicia en 1.
     */
    fun actualizarRacha(rachaActual: Int, ultimaFechaJuego: LocalDate?, fechaHoy: LocalDate): Int {
        if (ultimaFechaJuego == null) return 1
        return when (ChronoUnit.DAYS.between(ultimaFechaJuego, fechaHoy)) {
            0L -> rachaActual
            1L -> rachaActual + 1
            else -> 1
        }
    }
}
