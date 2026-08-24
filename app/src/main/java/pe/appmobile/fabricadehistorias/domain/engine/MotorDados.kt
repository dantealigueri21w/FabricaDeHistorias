package pe.appmobile.fabricadehistorias.domain.engine

import pe.appmobile.fabricadehistorias.domain.model.CaraDado
import pe.appmobile.fabricadehistorias.domain.model.TipoDado
import pe.appmobile.fabricadehistorias.domain.model.Tirada
import kotlin.random.Random

/**
 * El Molino de Ideas. Reparte un lugar, un objeto y un problema con los que hay
 * que escribir.
 *
 * El azar reparte las piezas, pero no resuelve la historia: el niño puede fijar la
 * pieza que le gustó y relanzar las otras. Y las tres tienen que aparecer de verdad
 * en lo que escriba — comprobado con tolerancia a plurales y conjugaciones, para
 * no marcarle error cuando escribió bien.
 */
object MotorDados {

    fun lanzar(caras: List<CaraDado>, aleatorio: Random): Tirada {
        val elegidas = TipoDado.entries.mapNotNull { tipo ->
            caras.filter { it.tipo == tipo }.randomOrNull(aleatorio)
        }
        return Tirada(caras = elegidas)
    }

    fun fijar(tirada: Tirada, tipo: TipoDado): Tirada =
        tirada.copy(fijadas = tirada.fijadas + tipo)

    fun relanzarNoFijados(tirada: Tirada, caras: List<CaraDado>, aleatorio: Random): Tirada {
        val nuevas = tirada.caras.map { cara ->
            if (cara.tipo in tirada.fijadas) {
                cara
            } else {
                caras.filter { it.tipo == cara.tipo }.randomOrNull(aleatorio) ?: cara
            }
        }
        return tirada.copy(caras = nuevas)
    }

    /** Las piezas de la tirada que todavía no aparecen en el texto del niño. */
    fun piezasFaltantes(texto: String, tirada: Tirada): List<CaraDado> =
        tirada.caras.filterNot { NormalizadorTexto.contienePalabra(texto, it.palabraClave) }
}
