package pe.appmobile.fabricadehistorias.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pe.appmobile.fabricadehistorias.data.local.entity.EncargoEntity

@Dao
interface EncargoDao {
    @Query("SELECT COUNT(*) FROM encargo")
    suspend fun contar(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTodos(encargos: List<EncargoEntity>)

    @Query("SELECT * FROM encargo WHERE id NOT IN (SELECT encargoId FROM fabula WHERE encargoId IS NOT NULL) ORDER BY RANDOM() LIMIT 1")
    suspend fun obtenerUnoSinUsar(): EncargoEntity?

    @Query("SELECT * FROM encargo ORDER BY RANDOM() LIMIT 1")
    suspend fun obtenerUnoAlAzar(): EncargoEntity?
}
