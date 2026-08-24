package pe.appmobile.fabricadehistorias.domain.engine

/**
 * El Fuelle: expandir una frase plana con un detalle (dónde, cuándo, cómo).
 * Válida dos cosas mecánicas, nunca la calidad: que el contenido original siga
 * ahí —no se reemplazó, se amplió— y que de verdad haya algo nuevo, no solo las
 * mismas palabras reordenadas.
 */
object MotorFuelle {

    private val PALABRAS_VACIAS = setOf(
        "el", "la", "los", "las", "un", "una", "unos", "unas",
        "de", "del", "al", "a", "en", "con", "por", "para", "sin",
        "y", "o", "u", "que", "quien", "donde", "lo",
        "es", "son", "era", "eran", "fue", "fueron", "esta", "estan", "estaba", "estaban",
        "tiene", "tienen", "tenia", "tenian",
        "se", "su", "sus", "le", "les", "me", "te", "nos"
    )

    private const val LARGO_FIRMA = 4

    fun esExpansionValida(fraseOriginal: String, fraseExpandida: String): Boolean {
        if (fraseExpandida.isBlank()) return false

        val clavesOriginal = palabrasClave(fraseOriginal)
        val clavesExpandida = palabrasClave(fraseExpandida)
        if (clavesOriginal.isEmpty()) return false

        val conservaOriginal = clavesOriginal.all { original ->
            clavesExpandida.any { firma(it) == firma(original) }
        }
        return conservaOriginal && tieneDetalleNuevo(fraseOriginal, fraseExpandida)
    }

    fun tieneDetalleNuevo(fraseOriginal: String, fraseExpandida: String): Boolean {
        val firmasOriginal = palabrasClave(fraseOriginal).map { firma(it) }.toSet()
        val clavesExpandida = palabrasClave(fraseExpandida)
        return clavesExpandida.any { firma(it) !in firmasOriginal }
    }

    private fun palabrasClave(frase: String): List<String> =
        NormalizadorTexto.palabras(frase).filter { it !in PALABRAS_VACIAS && it.length > 2 }

    private fun firma(palabra: String): String = palabra.take(LARGO_FIRMA)
}
