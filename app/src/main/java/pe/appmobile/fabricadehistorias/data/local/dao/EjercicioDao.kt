package pe.appmobile.fabricadehistorias.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pe.appmobile.fabricadehistorias.data.local.entity.EjercicioEntity

@Dao
interface EjercicioDao {
    @Insert
    suspend fun registrar(ejercicio: EjercicioEntity): Long

    @Query("SELECT COALESCE(SUM(cantidad), 0) FROM ejercicio WHERE tipo = :tipo AND confirmado = 1")
    suspend fun sumarCantidadConfirmadaDeTipo(tipo: String): Int
}
