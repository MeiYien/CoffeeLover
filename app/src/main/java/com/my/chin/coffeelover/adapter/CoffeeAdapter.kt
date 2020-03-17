package com.my.chin.coffeelover.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.my.chin.coffeelover.database.Coffee
import com.my.chin.coffeelover.databinding.CoffeeItemBinding
import com.my.chin.coffeelover.fragment.MainFragmentDirections
import com.squareup.picasso.Picasso

class CoffeeAdapter(private val list: List<Coffee>) :
    RecyclerView.Adapter<CoffeeAdapter.ViewHolder>() {

    //private val inflater: LayoutInflater = LayoutInflater.from(context)
    //private var words = emptyList<Coffee>() // Cached copy of words

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CoffeeItemBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])

//        val item = list[position]
//        holder.name.text = item.name
//        holder.qty = item.quantity.toString()
//        Picasso.get().load(item.image).resize(50, 50).centerCrop().into(holder.coffeeImage)
    }

    class ViewHolder(private val binding: CoffeeItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(coffeeData: Coffee) {
            with(binding) {
                binding.coffeeData = coffeeData //this line replace all item findViewById val name: TextView = itemView.findViewById(R.id.tv_coffee_name)
                Picasso.get()
                    .load(coffeeData.image)
                    .into(imgCoffee)
            }

        }

        init {

            binding.setClickListener {
                binding.coffeeData?.let {
                    coffeeData -> navigateToResultFragment(coffeeData,it)
                }
            }
        }

        private fun navigateToResultFragment(coffeeData: Coffee, view: View) {
            val direction =
                MainFragmentDirections.actionMainFragmentToResultFragment(
                    coffeeData.detail,
                    coffeeData.image,
                    coffeeData.name
                )
            view.findNavController().navigate(direction)
        }
    }


}



