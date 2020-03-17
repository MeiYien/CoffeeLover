package com.my.chin.coffeelover.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coffee_table")
data class Coffee(
    @PrimaryKey val name: String,
    val image: String,
    val detail: String
)


