package pe.appmobile.fabricadehistorias.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/** [id] guarda el nombre del enum [pe.appmobile.fabricadehistorias.domain.model.Insignia]. */
@Entity(tableName = "insignia")
data class InsigniaEntity(
    @PrimaryKey val id: String,
    val nombre: String,
    val descripcion: String,
    val obtenida: Boolean = false,
    val fechaObtencionEpochMillis: Long? = null
)
