package pe.appmobile.fabricadehistorias.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pe.appmobile.fabricadehistorias.data.local.entity.VisitanteEntity

@Dao
interface VisitanteDao {
    @Query("SELECT COUNT(*) FROM visitante")
    suspend fun contar(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTodos(visitantes: List<VisitanteEntity>)

    @Query("SELECT * FROM visitante ORDER BY orden")
    fun observarTodos(): Flow<List<VisitanteEntity>>

    @Query("SELECT * FROM visitante WHERE recibido = 0 ORDER BY orden LIMIT 1")
    suspend fun obtenerSiguientePorRecibir(): VisitanteEntity?

    @Query("SELECT COUNT(*) FROM visitante WHERE recibido = 1")
    suspend fun contarRecibidos(): Int

    @Update
    suspend fun actualizar(visitante: VisitanteEntity)
}
