package pe.appmobile.fabricadehistorias.domain.engine

import pe.appmobile.fabricadehistorias.domain.model.Tramo

/**
 * El Auditorio: aforo y reacciones, calculados desde lo que el niño ya
 * escribió y guardó en Room — nunca un contador suelto ni una opinión sobre la
 * prosa. Se apoya en MotorEsqueleto (completo) y MotorCriba (repetición), que
 * ya validan mecánicamente; este motor solo junta esas observaciones.
 */
object MotorAuditorio {

    private const val PUBLICO_POR_FABULA = 3

    fun aforo(fabulasTerminadas: Int): Int = fabulasTerminadas * PUBLICO_POR_FABULA

    fun observaciones(tramos: Map<Tramo, String>): List<String> {
        val obs = mutableListOf<String>()

        val faltantes = MotorEsqueleto.tramosFaltantes(tramos)
        if (faltantes.isNotEmpty()) obs += "Hay ${faltantes.size} tramo(s) sin terminar"

        // Por tramo, no por la fábula entera: repetir el nombre del protagonista
        // tres veces a lo largo de seis escenas es narrativa normal, no un defecto.
        // El problema real es repetir una palabra tres veces dentro de la misma frase.
        val repetidas = tramos.values.flatMap { MotorCriba.palabrasRepetidas(it) }.distinct()
        if (repetidas.isNotEmpty()) obs += "Se repite: ${repetidas.joinToString(", ")}"

        return obs
    }

    fun aplaudeFuerte(tramos: Map<Tramo, String>): Boolean =
        MotorEsqueleto.estaCompleto(tramos) && observaciones(tramos).isEmpty()
}
