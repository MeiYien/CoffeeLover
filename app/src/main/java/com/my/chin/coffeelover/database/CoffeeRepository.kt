package com.my.chin.coffeelover.database

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class CoffeeRepository {
//
//    // Room executes all queries on a separate thread.
//    // Observed LiveData will notify the observer when the data has changed.
//    private var coffeeDao:CoffeeDao
//    private var allCoffee: LiveData<List<Coffee>>
//
//    constructor(application: Application){
//        val db = CoffeeDatabase.getInstance(application)
//        coffeeDao = db!!.coffeeDao()
//        allCoffee = coffeeDao.getAll()
//    }
//
//    fun allCoffee():LiveData<List<Coffee>>{
//        return  allCoffee
//    }
//
//
//    fun insert(coffee: Coffee) {
//        InsertTask(coffeeDao).execute(coffee)
//    }
//
////    class InsertTask internal constructor(coffeeDao: CoffeeDao): AsyncTask<Coffee, Void, Void>(){
////        private var AsyncCoffeeDao: CoffeeDao
////        init {
////            AsyncCoffeeDao = coffeeDao
////        }
////
////        override fun doInBackground(vararg params: Coffee?): Void {
////            AsyncCoffeeDao.insertAll(params[0])
////
////            return null
////        }
////    }
}