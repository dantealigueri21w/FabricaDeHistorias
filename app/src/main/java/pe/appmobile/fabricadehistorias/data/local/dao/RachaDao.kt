package pe.appmobile.fabricadehistorias.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pe.appmobile.fabricadehistorias.data.local.entity.RachaEntity

@Dao
interface RachaDao {
    @Query("SELECT * FROM racha WHERE id = 1")
    suspend fun obtener(): RachaEntity?

    @Query("SELECT * FROM racha WHERE id = 1")
    fun observar(): Flow<RachaEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardar(racha: RachaEntity)
}
