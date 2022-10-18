package com.example.bitfit2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDAO2 {
    @Query("SELECT * FROM food_table2")
    fun getAll(): Flow<List<FoodEntity>>

    @Insert
    fun insert(foods: FoodEntity)

    @Query("DELETE FROM food_table2")
    fun deleteAll()


    @Query("SELECT MAX(calories) FROM food_table2")
    fun max():Float
    @Query("SELECT MIN(calories) FROM food_table2")
    fun min() :Float
    @Query("SELECT AVG(calories) FROM food_table2")
    fun avg() :Float

}