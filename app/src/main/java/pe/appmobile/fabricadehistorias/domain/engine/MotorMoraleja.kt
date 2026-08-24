package pe.appmobile.fabricadehistorias.domain.engine

/**
 * El Espejo de la Moraleja: que la enseñanza corresponda a lo que pasó. El
 * motor nunca juzga si la moraleja está bien escrita — solo si de verdad habla
 * de los rasgos o personajes que la fábula usó, para que no sea una frase
 * bonita pegada sin relación con la historia.
 */
object MotorMoraleja {

    fun correspondeConLosHechos(
        moraleja: String,
        palabrasClave: List<String>,
        minimoCoincidencias: Int = 1
    ): Boolean {
        if (moraleja.isBlank()) return false
        if (palabrasClave.isEmpty()) return true

        val coincidencias = palabrasClave.count { NormalizadorTexto.contienePalabra(moraleja, it) }
        return coincidencias >= minimoCoincidencias
    }
}
