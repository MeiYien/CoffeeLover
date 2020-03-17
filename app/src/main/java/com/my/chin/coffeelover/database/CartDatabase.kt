package com.my.chin.coffeelover.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Cart::class])
abstract class CartDatabase:RoomDatabase() {

    abstract fun cartDao() :CartDao
}