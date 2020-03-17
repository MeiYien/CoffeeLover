package com.my.chin.coffeelover.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
data class Cart(
    @PrimaryKey val name: String,
    val image: String,
    var quantity: Int = 1
)


