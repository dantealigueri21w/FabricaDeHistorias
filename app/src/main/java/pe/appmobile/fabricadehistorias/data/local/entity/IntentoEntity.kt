package pe.appmobile.fabricadehistorias.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Registra cada validación real: la lente, la regla de tres, el burlador
 * burlado o la moraleja, siempre atados a una fábula. [cantidad] guarda un
 * valor además del sí/no cuando hace falta —por ejemplo, cuántas palabras
 * quitó la criba en ese intento— para insignias como Mano Ligera.
 */
@Entity(
    tableName = "intento",
    foreignKeys = [
        ForeignKey(
            entity = FabulaEntity::class, parentColumns = ["id"], childColumns = ["fabulaId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("fabulaId")]
)
data class IntentoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val fabulaId: Long,
    val tipo: String,
    val fueCorrecto: Boolean,
    val cantidad: Int = 1,
    val fechaEpochMillis: Long
)
