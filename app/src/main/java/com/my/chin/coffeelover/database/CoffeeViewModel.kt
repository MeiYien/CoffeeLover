package com.my.chin.coffeelover.database

import android.app.Application
import androidx.lifecycle.*
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoffeeViewModel(application: Application) : AndroidViewModel(application) {

    val db = Room.databaseBuilder(application, CoffeeDatabase::class.java, "coffee_database").build()
    val db2 = Room.databaseBuilder(application,CartDatabase::class.java,"cart_database").build()

    val allCoffee : LiveData<List<Coffee>> = db.coffeeDao().getAll()
    val allCart : LiveData<List<Cart>> = db2.cartDao().getAllCart()

    init {
//        Log.d("Viewmodel", allCoffee.toString())
//        Log.d("Viewmodel", "created")
        viewModelScope.launch {
            dummyData().forEach {
                withContext(Dispatchers.IO){
                    db.coffeeDao().insertAll(it)
                }
            }
        }
    }

    suspend fun addToCart(name: String?, image: String) {
        withContext(Dispatchers.IO){
            db2.cartDao().insertAll(Cart(name!!,image))
        }
    }

    fun onAddClicked(name:String){
        db2.cartDao().increaseQuantity(name)
    }
//    fun addQuantity() {
//        db2.cartDao().increaseQuantity()
//    }
    fun minusQuantity(){
        db2.cartDao().decreaseQuantity()
    }
    fun deleteCoffee(){

    }

    fun dummyData(): List<Coffee> {
        val list = ArrayList<Coffee>()
        list.add(
            Coffee(
                "Latte",
                "https://www.thespruceeats.com/thmb/tSxyPYh37U34ba7sNn_nSvN3lnQ=/2446x2446/smart/filters:no_upscale()/how-to-make-caffe-latte-765372-Hero-5bbe0901c9e77c0026bf07c4.jpg",
                "Caffe latte (or simply latte) (/ˈlɑːteɪ/ or /ˈlæteɪ/) is a coffee drink made with espresso and steamed milk."
            )
        )
        list.add(
            Coffee(
                "Cappuccino",
                "https://www.caffesociety.co.uk/assets/recipe-images/cappuccino-small.jpg",
                "A cappuccino is an espresso-based coffee drink that originated in Italy, and is traditionally prepared with steamed milk foam (microfoam)."

            )
        )
        list.add(
            Coffee(
                "Espresso",
                "https://www.delonghi.com/Global/recipes/Coffee/espresso.png",
                "Espresso is a coffee-making method of Italian origin, in which a small amount of nearly boiling water is forced under pressure through finely-ground coffee beans."
            )
        )
        list.add(
            Coffee(
                "Flat White",
                "https://www.bbcgoodfood.com/sites/default/files/recipe/recipe-image/2018/03/flat-white.jpg",
                "A flat white is a coffee drink consisting of espresso with microfoam. It is comparable to a latte, but smaller in volume and with less microfoam"
            )
        )
        list.add(
            Coffee(
                "Macchiato",
                "https://www.nespresso.com/ncp/res/uploads/recipes/nespresso-recipes-Espresso-Macchiato.jpg",
                "Caffè macchiato, sometimes called espresso macchiato, is an espresso coffee drink with a small amount of milk, usually foamed."
            )
        )
        list.add(
            Coffee(
                "Mochaccino",
                "https://www.craftycookingmama.com/wp-content/uploads/2019/01/018.2.jpg",
                "A caffè mocha, also called mocaccino, is a chocolate-flavoured variant of a caffè latte. Other commonly used spellings are mochaccino and also mochachino."
            )
        )
        return list
    }
}



