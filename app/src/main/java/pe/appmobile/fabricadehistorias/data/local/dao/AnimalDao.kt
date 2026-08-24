package pe.appmobile.fabricadehistorias.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pe.appmobile.fabricadehistorias.data.local.entity.AnimalEntity

@Dao
interface AnimalDao {
    @Query("SELECT COUNT(*) FROM animal")
    suspend fun contar(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTodos(animales: List<AnimalEntity>)

    @Query("SELECT * FROM animal ORDER BY region, nombre")
    fun observarTodos(): Flow<List<AnimalEntity>>

    @Query("SELECT * FROM animal WHERE id = :id")
    suspend fun obtenerPorId(id: String): AnimalEntity?
}
