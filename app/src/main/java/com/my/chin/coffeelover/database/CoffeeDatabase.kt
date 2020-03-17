package com.my.chin.coffeelover.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Coffee::class])
abstract class CoffeeDatabase : RoomDatabase() {
    abstract fun coffeeDao(): CoffeeDao
}


