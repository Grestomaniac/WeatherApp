package com.example.weatherapp.ui.mainScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.MainFragmentBinding
import com.example.weatherapp.ui.mainScreen.recyclerview.DailyForecastAdapter
import com.example.weatherapp.ui.mainScreen.recyclerview.HourlyForecastAdapter

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

        val hourlyForecastAdapter = HourlyForecastAdapter()
        binding.hourlyForecast.adapter = hourlyForecastAdapter

        viewModel.hourlyForecastData.observe(viewLifecycleOwner, Observer {
            it?.let { hourlyForecastAdapter.submitList(it) }
        })

        val dailyForecastAdapter = DailyForecastAdapter()
        binding.dailyForecast.adapter = dailyForecastAdapter

        viewModel.dailyForecastData.observe(viewLifecycleOwner, Observer {
            it?.let { dailyForecastAdapter.submitList(it) }
        })

        return binding.root
    }

    //navigating to searchScreen
    private fun onSearchButtonClick(v: View) {
        v.findNavController().navigate(R.id.action_mainFragment_to_searchLocalityFragment)
    }

}