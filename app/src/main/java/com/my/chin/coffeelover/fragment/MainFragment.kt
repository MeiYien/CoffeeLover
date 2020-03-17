package com.my.chin.coffeelover.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.my.chin.coffeelover.R
import com.my.chin.coffeelover.adapter.CoffeeAdapter
import com.my.chin.coffeelover.database.CoffeeViewModel
import com.my.chin.coffeelover.databinding.FragmentMainBinding


class MainFragment : Fragment(){
    //1-shared view model-simple
    private val viewmodel: CoffeeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(
            inflater,
            R.layout.fragment_main, container, false
        )


        (activity as AppCompatActivity).let { settings ->    //code for toolbar
            settings.setSupportActionBar(binding.myToolbar)
            val toolbar = settings.supportActionBar
            toolbar!!.setDisplayShowTitleEnabled(true)
            setHasOptionsMenu(true)
        }

        //2-bind xml life cycle to view life cycle
        binding.lifecycleOwner = viewLifecycleOwner
        viewmodel.allCoffee.observe(viewLifecycleOwner, Observer {
            binding.coffeeList.adapter = CoffeeAdapter(it)
        })

        //val data = dummyData()
//
//        binding.coffeeList.apply {
//            adapter = CoffeeAdapter()
//            layoutManager = LinearLayoutManager(activity)
//        }


//        binding.btnOrder.setOnClickListener { view ->
//
//            view.findNavController().navigate(R.id.action_mainFragment_to_resultFragment)
//
//            //3-value that you wanna assigned for the variable setting here
//            viewmodel.name.value = "Latte"
//
//        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {  //code for inflate menu inside toolbar
        inflater.inflate(R.menu.menu_cart, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_cart -> {
                findNavController().navigate(R.id.action_mainFragment_to_cartFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }






}
