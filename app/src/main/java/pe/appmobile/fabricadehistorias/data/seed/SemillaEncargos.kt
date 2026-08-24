package pe.appmobile.fabricadehistorias.data.seed

import pe.appmobile.fabricadehistorias.data.local.entity.EncargoEntity

/**
 * Los 24 encargos de Antuco: 8 fáciles, 8 medios, 8 difíciles. Cada uno es una
 * restricción narrativa, nunca un tema — ver "Los 24 encargos de Antuco" en
 * la ficha. Texto transcrito tal cual de ahí, sin reescribir.
 */
object SemillaEncargos {

    val encargos = listOf(
        // Fáciles — un solo rasgo contra su opuesto
        EncargoEntity("F1", "FACIL",
            "Un pescador presumido le apostó al mar que nunca se perdería. Se perdió.",
            "Que alguien pierda por su propio orgullo"),
        EncargoEntity("F2", "FACIL",
            "Cerca de un puente colgante, alguien muy apurado se cayó por no mirar dónde pisaba.",
            "Que la prisa salga cara"),
        EncargoEntity("F3", "FACIL",
            "Un guacamayo se pasó la tarde entera contando sus plumas a quien quisiera oírlo. Nadie quiso oírlo dos veces.",
            "Que el presumido se quede solo"),
        EncargoEntity("F4", "FACIL",
            "En el arenal, un cangrejo miedoso no salió de su hueco ni cuando lo necesitaban de verdad.",
            "Que el miedo le cueste algo a otro, no solo a quien tiene miedo"),
        EncargoEntity("F5", "FACIL",
            "Una vicuña paciente esperó tanto que se le adelantó uno todavía más lento.",
            "Que hasta la paciencia tenga un límite gracioso"),
        EncargoEntity("F6", "FACIL",
            "Una tortuga humilde encontró un atajo y no se lo contó a nadie. Ni falta que hizo.",
            "Que lo humilde gane sin necesidad de presumirlo"),
        EncargoEntity("F7", "FACIL",
            "A un gallinazo le sobraba comida, y a su vecino no le sobraba nada.",
            "Que la generosidad (o su falta) tenga una consecuencia visible"),
        EncargoEntity("F8", "FACIL",
            "En la chacra, un sapo apurado sembró antes de tiempo. Cosechó antes de tiempo también: nada.",
            "Que apurarse salga más caro que esperar"),

        // Medios — un rasgo más un lugar u objeto concretos
        EncargoEntity("M1", "MEDIO",
            "En el mercado, alguien cambió su sombrero por algo que no valía ni la mitad. Se dio cuenta tarde. O nunca.",
            "Un astuto se aprovecha de un confiado, y algo se les tuerce a los dos"),
        EncargoEntity("M2", "MEDIO",
            "Un cóndor cruzó la quebrada por el camino largo, y un sapo por el corto. Llegaron casi juntos.",
            "Que el valiente y el miedoso lleguen a resultados parecidos, por caminos distintos"),
        EncargoEntity("M3", "MEDIO",
            "Dos animales subieron a la misma balsa: uno cargó de más por generoso, el otro no cargó nada por egoísta. La balsa casi se hunde igual.",
            "Que los dos extremos —dar todo, no dar nada— terminen en el mismo problema"),
        EncargoEntity("M4", "MEDIO",
            "Bajo un tejado de calamina, un zorro le prestó su costal a un cuy. Adivina quién se quedó con el costal.",
            "Usa la pareja zorro–cuy tal como es: el astuto se aprovecha del confiado"),
        EncargoEntity("M5", "MEDIO",
            "Una olla se quedó sin dueño en la orilla. Un pelícano dijo que la había encontrado él solo. No era del todo cierto.",
            "Que el presumido se atribuya un mérito que no es solo suyo"),
        EncargoEntity("M6", "MEDIO",
            "Una boa ayudó a un otorongo a cruzar un tronco caído, atada con una soga vieja. El otorongo ni le dio las gracias.",
            "Que la paciencia se ponga a prueba frente a quien no la merece"),
        EncargoEntity("M7", "MEDIO",
            "Con la neblina bien espesa, una vizcacha compartió su refugio con alguien que ni conocía. Nunca supo si hizo bien.",
            "Generosidad hacia un desconocido, sin saber cómo termina — el niño decide"),
        EncargoEntity("M8", "MEDIO",
            "En el arenal, un piquero cargó costales todo el día mientras un lobo marino dormía a la sombra del mismo costal.",
            "Que el trabajo de uno sostenga la comodidad del otro, y en algún momento se note"),

        // Difíciles — exigen una técnica, no solo un tema
        EncargoEntity("D1", "DIFICIL",
            "El cuy volvió a engañar al zorro con el mismo truco de la otra vez. Esta vez el zorro ya lo esperaba.",
            "El burlador burlado: que el astuto de siempre se tope con alguien que aprendió su truco"),
        EncargoEntity("D2", "DIFICIL",
            "Nadie en el pueblo sabe por qué el guacamayo grita tan fuerte apenas amanece. Alguien debería inventárselo.",
            "Fábula de origen: explica por qué un animal tiene la costumbre que tiene"),
        EncargoEntity("D3", "DIFICIL",
            "El pescador guardó algo en el bolsillo esa noche y nunca contó qué era. Ni yo lo sé.",
            "Usa el Gancho: termina sin resolver ese detalle a propósito"),
        EncargoEntity("D4", "DIFICIL",
            "Dicen que el cóndor vuela tan alto porque una vez algo lo asustó desde abajo. No sé si es cierto, pero debería serlo.",
            "Usa la Lente (una comparación) y que la moraleja salga de ahí, no que se diga aparte"),
        EncargoEntity("D5", "DIFICIL",
            "Un delfín de río le contó a un lobo marino que su agua era mejor. Ninguno de los dos había visto la del otro.",
            "Dos personajes de regiones distintas discuten algo que ninguno conoce del todo — que la fábula resuelva quién tenía razón, o que ninguno la tenga"),
        EncargoEntity("D6", "DIFICIL",
            "Una vizcacha intentó cruzar el mismo derrumbe tres veces, y las tres veces algo distinto la hizo volver.",
            "Usa la Regla de Tres: tres intentos de verdad distintos, no el mismo repetido"),
        EncargoEntity("D7", "DIFICIL",
            "Al gallinazo un día le sobró tanto que ya no supo ni qué hacer con lo que no quiso compartir.",
            "Que el propio defecto del personaje se vuelva en su contra, sin que nadie más se lo cause"),
        EncargoEntity("D8", "DIFICIL",
            "La última vez que un otorongo perdió contra alguien pequeño, la gente contó mal esa historia durante años. Cuéntala tú, la de verdad.",
            "Que la moraleja sorprenda: no \"el fuerte siempre gana\", sino lo contrario, defendido de verdad por lo que pasa en la fábula")
    )
}
