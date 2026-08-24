package pe.appmobile.fabricadehistorias.ui.navigation

object Rutas {
    const val ONBOARDING = "onboarding"
    const val TALLER = "taller"
    const val RUEDA_ANIMALES = "rueda_animales"
    const val MOLINO_IDEAS = "molino_ideas/{fabulaId}"
    const val MESA_ESQUELETO = "mesa_esqueleto/{fabulaId}"
    const val LENTE = "lente/{fabulaId}"
    const val SALA_PULIDO = "sala_pulido"
    const val AUDITORIO = "auditorio/{fabulaId}"
    const val FABULARIO = "fabulario"
    const val GALERIA_VISITANTES = "galeria_visitantes"
    const val CUADERNO_APRENDIZ = "cuaderno_aprendiz"
    const val RINCON_PRACTICA = "rincon_practica"
    const val AJUSTES = "ajustes"

    fun molinoIdeas(fabulaId: Long) = "molino_ideas/$fabulaId"
    fun mesaEsqueleto(fabulaId: Long) = "mesa_esqueleto/$fabulaId"
    fun lente(fabulaId: Long) = "lente/$fabulaId"
    fun auditorio(fabulaId: Long) = "auditorio/$fabulaId"
}
