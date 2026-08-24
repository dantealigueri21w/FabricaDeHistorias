package pe.appmobile.fabricadehistorias.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pe.appmobile.fabricadehistorias.data.local.entity.TramoEntity

@Dao
interface TramoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardar(tramo: TramoEntity)

    @Query("SELECT * FROM tramo WHERE fabulaId = :fabulaId")
    suspend fun obtenerDeFabula(fabulaId: Long): List<TramoEntity>
}
