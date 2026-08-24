package pe.appmobile.fabricadehistorias.di

import android.content.Context
import pe.appmobile.fabricadehistorias.data.local.AppDatabase
import pe.appmobile.fabricadehistorias.data.repository.FabricaRepository

/** Contenedor manual de dependencias. No hace falta Hilt para el tamaño de esta app. */
class AppContainer(context: Context) {
    private val database = AppDatabase.obtener(context)
    val repositorio = FabricaRepository(database)
}
