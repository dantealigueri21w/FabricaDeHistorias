package pe.appmobile.fabricadehistorias.domain.model

/**
 * El rasgo dominante de un animal del taller. En una fábula el personaje no
 * necesita psicología: necesita un solo rasgo que lo meta en problemas.
 *
 * Están en pares opuestos a propósito. Enfrentar dos opuestos es lo que hace que
 * la historia se cuente casi sola, y es lo que el niño descubre girando las ruedas.
 */
enum class Caracter {
    ASTUTO, CONFIADO,
    PRESUMIDO, HUMILDE,
    APURADO, PACIENTE,
    GENEROSO, EGOISTA,
    VALIENTE, MIEDOSO,
    TRABAJADOR, PEREZOSO;

    val opuesto: Caracter
        get() = when (this) {
            ASTUTO -> CONFIADO
            CONFIADO -> ASTUTO
            PRESUMIDO -> HUMILDE
            HUMILDE -> PRESUMIDO
            APURADO -> PACIENTE
            PACIENTE -> APURADO
            GENEROSO -> EGOISTA
            EGOISTA -> GENEROSO
            VALIENTE -> MIEDOSO
            MIEDOSO -> VALIENTE
            TRABAJADOR -> PEREZOSO
            PEREZOSO -> TRABAJADOR
        }

    fun esOpuestoDe(otro: Caracter): Boolean = otro == opuesto
}
