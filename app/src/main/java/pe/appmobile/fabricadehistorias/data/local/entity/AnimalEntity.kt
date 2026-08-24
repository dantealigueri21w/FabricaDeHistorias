package pe.appmobile.fabricadehistorias.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/** [caracter] guarda el nombre del enum [pe.appmobile.fabricadehistorias.domain.model.Caracter]. */
@Entity(tableName = "animal")
data class AnimalEntity(
    @PrimaryKey val id: String,
    val nombre: String,
    val region: String,
    val caracter: String
)
