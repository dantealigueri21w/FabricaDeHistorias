package pe.appmobile.fabricadehistorias.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pe.appmobile.fabricadehistorias.data.local.entity.PaginaCuadernoEntity

@Dao
interface PaginaCuadernoDao {
    @Query("SELECT COUNT(*) FROM pagina_cuaderno")
    suspend fun contar(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTodas(paginas: List<PaginaCuadernoEntity>)

    @Query("SELECT * FROM pagina_cuaderno")
    fun observarTodas(): Flow<List<PaginaCuadernoEntity>>

    @Update
    suspend fun actualizar(pagina: PaginaCuadernoEntity)

    @Query("SELECT COUNT(*) FROM pagina_cuaderno WHERE fraseElegida IS NOT NULL")
    suspend fun contarLlenas(): Int
}
