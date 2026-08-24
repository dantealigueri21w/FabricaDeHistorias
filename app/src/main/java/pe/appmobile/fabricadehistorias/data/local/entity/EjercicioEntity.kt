package pe.appmobile.fabricadehistorias.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Un uso de una de las tres máquinas de la Sala de Pulido (prensa, fuelle,
 * criba) sobre una frase real del propio niño. [tipo] es "PRENSA", "FUELLE"
 * o "CRIBA". No depende de una fábula concreta: se pulen frases sueltas de los
 * ejercicios sembrados. [cantidad] vale 1 para prensa y fuelle (una fusión o
 * una expansión), y el número real de palabras quitadas para la criba —
 * Mano Ligera cuenta palabras, no usos de la máquina.
 */
@Entity(tableName = "ejercicio")
data class EjercicioEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val tipo: String,
    val textoResultado: String,
    val confirmado: Boolean,
    val cantidad: Int = 1,
    val fechaEpochMillis: Long
)
