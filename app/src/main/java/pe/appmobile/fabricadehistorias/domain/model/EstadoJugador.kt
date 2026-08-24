package pe.appmobile.fabricadehistorias.domain.model

/** Todo lo que el motor de progreso necesita para decidir qué insignias se ganaron. */
data class EstadoJugador(
    val fabulasTerminadas: Int = 0,
    val visitantesRecibidos: Int = 0,
    val paginasCuadernoLlenas: Int = 0,
    val aforoAuditorio: Int = 0,
    val lenteUsadaEnFabulasDistintas: Int = 0,
    val fusionesPrensaConfirmadas: Int = 0,
    val palabrasQuitadasConCriba: Int = 0,
    val expansionesFuelleConfirmadas: Int = 0,
    val fabulasConReglaDeTresCompleta: Int = 0,
    val fabulasBurladorBurlado: Int = 0,
    val moralejasCorrectasPrimerIntento: Int = 0
)
