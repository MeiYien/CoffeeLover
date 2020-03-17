package com.my.chin.coffeelover.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.my.chin.coffeelover.database.Cart
import com.my.chin.coffeelover.databinding.CartItemBinding
import com.squareup.picasso.Picasso


class CartAdapter(private val list: List<Cart>,val clickListener:QuantityListener) :RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CartItemBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], clickListener)
/*        holder.btn.setOnClickListener{
            Log.e("btn","clicked")
            viewModel.addQuantity("Latte")
        }*/
    }

    class ViewHolder(private val binding: CartItemBinding):RecyclerView.ViewHolder(binding.root){

        lateinit var btn: ImageButton

        fun bind(
            cart: Cart,
            clickListener: QuantityListener
        ){
            with(binding){
                binding.cartData = cart
                Picasso.get().load(cart.image).into(imageView2)
                binding.position = position
                binding.lifecycleOwner = lifecycleOwner
                binding.vm = vm
                binding.clickListener = clickListener
                //btn = binding.btnAdd
            }
        }

//        init {
//            binding.setClickListener{
//                binding.btnAdd.let {
//
//                    //Log.e("btn","clicked")
//                    //binding.tvQuantity.text = binding.cartData.quantity.toString()
//                }
////                var quantity =binding.tvQuantity.text.toString()
////                var number = quantity.toInt()
////                binding.btnAdd?.let {
////                    var t=coffeeViewModel.addQuantity(number)
////                    binding.tvQuantity.text = t.toString()
////                }
//            }
//
//        }

    }
    fun increaseQuantity(position: Int) {
        val cart: Cart = list[position]
        cart.quantity += 1
        notifyItemChanged(position)
    }

    fun decreaseQuantity(position: Int) {
        val cart: Cart = list[position]
        cart.quantity -= 1
        notifyItemChanged(position)
    }
}

class QuantityListener(val clickListener:(cartId: String) -> Unit){
    fun onClick(cart: Cart)=clickListener(cart.name)
}

