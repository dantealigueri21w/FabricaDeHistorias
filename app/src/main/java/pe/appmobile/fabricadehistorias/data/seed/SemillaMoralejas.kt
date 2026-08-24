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
 */
object SemillaMoralejas {

    val piezas = listOf(
        PiezaMoraleja("El más astuto", ParteMoraleja.INICIO, Caracter.ASTUTO),
        PiezaMoraleja("a veces cae en su propia trampa.", ParteMoraleja.FIN, Caracter.ASTUTO),

        PiezaMoraleja("Quien confía demasiado", ParteMoraleja.INICIO, Caracter.CONFIADO),
        PiezaMoraleja("termina pagando caro.", ParteMoraleja.FIN, Caracter.CONFIADO),

        PiezaMoraleja("El que más presume", ParteMoraleja.INICIO, Caracter.PRESUMIDO),
        PiezaMoraleja("es el que menos tiene que mostrar.", ParteMoraleja.FIN, Caracter.PRESUMIDO),

        PiezaMoraleja("El más humilde", ParteMoraleja.INICIO, Caracter.HUMILDE),
        PiezaMoraleja("gana sin que nadie lo note.", ParteMoraleja.FIN, Caracter.HUMILDE),

        PiezaMoraleja("Quien vive apurado", ParteMoraleja.INICIO, Caracter.APURADO),
        PiezaMoraleja("pierde lo que pudo tener con calma.", ParteMoraleja.FIN, Caracter.APURADO),

        PiezaMoraleja("La paciencia", ParteMoraleja.INICIO, Caracter.PACIENTE),
        PiezaMoraleja("siempre encuentra su momento.", ParteMoraleja.FIN, Caracter.PACIENTE),

        PiezaMoraleja("Quien da sin medir", ParteMoraleja.INICIO, Caracter.GENEROSO),
        PiezaMoraleja("recibe cuando menos lo espera.", ParteMoraleja.FIN, Caracter.GENEROSO),

        PiezaMoraleja("El que no comparte nada", ParteMoraleja.INICIO, Caracter.EGOISTA),
        PiezaMoraleja("se queda solo cuando más necesita.", ParteMoraleja.FIN, Caracter.EGOISTA),

        PiezaMoraleja("El valiente", ParteMoraleja.INICIO, Caracter.VALIENTE),
        PiezaMoraleja("no es el que no tiene miedo, sino el que actúa igual.", ParteMoraleja.FIN, Caracter.VALIENTE),

        PiezaMoraleja("El miedo", ParteMoraleja.INICIO, Caracter.MIEDOSO),
        PiezaMoraleja("a veces cuesta más que el peligro mismo.", ParteMoraleja.FIN, Caracter.MIEDOSO),

        PiezaMoraleja("El esfuerzo constante", ParteMoraleja.INICIO, Caracter.TRABAJADOR),
        PiezaMoraleja("vale más que la suerte de un día.", ParteMoraleja.FIN, Caracter.TRABAJADOR),

        PiezaMoraleja("Quien deja todo para después", ParteMoraleja.INICIO, Caracter.PEREZOSO),
        PiezaMoraleja("termina corriendo al final.", ParteMoraleja.FIN, Caracter.PEREZOSO)
    )
}
