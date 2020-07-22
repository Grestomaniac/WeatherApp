package com.example.weatherapp.ui.mainScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.MainFragmentBinding

class MainScreenFragment : Fragment() {

    companion object {
        fun newInstance() = MainScreenFragment()
    }

    //loading viewModel
    private val viewModel: MainScreenViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding data
        val binding: MainFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.search.setOnClickListener { onSearchButtonClick(it) }
        // TODO: add adapter for main recycler


        return binding.root
    }

    //navigating to searchScreen
    private fun onSearchButtonClick(v: View) {
        v.findNavController().navigate(R.id.action_mainFragment_to_searchLocalityFragment)
    }

}