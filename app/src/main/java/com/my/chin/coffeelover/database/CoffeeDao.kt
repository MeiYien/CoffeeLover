package com.my.chin.coffeelover.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface CoffeeDao {
    @Query("select * from coffee_table")
    fun getAll(): LiveData<List<Coffee>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(coffee: Coffee)

    @Delete
    fun delete(coffee: Coffee)

    @Query("delete from coffee_table")
    suspend fun deleteAll()
}