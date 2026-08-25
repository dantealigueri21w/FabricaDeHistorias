package pe.appmobile.fabricadehistorias.ui.theme

import androidx.annotation.DrawableRes
import pe.appmobile.fabricadehistorias.R

/**
 * Traduce los identificadores del dominio a sus ilustraciones.
 *
 * Está centralizado a propósito: si una ilustración cambia de nombre o falta,
 * se arregla en un solo sitio y no en quince pantallas. Los animales de costa
 * y sierra todavía no tienen ilustración real (las dos tandas generadas
 * salieron con texto incrustado y hay que regenerarlas — ver BUILD_REPORT.md),
 * así que [animal] cae a [MarcadorIlustracion] para esos doce hasta entonces.
 */
object Arte {

    @DrawableRes val portada = R.drawable.portada_splash
    @DrawableRes val iconoLanzador = R.drawable.icono_lanzador

    @DrawableRes
    fun fondoDeEstacion(estacionId: String): Int = when (estacionId) {
        "home" -> R.drawable.fondo_taller_home
        "rueda" -> R.drawable.fondo_rueda_animales
        "molino" -> R.drawable.fondo_molino_ideas
        "mesa" -> R.drawable.fondo_mesa_esqueleto
        "pulido" -> R.drawable.fondo_sala_pulido
        "auditorio" -> R.drawable.fondo_auditorio
        else -> R.drawable.fondo_galeria_visitantes
    }

    /** null si el animal todavía no tiene ilustración real (costa y sierra, pendientes). */
    @DrawableRes
    fun animalONull(animalId: String): Int? = when (animalId) {
        "otorongo" -> R.drawable.animal_otorongo
        "guacamayo" -> R.drawable.animal_guacamayo
        "boa" -> R.drawable.animal_boa
        "tortuga_charapa" -> R.drawable.animal_tortuga_charapa
        "delfin_rosado" -> R.drawable.animal_delfin_rosado
        "perezoso" -> R.drawable.animal_perezoso
        else -> null
    }

    @DrawableRes
    fun visitante(visitanteId: String): Int = when (visitanteId) {
        "no_isidro" -> R.drawable.visitante_no_isidro
        "fortunato_buhonero" -> R.drawable.visitante_fortunato
        "el_bululu" -> R.drawable.visitante_el_bululu
        "seno_herminia" -> R.drawable.visitante_seno_herminia
        "don_espino" -> R.drawable.visitante_don_espino
        "la_killa" -> R.drawable.visitante_la_killa
        "abuela_remigia" -> R.drawable.visitante_abuela_remigia
        "compadre_cuy" -> R.drawable.visitante_compadre_cuy
        "tayta_condor" -> R.drawable.visitante_tayta_condor
        "dona_puntada" -> R.drawable.visitante_dona_puntada
        "dona_eusebia" -> R.drawable.visitante_dona_eusebia
        else -> R.drawable.visitante_don_laconico
    }

    @DrawableRes
    fun insignia(insigniaId: String): Int = when (insigniaId) {
        "PRIMERA_FABULA" -> R.drawable.insignia_primera_fabula
        "PUERTA_ABIERTA" -> R.drawable.insignia_puerta_abierta
        "CUADERNO_LLENO" -> R.drawable.insignia_cuaderno_lleno
        "CASA_LLENA" -> R.drawable.insignia_casa_llena
        "OJO_DE_CABALLERO" -> R.drawable.insignia_ojo_caballero
        "BUENA_PRENSA" -> R.drawable.insignia_buena_prensa
        "MANO_LIGERA" -> R.drawable.insignia_mano_ligera
        "AIRE_FRESCO" -> R.drawable.insignia_aire_fresco
        "REGLA_DE_TRES" -> R.drawable.insignia_regla_de_tres
        "BURLADOR_BURLADO" -> R.drawable.insignia_burlador_burlado
        "ESPEJO_LIMPIO" -> R.drawable.insignia_espejo_limpio
        else -> R.drawable.insignia_fabulista_casa
    }

    @DrawableRes
    fun avatar(avatarId: Int): Int = when (avatarId) {
        0 -> R.drawable.avatar_zorro
        1 -> R.drawable.avatar_cuy
        2 -> R.drawable.avatar_condor
        3 -> R.drawable.avatar_tortuga
        4 -> R.drawable.avatar_guacamayo
        5 -> R.drawable.avatar_vizcacha
        6 -> R.drawable.avatar_delfin
        else -> R.drawable.avatar_pelicano
    }
}
