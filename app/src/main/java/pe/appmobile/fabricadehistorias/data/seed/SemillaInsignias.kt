package pe.appmobile.fabricadehistorias.data.seed

import pe.appmobile.fabricadehistorias.data.local.entity.InsigniaEntity
import pe.appmobile.fabricadehistorias.domain.model.Insignia

object SemillaInsignias {

    val insignias = listOf(
        InsigniaEntity(Insignia.PRIMERA_FABULA.name, "Primera Fábula", "Terminar y leer la primera fábula en el auditorio"),
        InsigniaEntity(Insignia.PUERTA_ABIERTA.name, "Puerta Abierta", "Recibir a los 12 visitantes"),
        InsigniaEntity(Insignia.CUADERNO_LLENO.name, "Cuaderno Lleno", "Guardar una frase propia en las 12 páginas"),
        InsigniaEntity(Insignia.CASA_LLENA.name, "Casa Llena", "Llegar a 20 animales en el auditorio"),
        InsigniaEntity(Insignia.OJO_DE_CABALLERO.name, "Ojo de Caballero", "Usar la lente en 5 fábulas distintas"),
        InsigniaEntity(Insignia.BUENA_PRENSA.name, "Buena Prensa", "Combinar 10 pares de frases sin deshacerlas"),
        InsigniaEntity(Insignia.MANO_LIGERA.name, "Mano Ligera", "Quitar 15 palabras repetidas con la criba"),
        InsigniaEntity(Insignia.AIRE_FRESCO.name, "Aire Fresco", "Expandir 10 frases con un detalle nuevo"),
        InsigniaEntity(Insignia.REGLA_DE_TRES.name, "Regla de Tres", "Terminar una fábula con los tres intentos completos"),
        InsigniaEntity(Insignia.BURLADOR_BURLADO.name, "Burlador Burlado", "Escribir una fábula donde el astuto caiga en su trampa"),
        InsigniaEntity(Insignia.ESPEJO_LIMPIO.name, "Espejo Limpio", "5 moralejas que corresponden a lo que pasó, al primer intento"),
        InsigniaEntity(Insignia.FABULISTA_DE_LA_CASA.name, "Fabulista de la Casa", "Tener 10 fábulas en el Fabulario")
    )
}
