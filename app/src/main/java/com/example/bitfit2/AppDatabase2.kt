package com.example.bitfit2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [FoodEntity::class], version = 1)
abstract class AppDatabase2 : RoomDatabase() {

    abstract fun foodDAO(): FoodDAO2

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase2? = null

        fun getInstance(context:Context): AppDatabase2 =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase2::class.java, "Food3-db"
            ).build()
    }
}