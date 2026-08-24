package pe.appmobile.fabricadehistorias.data.seed

import pe.appmobile.fabricadehistorias.domain.model.Caracter

enum class ParteMoraleja { INICIO, FIN }

/** Una pieza arrastrable del Espejo de la Moraleja. */
data class PiezaMoraleja(val texto: String, val parte: ParteMoraleja, val caracter: Caracter)

/**
 * Las 24 piezas del Espejo: 12 inicios y 12 finales, dos por cada carácter del
 * taller. El niño arma su moraleja arrastrando un inicio y un fin; el Espejo
 * (MotorMoraleja) comprueba que corresponda al carácter que de verdad usó en
 * su fábula, no que la frase suene bonita.
 *
 * Cada INICIO contiene, a propósito, la palabra exacta del carácter que
 * representa (Caracter.CONFIADO.name → "confiado" en el texto). Es lo que
 * hace que MotorMoraleja pueda validar sin opinar: si no contuviera esa
 * palabra literal, una moraleja perfectamente correcta podría marcarse como
 * que no corresponde, y ese falso negativo es justo lo que el motor evita.
 */
object SemillaMoralejas {

    val piezas = listOf(
        PiezaMoraleja("El más astuto", ParteMoraleja.INICIO, Caracter.ASTUTO),
        PiezaMoraleja("a veces cae en su propia trampa.", ParteMoraleja.FIN, Caracter.ASTUTO),

        PiezaMoraleja("Quien es demasiado confiado", ParteMoraleja.INICIO, Caracter.CONFIADO),
        PiezaMoraleja("termina pagando caro.", ParteMoraleja.FIN, Caracter.CONFIADO),

        PiezaMoraleja("El más presumido", ParteMoraleja.INICIO, Caracter.PRESUMIDO),
        PiezaMoraleja("es el que menos tiene que mostrar.", ParteMoraleja.FIN, Caracter.PRESUMIDO),

        PiezaMoraleja("El más humilde", ParteMoraleja.INICIO, Caracter.HUMILDE),
        PiezaMoraleja("gana sin que nadie lo note.", ParteMoraleja.FIN, Caracter.HUMILDE),

        PiezaMoraleja("Quien vive apurado", ParteMoraleja.INICIO, Caracter.APURADO),
        PiezaMoraleja("pierde lo que pudo tener con calma.", ParteMoraleja.FIN, Caracter.APURADO),

        PiezaMoraleja("El más paciente", ParteMoraleja.INICIO, Caracter.PACIENTE),
        PiezaMoraleja("siempre encuentra su momento.", ParteMoraleja.FIN, Caracter.PACIENTE),

        PiezaMoraleja("El más generoso", ParteMoraleja.INICIO, Caracter.GENEROSO),
        PiezaMoraleja("recibe cuando menos lo espera.", ParteMoraleja.FIN, Caracter.GENEROSO),

        PiezaMoraleja("El más egoísta", ParteMoraleja.INICIO, Caracter.EGOISTA),
        PiezaMoraleja("se queda solo cuando más necesita.", ParteMoraleja.FIN, Caracter.EGOISTA),

        PiezaMoraleja("El valiente", ParteMoraleja.INICIO, Caracter.VALIENTE),
        PiezaMoraleja("no es el que no tiene miedo, sino el que actúa igual.", ParteMoraleja.FIN, Caracter.VALIENTE),

        PiezaMoraleja("El más miedoso", ParteMoraleja.INICIO, Caracter.MIEDOSO),
        PiezaMoraleja("a veces pierde más que el que se arriesga.", ParteMoraleja.FIN, Caracter.MIEDOSO),

        PiezaMoraleja("El más trabajador", ParteMoraleja.INICIO, Caracter.TRABAJADOR),
        PiezaMoraleja("vale más que la suerte de un día.", ParteMoraleja.FIN, Caracter.TRABAJADOR),

        PiezaMoraleja("El más perezoso", ParteMoraleja.INICIO, Caracter.PEREZOSO),
        PiezaMoraleja("termina corriendo al final.", ParteMoraleja.FIN, Caracter.PEREZOSO)
    )
}
