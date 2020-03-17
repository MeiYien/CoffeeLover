package com.my.chin.coffeelover.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.my.chin.coffeelover.R
import com.my.chin.coffeelover.adapter.CartAdapter
import com.my.chin.coffeelover.adapter.QuantityListener
import com.my.chin.coffeelover.database.Cart
import com.my.chin.coffeelover.database.CoffeeViewModel
import com.my.chin.coffeelover.databinding.FragmentCartBinding

class CartFragment: Fragment() {

    private val viewModel: CoffeeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCartBinding>(
            inflater,
            R.layout.fragment_cart, container, false
        )
        (activity as AppCompatActivity).let { settings ->    //code for toolbar
            settings.setSupportActionBar(binding.myToolbar)
            val toolbar = settings.supportActionBar
            toolbar!!.setDisplayShowTitleEnabled(true)
            toolbar.setTitle("Cart")
            setHasOptionsMenu(true)
        }
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.allCart.observe(viewLifecycleOwner, Observer {
            binding.cartList.adapter = CartAdapter(it, QuantityListener {
                cartId ->  Toast.makeText(context, "${cartId}+added", Toast.LENGTH_LONG).show()
                viewModel.onAddClicked(cartId)
            })
        })
        // Inflate the layout for this fragment
        return binding.root
    }


}
