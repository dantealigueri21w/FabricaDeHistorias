package pe.appmobile.fabricadehistorias.domain.model

/** Qué clase de conflicto sale de juntar dos animales. */
enum class TipoChoque {
    /** Caracteres opuestos: el conflicto ya está servido. */
    OPUESTOS,

    /** El mismo carácter en los dos: compiten por lo mismo. */
    IGUALES,

    /** Ni opuestos ni iguales: puede funcionar, pero el motivo lo pone el niño. */
    DISTINTOS
}

enum class FuerzaChoque { FUERTE, MEDIA, DEBIL }

/**
 * Lo que el taller le devuelve al niño cuando empareja dos animales. Nunca dice
 * "esta pareja está mal": dice qué tan fácil se va a contar sola esta historia.
 */
data class Choque(
    val tipo: TipoChoque,
    val fuerza: FuerzaChoque
)
