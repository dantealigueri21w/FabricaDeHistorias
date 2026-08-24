package pe.appmobile.fabricadehistorias.domain.model

enum class TipoDado { LUGAR, OBJETO, PROBLEMA }

/** Una cara concreta del molino de ideas: "el puente colgante", "la neblina". */
data class CaraDado(
    val tipo: TipoDado,
    val texto: String,
    /** La palabra que se busca en el texto del niño para dar la pieza por usada. */
    val palabraClave: String
)

/** Lo que salió en el molino: una cara por cada tipo de dado. */
data class Tirada(
    val caras: List<CaraDado>,
    val fijadas: Set<TipoDado> = emptySet()
)
