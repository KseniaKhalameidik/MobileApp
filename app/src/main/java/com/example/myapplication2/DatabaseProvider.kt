package com.example.myapplication2

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    @Volatile
    private var db: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        // Двойная проверка для потокобезопасности
        return db ?: synchronized(this) {
            db ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "my-database"
            ).build().also { db = it }
        }
    }
}