package pe.appmobile.fabricadehistorias.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "racha")
data class RachaEntity(
    @PrimaryKey val id: Int = 1,
    val rachaActual: Int,
    val ultimaFechaJuegoEpochDay: Long
)
