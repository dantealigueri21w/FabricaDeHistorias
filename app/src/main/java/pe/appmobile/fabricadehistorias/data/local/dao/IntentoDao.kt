package pe.appmobile.fabricadehistorias.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pe.appmobile.fabricadehistorias.data.local.entity.IntentoEntity

@Dao
interface IntentoDao {
    @Insert
    suspend fun registrar(intento: IntentoEntity): Long

    @Query("SELECT COUNT(DISTINCT fabulaId) FROM intento WHERE tipo = :tipo AND fueCorrecto = 1")
    suspend fun contarFabulasDistintasDeTipo(tipo: String): Int

    @Query("SELECT COUNT(*) FROM intento WHERE tipo = :tipo AND fueCorrecto = 1")
    suspend fun contarCorrectosDeTipo(tipo: String): Int
}
