package pe.appmobile.fabricadehistorias.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/** [tipoTramo] guarda el nombre del enum [pe.appmobile.fabricadehistorias.domain.model.Tramo]. */
@Entity(
    tableName = "tramo",
    foreignKeys = [
        ForeignKey(
            entity = FabulaEntity::class, parentColumns = ["id"], childColumns = ["fabulaId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("fabulaId")]
)
data class TramoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val fabulaId: Long,
    val tipoTramo: String,
    val texto: String
)
