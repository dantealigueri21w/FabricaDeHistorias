package pe.appmobile.fabricadehistorias.data.seed

import pe.appmobile.fabricadehistorias.data.local.entity.VisitanteEntity

/**
 * Los 12 visitantes, en el orden en que llegan. Cada uno evoca una tradición
 * literaria sin nombrarla ni copiarla (sección "Aviso de derechos" de la
 * ficha): son personajes originales.
 *
 * Los cuatro primeros (orden 1-4) llegan durante la primera fábula, uno por
 * estación a la que el niño llega. Los ocho restantes (orden 5-12) llegan uno
 * por fábula terminada, empezando por la segunda.
 */
object SemillaVisitantes {

    val visitantes = listOf(
        VisitanteEntity(
            id = "no_isidro", orden = 1,
            nombre = "Ño Isidro",
            descripcion = "El que hablaba de animales para decir verdades de la gente.",
            herramienta = "La Rueda de Animales",
            tradicionEvocada = "Esopo",
            encargoDelVisitante = "Dame una fábula donde el más fuerte no sea el que gana."
        ),
        VisitanteEntity(
            id = "fortunato_buhonero", orden = 2,
            nombre = "Fortunato el Buhonero",
            descripcion = "El buhonero que lo decidía todo echando suertes.",
            herramienta = "El Molino de Ideas",
            tradicionEvocada = "Los dados de historia",
            encargoDelVisitante = "Usa las tres piezas que te toquen, ninguna de más."
        ),
        VisitanteEntity(
            id = "el_bululu", orden = 3,
            nombre = "El Bululú",
            descripcion = "El cómico de plaza que inventaba la obra completa él solo, en el momento.",
            herramienta = "El Esqueleto",
            tradicionEvocada = "El teatro improvisado",
            encargoDelVisitante = "Cuéntamela completa, con sus seis partes, de un tirón."
        ),
        VisitanteEntity(
            id = "seno_herminia", orden = 4,
            nombre = "Seño Herminia",
            descripcion = "La maestra que siempre preguntaba \"¿y eso qué enseña?\".",
            herramienta = "El Espejo de la Moraleja",
            tradicionEvocada = "Los fabulistas en español",
            encargoDelVisitante = "Que tu moraleja diga de verdad lo que pasó, no una frase bonita suelta."
        ),
        VisitanteEntity(
            id = "don_espino", orden = 5,
            nombre = "Don Espino",
            descripcion = "El caballero flaco del yelmo abollado.",
            herramienta = "La Lente",
            tradicionEvocada = "El que veía gigantes donde había molinos",
            encargoDelVisitante = "Compara algo de tu historia con otra cosa. Solo eso."
        ),
        VisitanteEntity(
            id = "la_killa", orden = 6,
            nombre = "La Killa",
            descripcion = "La que contaba de noche para que no amaneciera. Nadie la vio nunca de día.",
            herramienta = "El Gancho",
            tradicionEvocada = "Las mil y una noches",
            encargoDelVisitante = "Termínala sin resolverlo todo. Déjame con la duda."
        ),
        VisitanteEntity(
            id = "abuela_remigia", orden = 7,
            nombre = "Abuela Remigia",
            descripcion = "La abuela de los tres intentos.",
            herramienta = "La Regla de Tres",
            tradicionEvocada = "Los cuentos populares",
            encargoDelVisitante = "Que lo intente tres veces de verdad, no la misma tres veces."
        ),
        VisitanteEntity(
            id = "compadre_cuy", orden = 8,
            nombre = "Compadre Cuy",
            descripcion = "El cuy que burló al zorro.",
            herramienta = "El Sello del Burlador Burlado",
            tradicionEvocada = "Tradición andina del zorro y el cuy",
            encargoDelVisitante = "Que el más astuto se lleve su merecido."
        ),
        VisitanteEntity(
            id = "tayta_condor", orden = 9,
            nombre = "Tayta Cóndor",
            descripcion = "El viejo que explicaba por qué el cóndor tiene el cuello pelado. No habla de otra cosa.",
            herramienta = "El Cuaderno del Porqué",
            tradicionEvocada = "Los relatos de origen",
            encargoDelVisitante = "Explícame por qué un animal es como es. Invéntatelo bien."
        ),
        VisitanteEntity(
            id = "dona_puntada", orden = 10,
            nombre = "Doña Puntada",
            descripcion = "La costurera que unía dos retazos sin que se notara la costura.",
            herramienta = "La Prensa",
            tradicionEvocada = "Combinar oraciones (evidencia de Writing Next, tamaño de efecto 0.50)",
            encargoDelVisitante = "Une dos frases sueltas en una sola, sin que se note la costura."
        ),
        VisitanteEntity(
            id = "dona_eusebia", orden = 11,
            nombre = "Doña Eusebia",
            descripcion = "La retablista que metía un pueblo entero en una caja.",
            herramienta = "El Fuelle",
            tradicionEvocada = "El retablo ayacuchano",
            encargoDelVisitante = "Métele un detalle a una frase seca: dónde, cuándo o cómo."
        ),
        VisitanteEntity(
            id = "don_laconico", orden = 12,
            nombre = "Don Lacónico",
            descripcion = "El telegrafista al que le cobraban por palabra.",
            herramienta = "La Criba",
            tradicionEvocada = "La revisión, oficio de todo escritor",
            encargoDelVisitante = "Quítale lo que sobra. Que no repitas ni una palabra de más."
        )
    )
}
