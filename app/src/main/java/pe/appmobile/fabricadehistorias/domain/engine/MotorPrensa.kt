package pe.appmobile.fabricadehistorias.domain.engine

/**
 * La Prensa: combinar dos oraciones cortas en una sola (sentence combining,
 * Graham & Perin 2007, tamaño de efecto 0.50 — la única técnica de "mejorar"
 * con evidencia medida detrás, ver investigación específica de esta app).
 *
 * Fundir no siempre necesita una palabra de enlace explícita ("porque", "que"):
 * en español, incrustar una frase como modificador de la otra ("el puente
 * cubierto de neblina") es una fusión tan válida como una con conector. Por eso
 * la validez no exige `tieneConector` — exige que sea **una sola oración** (no
 * las dos originales separadas por un punto) y que conserve el contenido real
 * de las dos. `tieneConector` queda aparte, como señal positiva que la interfaz
 * puede usar para reforzar, no como requisito.
 */
object MotorPrensa {

    private val CONECTORES = setOf(
        "porque", "que", "y", "como", "aunque", "pero", "cuando", "donde", "mientras", "si", "quien"
    )

    private val PALABRAS_VACIAS = setOf(
        "el", "la", "los", "las", "un", "una", "unos", "unas",
        "de", "del", "al", "a", "en", "con", "por", "para", "sin",
        "y", "o", "u", "que", "quien", "donde",
        "es", "son", "era", "eran", "fue", "fueron", "esta", "estan", "estaba", "estaban",
        "tiene", "tienen", "tenia", "tenian",
        "hay", "habia", "habian",
        "se", "su", "sus", "lo", "le", "les", "me", "te", "nos"
    )

    private const val LARGO_FIRMA = 4

    fun tieneConector(texto: String): Boolean =
        NormalizadorTexto.palabras(texto).any { it in CONECTORES }

    fun esFusionValida(fraseA: String, fraseB: String, fusion: String): Boolean {
        if (fusion.isBlank()) return false
        if (!esUnaSolaOracion(fusion)) return false

        val palabrasFusion = NormalizadorTexto.palabras(fusion)
        val clavesA = palabrasClave(fraseA)
        val clavesB = palabrasClave(fraseB)
        if (clavesA.isEmpty() || clavesB.isEmpty()) return false

        return clavesA.all { contieneFirma(palabrasFusion, it) } &&
            clavesB.all { contieneFirma(palabrasFusion, it) }
    }

    private fun esUnaSolaOracion(texto: String): Boolean {
        val sinPuntoFinal = texto.trim().trimEnd('.', '!', '?')
        return !sinPuntoFinal.contains(".")
    }

    private fun palabrasClave(frase: String): List<String> =
        NormalizadorTexto.palabras(frase).filter { it !in PALABRAS_VACIAS && it.length > 2 }

    private fun firma(palabra: String): String = palabra.take(LARGO_FIRMA)

    private fun contieneFirma(palabrasFusion: List<String>, palabraClave: String): Boolean {
        val objetivo = firma(palabraClave)
        return palabrasFusion.any { firma(it) == objetivo }
    }
}
