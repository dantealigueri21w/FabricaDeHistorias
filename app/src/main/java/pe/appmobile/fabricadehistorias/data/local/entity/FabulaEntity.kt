package pe.appmobile.fabricadehistorias.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Una fábula del niño. Los banderines (usoLente, cumplioReglaDeTres,
 * esBurladorBurlado) se marcan una vez, cuando pasa, y alimentan los umbrales
 * de MotorProgreso sin tener que recalcular sobre los tramos cada vez.
 */
@Entity(
    tableName = "fabula",
    foreignKeys = [
        ForeignKey(entity = AnimalEntity::class, parentColumns = ["id"], childColumns = ["animalAId"]),
        ForeignKey(entity = AnimalEntity::class, parentColumns = ["id"], childColumns = ["animalBId"]),
        ForeignKey(
            entity = EncargoEntity::class, parentColumns = ["id"], childColumns = ["encargoId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("animalAId"), Index("animalBId"), Index("encargoId")]
)
data class FabulaEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val titulo: String = "",
    val animalAId: String,
    val animalBId: String,
    val encargoId: String?,
    val terminada: Boolean = false,
    val fechaCreacionEpochMillis: Long,
    val fechaTerminadaEpochMillis: Long? = null,
    val usoLente: Boolean = false,
    val cumplioReglaDeTres: Boolean = false,
    val esBurladorBurlado: Boolean = false
)
