package pe.appmobile.fabricadehistorias.domain.engine

/**
 * La Lente: comparación y metáfora. El motor no opina si la comparación es
 * buena — eso es del niño — solo comprueba dos cosas mecánicas: que nombre las
 * dos cosas que se comparan y que use un conector real, no que estén sueltas en
 * la misma frase por casualidad.
 */
object MotorComparacion {

    private val CONECTORES = listOf(
        "como", "parece", "parecia", "parecio",
        "igual que", "semejante a", "se parece a", "se veia como"
    )

    fun tieneConector(texto: String): Boolean {
        val normalizado = NormalizadorTexto.normalizar(texto)
        return CONECTORES.any { conector -> normalizado.contains(NormalizadorTexto.normalizar(conector)) }
    }

    fun esComparacionValida(texto: String, terminoA: String, terminoB: String): Boolean =
        NormalizadorTexto.contienePalabra(texto, terminoA) &&
            NormalizadorTexto.contienePalabra(texto, terminoB) &&
            tieneConector(texto)
}
