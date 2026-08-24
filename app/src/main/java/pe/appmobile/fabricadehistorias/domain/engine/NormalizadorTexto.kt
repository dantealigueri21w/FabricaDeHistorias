package pe.appmobile.fabricadehistorias.domain.engine

import java.text.Normalizer

/**
 * Comprueba si el niño usó en su texto las piezas que le tocaron.
 *
 * La comparación no puede ser literal. Si al niño le sale la pieza "puente" y
 * escribe "los puentes colgantes", escribió bien: marcarlo como error sería
 * decirle que se equivocó cuando no lo hizo, y ese falso negativo es peor que no
 * comprobar nada. Por eso se toleran tildes, mayúsculas, plurales, diminutivos y
 * las formas conjugadas de un verbo.
 *
 * La tolerancia tiene un límite deliberado: nunca se acepta una palabra por estar
 * contenida dentro de otra ("oso" no está en "hermoso"), y con tres letras o menos
 * la comparación vuelve a ser literal, porque cualquier margen ahí da falsos
 * positivos.
 */
object NormalizadorTexto {

    private val SEPARADORES = Regex("[^a-z]+")
    private val MARCAS_DIACRITICAS = Regex("\\p{Mn}+")

    private val SUFIJOS_DIMINUTIVO = setOf(
        "ito", "ita", "itos", "itas",
        "cito", "cita", "citos", "citas",
        "illo", "illa", "illos", "illas"
    )

    /** Terminaciones verbales frecuentes en presente, pasado, futuro y participio. */
    private val TERMINACIONES_VERBALES = setOf(
        "ar", "er", "ir",
        "o", "as", "a", "amos", "ais", "an",
        "es", "e", "emos", "eis", "en",
        "imos", "is",
        "aba", "abas", "abamos", "aban",
        "ia", "ias", "iamos", "ian",
        "aste", "asteis", "aron",
        "i", "iste", "io", "isteis", "ieron",
        "are", "aras", "ara", "aremos", "aran",
        "ere", "eras", "era", "eremos", "eran",
        "ire", "iras", "ira", "iremos", "iran",
        "ando", "iendo",
        "ado", "ada", "ados", "adas",
        "ido", "ida", "idos", "idas"
    )

    /** Minúsculas y sin tildes ni diéresis: "Cañón" queda como "canon". */
    fun normalizar(texto: String): String =
        Normalizer.normalize(texto.lowercase(), Normalizer.Form.NFD)
            .replace(MARCAS_DIACRITICAS, "")

    /** Las palabras del texto, ya normalizadas y sin signos pegados. */
    fun palabras(texto: String): List<String> =
        normalizar(texto).split(SEPARADORES).filter { it.isNotEmpty() }

    fun contienePalabra(texto: String, palabra: String): Boolean {
        val objetivo = normalizar(palabra).trim()
        if (objetivo.isEmpty()) return false

        val palabrasDelTexto = palabras(texto)
        if (objetivo.length <= 3) return palabrasDelTexto.any { it == objetivo }

        return palabrasDelTexto.any { coincide(it, objetivo) }
    }

    /** Las piezas de la lista que sí aparecen en el texto, en el mismo orden. */
    fun piezasUsadas(texto: String, piezas: List<String>): List<String> =
        piezas.filter { contienePalabra(texto, it) }

    private fun coincide(palabra: String, objetivo: String): Boolean =
        palabra == objetivo ||
            esPlural(palabra, objetivo) ||
            esDiminutivo(palabra, objetivo) ||
            esFormaVerbal(palabra, objetivo)

    private fun esPlural(palabra: String, objetivo: String): Boolean =
        palabra == objetivo + "s" || palabra == objetivo + "es"

    private fun esDiminutivo(palabra: String, objetivo: String): Boolean {
        val raiz = objetivo.dropLastWhile { it in "aeiou" }
        if (raiz.length < 3 || !palabra.startsWith(raiz)) return false
        return palabra.removePrefix(raiz) in SUFIJOS_DIMINUTIVO
    }

    private fun esFormaVerbal(palabra: String, objetivo: String): Boolean {
        if (objetivo.length < 5) return false
        if (objetivo.takeLast(2) !in setOf("ar", "er", "ir")) return false

        val raiz = objetivo.dropLast(2)
        if (raiz.length < 3 || !palabra.startsWith(raiz)) return false
        return palabra.removePrefix(raiz) in TERMINACIONES_VERBALES
    }
}
