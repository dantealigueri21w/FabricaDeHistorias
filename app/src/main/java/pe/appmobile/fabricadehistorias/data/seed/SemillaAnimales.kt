package pe.appmobile.fabricadehistorias.data.seed

import pe.appmobile.fabricadehistorias.data.local.entity.AnimalEntity
import pe.appmobile.fabricadehistorias.domain.model.Caracter

/**
 * Los 18 animales del taller, de las tres regiones del Perú, cada uno con un
 * solo rasgo — nunca leones, lobos ni zorros europeos.
 *
 * El zorro es CONFIADO y el cuy es ASTUTO (no al revés): es lo que hace que la
 * pareja zorro–cuy produzca sola el "burlador burlado" andino. El animal
 * "perezoso" lleva el carácter APURADO, no PEREZOSO — un chiste deliberado que
 * además evita confundir el nombre del animal con el nombre de su rasgo.
 */
object SemillaAnimales {

    val animales = listOf(
        // Costa
        AnimalEntity(id = "pelicano", nombre = "Pelícano", region = "COSTA", caracter = Caracter.PRESUMIDO.name),
        AnimalEntity(id = "lobo_marino", nombre = "Lobo marino", region = "COSTA", caracter = Caracter.PEREZOSO.name),
        AnimalEntity(id = "cangrejo", nombre = "Cangrejo", region = "COSTA", caracter = Caracter.MIEDOSO.name),
        AnimalEntity(id = "gallinazo", nombre = "Gallinazo", region = "COSTA", caracter = Caracter.EGOISTA.name),
        AnimalEntity(id = "piquero", nombre = "Piquero", region = "COSTA", caracter = Caracter.TRABAJADOR.name),
        AnimalEntity(id = "cuculi", nombre = "Cuculí", region = "COSTA", caracter = Caracter.HUMILDE.name),

        // Sierra
        AnimalEntity(id = "zorro_andino", nombre = "Zorro andino", region = "SIERRA", caracter = Caracter.CONFIADO.name),
        AnimalEntity(id = "cuy", nombre = "Cuy", region = "SIERRA", caracter = Caracter.ASTUTO.name),
        AnimalEntity(id = "condor", nombre = "Cóndor", region = "SIERRA", caracter = Caracter.VALIENTE.name),
        AnimalEntity(id = "vicuna", nombre = "Vicuña", region = "SIERRA", caracter = Caracter.PACIENTE.name),
        AnimalEntity(id = "sapo", nombre = "Sapo", region = "SIERRA", caracter = Caracter.APURADO.name),
        AnimalEntity(id = "vizcacha", nombre = "Vizcacha", region = "SIERRA", caracter = Caracter.GENEROSO.name),

        // Selva
        AnimalEntity(id = "otorongo", nombre = "Otorongo", region = "SELVA", caracter = Caracter.EGOISTA.name),
        AnimalEntity(id = "guacamayo", nombre = "Guacamayo", region = "SELVA", caracter = Caracter.PRESUMIDO.name),
        AnimalEntity(id = "boa", nombre = "Boa", region = "SELVA", caracter = Caracter.PACIENTE.name),
        AnimalEntity(id = "tortuga_charapa", nombre = "Tortuga charapa", region = "SELVA", caracter = Caracter.HUMILDE.name),
        AnimalEntity(id = "delfin_rosado", nombre = "Delfín rosado", region = "SELVA", caracter = Caracter.GENEROSO.name),
        AnimalEntity(id = "perezoso", nombre = "Perezoso", region = "SELVA", caracter = Caracter.APURADO.name)
    )
}
