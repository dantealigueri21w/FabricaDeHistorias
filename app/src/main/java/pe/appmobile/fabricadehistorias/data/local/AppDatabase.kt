package pe.appmobile.fabricadehistorias.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pe.appmobile.fabricadehistorias.data.local.dao.AnimalDao
import pe.appmobile.fabricadehistorias.data.local.dao.EjercicioDao
import pe.appmobile.fabricadehistorias.data.local.dao.EncargoDao
import pe.appmobile.fabricadehistorias.data.local.dao.FabulaDao
import pe.appmobile.fabricadehistorias.data.local.dao.InsigniaDao
import pe.appmobile.fabricadehistorias.data.local.dao.IntentoDao
import pe.appmobile.fabricadehistorias.data.local.dao.PaginaCuadernoDao
import pe.appmobile.fabricadehistorias.data.local.dao.PerfilDao
import pe.appmobile.fabricadehistorias.data.local.dao.RachaDao
import pe.appmobile.fabricadehistorias.data.local.dao.TramoDao
import pe.appmobile.fabricadehistorias.data.local.dao.VisitanteDao
import pe.appmobile.fabricadehistorias.data.local.entity.AnimalEntity
import pe.appmobile.fabricadehistorias.data.local.entity.EjercicioEntity
import pe.appmobile.fabricadehistorias.data.local.entity.EncargoEntity
import pe.appmobile.fabricadehistorias.data.local.entity.FabulaEntity
import pe.appmobile.fabricadehistorias.data.local.entity.InsigniaEntity
import pe.appmobile.fabricadehistorias.data.local.entity.IntentoEntity
import pe.appmobile.fabricadehistorias.data.local.entity.PaginaCuadernoEntity
import pe.appmobile.fabricadehistorias.data.local.entity.PerfilEntity
import pe.appmobile.fabricadehistorias.data.local.entity.RachaEntity
import pe.appmobile.fabricadehistorias.data.local.entity.TramoEntity
import pe.appmobile.fabricadehistorias.data.local.entity.VisitanteEntity

@Database(
    entities = [
        PerfilEntity::class,
        AnimalEntity::class,
        VisitanteEntity::class,
        EncargoEntity::class,
        FabulaEntity::class,
        TramoEntity::class,
        EjercicioEntity::class,
        PaginaCuadernoEntity::class,
        IntentoEntity::class,
        InsigniaEntity::class,
        RachaEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun perfilDao(): PerfilDao
    abstract fun animalDao(): AnimalDao
    abstract fun visitanteDao(): VisitanteDao
    abstract fun encargoDao(): EncargoDao
    abstract fun fabulaDao(): FabulaDao
    abstract fun tramoDao(): TramoDao
    abstract fun ejercicioDao(): EjercicioDao
    abstract fun paginaCuadernoDao(): PaginaCuadernoDao
    abstract fun intentoDao(): IntentoDao
    abstract fun insigniaDao(): InsigniaDao
    abstract fun rachaDao(): RachaDao

    companion object {
        @Volatile private var instancia: AppDatabase? = null

        fun obtener(context: Context): AppDatabase {
            return instancia ?: synchronized(this) {
                instancia ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fabricadehistorias.db"
                ).build().also { instancia = it }
            }
        }
    }
}
