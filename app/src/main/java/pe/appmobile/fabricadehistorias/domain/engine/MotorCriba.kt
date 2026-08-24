package pe.appmobile.fabricadehistorias.domain.engine

/**
 * La Criba: quitar lo repetido y lo que sobra. Solo cuenta palabras de
 * **contenido** — "el", "y", "que" se repiten en cualquier texto real y
 * marcarlas sería ruido, no una observación útil. Agrupa por raíz (los cuatro
 * primeros caracteres normalizados) para que "zorro" y "zorros" cuenten como
 * la misma palabra repetida, no como dos distintas.
 */
object MotorCriba {

    private val PALABRAS_VACIAS = setOf(
        "el", "la", "los", "las", "un", "una", "unos", "unas",
        "de", "del", "al", "a", "en", "con", "por", "para", "sin",
        "y", "o", "u", "que", "quien", "donde", "lo", "mas",
        "es", "son", "era", "eran", "fue", "fueron", "esta", "estan", "estaba", "estaban",
        "tiene", "tienen", "tenia", "tenian",
        "se", "su", "sus", "le", "les", "me", "te", "nos"
    )

    private const val LARGO_FIRMA = 4

    fun palabrasRepetidas(texto: String, minimo: Int = 3): List<String> {
        val contenido = NormalizadorTexto.palabras(texto).filter { it !in PALABRAS_VACIAS && it.length > 2 }
        return contenido.groupBy { it.take(LARGO_FIRMA) }
            .filterValues { it.size >= minimo }
            .map { (_, formas) -> formas.first() }
    }

    fun tieneRepeticionExcesiva(texto: String, minimo: Int = 3): Boolean =
        palabrasRepetidas(texto, minimo).isNotEmpty()
}
