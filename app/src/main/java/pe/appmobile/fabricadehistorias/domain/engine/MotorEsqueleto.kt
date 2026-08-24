package pe.appmobile.fabricadehistorias.domain.engine

import pe.appmobile.fabricadehistorias.domain.model.Tramo

/**
 * La Mesa del Esqueleto. Comprueba que los seis tramos estén escritos y que el
 * niño los haya puesto en su orden.
 *
 * Lo único que mide es que haya algo escrito: tres palabras como mínimo, para que
 * un "sí" o un "el zorro" no cuenten como tramo resuelto. **Nunca juzga lo que
 * dicen** — eso sería opinar sobre la historia del niño, y no hay forma de hacerlo
 * sin equivocarse.
 */
object MotorEsqueleto {

    const val PALABRAS_MINIMAS = 3

    fun ordenNarrativo(): List<Tramo> = Tramo.entries.toList()

    fun tramosFaltantes(escrito: Map<Tramo, String>): List<Tramo> =
        ordenNarrativo().filterNot { estaEscrito(escrito[it]) }

    fun estaCompleto(escrito: Map<Tramo, String>): Boolean = tramosFaltantes(escrito).isEmpty()

    fun estaEnOrden(propuesto: List<Tramo>): Boolean = propuesto == ordenNarrativo()

    private fun estaEscrito(texto: String?): Boolean =
        texto != null && NormalizadorTexto.palabras(texto).size >= PALABRAS_MINIMAS
}
