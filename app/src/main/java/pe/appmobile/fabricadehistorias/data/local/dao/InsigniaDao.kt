package pe.appmobile.fabricadehistorias.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pe.appmobile.fabricadehistorias.data.local.entity.InsigniaEntity

@Dao
interface InsigniaDao {
    @Query("SELECT COUNT(*) FROM insignia")
    suspend fun contar(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTodas(insignias: List<InsigniaEntity>)

    @Query("SELECT * FROM insignia")
    fun observarTodas(): Flow<List<InsigniaEntity>>

    @Query("SELECT id FROM insignia WHERE obtenida = 1")
    suspend fun obtenerIdsGanadas(): List<String>

    @Update
    suspend fun actualizar(insignia: InsigniaEntity)
}
