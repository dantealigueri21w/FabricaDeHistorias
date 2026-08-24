package pe.appmobile.fabricadehistorias.data.seed

/** Un par de frases cortas para fundir en la Prensa. */
data class ParPrensa(val fraseA: String, val fraseB: String)

/**
 * Los pares y frases de la Sala de Pulido. Viven como datos planos, no en
 * Room: el repositorio reparte uno cada vez y solo el resultado del niño
 * (EjercicioEntity) se persiste — el par en sí no necesita historial propio.
 */
object SemillaEjercicios {

    /** 20 pares para la Prensa. */
    val paresPrensa = listOf(
        ParPrensa("El zorro cruzó el puente.", "El puente tenía neblina."),
        ParPrensa("La vicuña subió el cerro.", "El cerro estaba nevado."),
        ParPrensa("El cóndor voló muy alto.", "El cielo estaba despejado."),
        ParPrensa("El cuy escondió su comida.", "La comida era maíz."),
        ParPrensa("El pelícano miró el mar.", "El mar estaba agitado."),
        ParPrensa("La tortuga cruzó el río.", "El río bajaba lento."),
        ParPrensa("El otorongo caminó de noche.", "La noche era oscura."),
        ParPrensa("El sapo saltó al charco.", "El charco estaba frío."),
        ParPrensa("La boa se enroscó en la rama.", "La rama era gruesa."),
        ParPrensa("El guacamayo gritó fuerte.", "El bosque se quedó en silencio."),
        ParPrensa("El lobo marino durmió en la roca.", "La roca estaba caliente."),
        ParPrensa("La vizcacha compartió su refugio.", "El refugio era pequeño."),
        ParPrensa("El cangrejo se escondió en la arena.", "La arena estaba mojada."),
        ParPrensa("El gallinazo esperó en el poste.", "El poste era de madera."),
        ParPrensa("El piquero pescó todo el día.", "El día fue muy largo."),
        ParPrensa("La cuculí cantó al amanecer.", "El amanecer llegó despacio."),
        ParPrensa("El delfín nadó cerca de la orilla.", "La orilla tenía piedras."),
        ParPrensa("El zorro miró el costal.", "El costal estaba lleno."),
        ParPrensa("La vicuña bebió del arroyo.", "El arroyo bajaba de la nieve."),
        ParPrensa("El cuy cruzó la chacra.", "La chacra tenía papas.")
    )

    /** 16 frases planas para expandir con el Fuelle. */
    val frasesFuelle = listOf(
        "El zorro cruzó el puente.",
        "La tortuga llegó tarde.",
        "El cóndor descansó en la roca.",
        "El cuy se escondió del zorro.",
        "La vicuña miró el valle.",
        "El sapo cantó toda la noche.",
        "El pelícano encontró un pez.",
        "La boa durmió bajo el árbol.",
        "El otorongo cruzó el río.",
        "El guacamayo perdió una pluma.",
        "El lobo marino subió a la roca.",
        "La vizcacha guardó su comida.",
        "El cangrejo salió de su hueco.",
        "El gallinazo voló sobre el pueblo.",
        "El piquero volvió al mar.",
        "El delfín saltó fuera del agua."
    )
}
