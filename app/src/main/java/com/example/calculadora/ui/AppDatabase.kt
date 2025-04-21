package com.example.calculadora.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Historico::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historicoDao(): HistoricoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "calculadora_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
