package pe.appmobile.fabricadehistorias.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "encargo")
data class EncargoEntity(
    @PrimaryKey val id: String,
    val dificultad: String,
    val textoAntuco: String,
    val restriccion: String
)
