package com.my.chin.coffeelover.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.my.chin.coffeelover.R
import com.my.chin.coffeelover.database.CoffeeViewModel
import com.my.chin.coffeelover.databinding.FragmentResultBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch


class ResultFragment : Fragment() {
    //4-declare view model in fragment that you wanna receive data
        private val viewmodel : CoffeeViewModel by activityViewModels()

    private val args: ResultFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentResultBinding>(inflater,
            R.layout.fragment_result,container,false)
        (activity as AppCompatActivity).let { settings ->    //code for toolbar
            settings.setSupportActionBar(binding.myToolbar)
            val toolbar = settings.supportActionBar
            toolbar!!.setDisplayShowTitleEnabled(true)
            toolbar.title = "Details"
            setHasOptionsMenu(true)
        }

        Picasso.get()
            .load(args.image)
            .into(binding.imageView)

        binding.tvName.text = args.name
        binding.tvDetails.text=args.details

        binding.btnBack.setOnClickListener {
            it.findNavController().navigate(R.id.action_resultFragment_to_mainFragment)
        }
        binding.btnAddCart.setOnClickListener {
            Toast.makeText(it.context,args.name+" add to the cart",Toast.LENGTH_SHORT).show()
            viewLifecycleOwner.lifecycleScope.launch {
                viewmodel.addToCart(args.name,args.image)
            }
        }
        //5-observe live data

//        viewmodel.details.observe(viewLifecycleOwner, Observer {
//            binding.tvDetails.text=it
//        })

        return binding.root
    }



}
