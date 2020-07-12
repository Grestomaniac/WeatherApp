package com.example.weatherapp.ui.searchScreen

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.weatherapp.R
import com.example.weatherapp.databinding.MainFragmentBinding
import com.example.weatherapp.databinding.SearchLocalityFragmentBinding
import com.example.weatherapp.ui.mainScreen.MainScreenViewModel

class SearchLocalityFragment : Fragment() {

    companion object {
        fun newInstance() = SearchLocalityFragment()
    }

    private val viewModel: SearchLocalityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: SearchLocalityFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.search_locality_fragment, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}