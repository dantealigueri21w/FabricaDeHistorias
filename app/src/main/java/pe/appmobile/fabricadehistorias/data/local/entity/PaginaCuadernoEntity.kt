package pe.appmobile.fabricadehistorias.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Una página del Cuaderno del Aprendiz, una por herramienta (12 en total, ya
 * sembradas vacías). [fraseElegida] la llena el niño: él decide cuál de sus
 * frases merece guardarse, la app nunca la elige por él.
 */
@Entity(tableName = "pagina_cuaderno")
data class PaginaCuadernoEntity(
    @PrimaryKey val herramienta: String,
    val fraseElegida: String? = null,
    val fechaGuardadaEpochMillis: Long? = null
)
