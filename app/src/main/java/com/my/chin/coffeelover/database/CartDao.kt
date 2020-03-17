package com.my.chin.coffeelover.database

import android.view.View
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface CartDao {
    @Query("select * from cart_table")
    fun getAllCart(): LiveData<List<Cart>>

    @Query("UPDATE cart_table SET quantity = quantity+1 WHERE name=:name")
    fun increaseQuantity(name: String)

    @Query("UPDATE cart_table SET quantity = quantity-1")
    fun decreaseQuantity()

    @Query("select quantity from cart_table WHERE name=:name")
    fun getCart(name:String): Int

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(cart: Cart)

    @Delete
    fun delete(cart: Cart)
}