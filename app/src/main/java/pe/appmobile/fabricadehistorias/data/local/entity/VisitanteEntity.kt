package pe.appmobile.fabricadehistorias.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Cada visitante trae una herramienta y pide un encargo propio antes de
 * quedarse en la Galería. [orden] fija la secuencia de llegada (los cuatro
 * primeros durante la primera fábula, los ocho restantes uno por fábula).
 */
@Entity(tableName = "visitante")
data class VisitanteEntity(
    @PrimaryKey val id: String,
    val nombre: String,
    val descripcion: String,
    val herramienta: String,
    val tradicionEvocada: String,
    val encargoDelVisitante: String,
    val orden: Int,
    val recibido: Boolean = false,
    val fechaRecibidoEpochMillis: Long? = null
)
