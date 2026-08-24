package pe.appmobile.fabricadehistorias.data.seed

import pe.appmobile.fabricadehistorias.domain.model.CaraDado
import pe.appmobile.fabricadehistorias.domain.model.TipoDado

/** Las 36 caras del Molino de Ideas: 12 lugares, 12 objetos, 12 problemas. */
object SemillaDados {

    val caras = listOf(
        // Lugares
        CaraDado(TipoDado.LUGAR, "el puente colgante", "puente"),
        CaraDado(TipoDado.LUGAR, "la chacra", "chacra"),
        CaraDado(TipoDado.LUGAR, "el mercado", "mercado"),
        CaraDado(TipoDado.LUGAR, "la balsa", "balsa"),
        CaraDado(TipoDado.LUGAR, "el arenal", "arenal"),
        CaraDado(TipoDado.LUGAR, "la quebrada", "quebrada"),
        CaraDado(TipoDado.LUGAR, "el tejado de calamina", "tejado"),
        CaraDado(TipoDado.LUGAR, "la orilla del río", "orilla"),
        CaraDado(TipoDado.LUGAR, "el cerro nevado", "cerro"),
        CaraDado(TipoDado.LUGAR, "la plaza del pueblo", "plaza"),
        CaraDado(TipoDado.LUGAR, "el bosque", "bosque"),
        CaraDado(TipoDado.LUGAR, "el poste de madera", "poste"),

        // Objetos
        CaraDado(TipoDado.OBJETO, "una olla", "olla"),
        CaraDado(TipoDado.OBJETO, "un sombrero", "sombrero"),
        CaraDado(TipoDado.OBJETO, "una soga", "soga"),
        CaraDado(TipoDado.OBJETO, "un costal", "costal"),
        CaraDado(TipoDado.OBJETO, "una canasta", "canasta"),
        CaraDado(TipoDado.OBJETO, "una sombrilla de paja", "sombrilla"),
        CaraDado(TipoDado.OBJETO, "una red de pescar", "red"),
        CaraDado(TipoDado.OBJETO, "una manta de lana", "manta"),
        CaraDado(TipoDado.OBJETO, "un machete", "machete"),
        CaraDado(TipoDado.OBJETO, "una botija de barro", "botija"),
        CaraDado(TipoDado.OBJETO, "una escalera de madera", "escalera"),
        CaraDado(TipoDado.OBJETO, "un farol", "farol"),

        // Problemas
        CaraDado(TipoDado.PROBLEMA, "se levantó la neblina", "neblina"),
        CaraDado(TipoDado.PROBLEMA, "se acabó el agua", "agua"),
        CaraDado(TipoDado.PROBLEMA, "alguien llegó tarde", "tarde"),
        CaraDado(TipoDado.PROBLEMA, "se rompió algo", "rompio"),
        CaraDado(TipoDado.PROBLEMA, "empezó a llover", "llover"),
        CaraDado(TipoDado.PROBLEMA, "se perdió un objeto", "objeto"),
        CaraDado(TipoDado.PROBLEMA, "hizo mucho calor", "calor"),
        CaraDado(TipoDado.PROBLEMA, "se hizo de noche", "noche"),
        CaraDado(TipoDado.PROBLEMA, "apareció un forastero", "forastero"),
        CaraDado(TipoDado.PROBLEMA, "faltó comida", "comida"),
        CaraDado(TipoDado.PROBLEMA, "se rompió una rama en el camino", "rama"),
        CaraDado(TipoDado.PROBLEMA, "se escuchó un ruido raro", "ruido")
    )
}
