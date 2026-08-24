package pe.appmobile.fabricadehistorias.ui.screens.perfil

/** Los 8 avatares base del primer arranque. Nunca se pide el nombre real. */
data class Avatar(val id: Int, val nombreDrawable: String, val descripcion: String)

object Avatares {
    val lista = listOf(
        Avatar(0, "avatar_zorro", "Avatar de zorro"),
        Avatar(1, "avatar_cuy", "Avatar de cuy"),
        Avatar(2, "avatar_condor", "Avatar de cóndor"),
        Avatar(3, "avatar_tortuga", "Avatar de tortuga"),
        Avatar(4, "avatar_guacamayo", "Avatar de guacamayo"),
        Avatar(5, "avatar_vizcacha", "Avatar de vizcacha"),
        Avatar(6, "avatar_delfin", "Avatar de delfín"),
        Avatar(7, "avatar_pelicano", "Avatar de pelícano")
    )
}
