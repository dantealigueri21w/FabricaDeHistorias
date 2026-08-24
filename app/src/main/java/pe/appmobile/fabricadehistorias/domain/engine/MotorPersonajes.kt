package pe.appmobile.fabricadehistorias.domain.engine

import pe.appmobile.fabricadehistorias.domain.model.Caracter
import pe.appmobile.fabricadehistorias.domain.model.Choque
import pe.appmobile.fabricadehistorias.domain.model.FuerzaChoque
import pe.appmobile.fabricadehistorias.domain.model.TipoChoque

/**
 * La Rueda de Animales. Empareja dos caracteres y dice qué conflicto sale de ahí.
 *
 * Ninguna pareja se rechaza: rechazarla sería decirle al niño que eligió mal, y
 * elegir es justamente lo que se le está pidiendo. Lo que cambia es la fuerza del
 * choque, que es la forma de enseñarle —sin decírselo— que el contraste es lo que
 * hace que una historia se cuente sola.
 */
object MotorPersonajes {

    fun choqueEntre(uno: Caracter, otro: Caracter): Choque = when {
        uno.esOpuestoDe(otro) -> Choque(TipoChoque.OPUESTOS, FuerzaChoque.FUERTE)
        uno == otro -> Choque(TipoChoque.IGUALES, FuerzaChoque.MEDIA)
        else -> Choque(TipoChoque.DISTINTOS, FuerzaChoque.DEBIL)
    }
}
