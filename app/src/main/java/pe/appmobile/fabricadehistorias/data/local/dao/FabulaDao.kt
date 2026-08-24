package pe.appmobile.fabricadehistorias.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pe.appmobile.fabricadehistorias.data.local.entity.FabulaEntity

@Dao
interface FabulaDao {
    @Insert
    suspend fun crear(fabula: FabulaEntity): Long

    @Update
    suspend fun actualizar(fabula: FabulaEntity)

    @Query("SELECT * FROM fabula WHERE id = :id")
    suspend fun obtenerPorId(id: Long): FabulaEntity?

    @Query("SELECT * FROM fabula WHERE terminada = 1 ORDER BY fechaTerminadaEpochMillis DESC")
    fun observarTerminadas(): Flow<List<FabulaEntity>>

    @Query("SELECT COUNT(*) FROM fabula WHERE terminada = 1")
    suspend fun contarTerminadas(): Int

    /** Las que el niño empezó y dejó a medias: el material real del Rincón de Práctica. */
    @Query("SELECT * FROM fabula WHERE terminada = 0 ORDER BY fechaCreacionEpochMillis DESC")
    suspend fun obtenerSinTerminar(): List<FabulaEntity>

    @Query("SELECT COUNT(*) FROM fabula WHERE terminada = 1 AND usoLente = 1")
    suspend fun contarConLenteUsada(): Int

    @Query("SELECT COUNT(*) FROM fabula WHERE terminada = 1 AND cumplioReglaDeTres = 1")
    suspend fun contarConReglaDeTres(): Int

    @Query("SELECT COUNT(*) FROM fabula WHERE terminada = 1 AND esBurladorBurlado = 1")
    suspend fun contarBurladorBurlado(): Int
}
