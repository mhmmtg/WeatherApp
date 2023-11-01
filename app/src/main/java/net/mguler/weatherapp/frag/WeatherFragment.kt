package net.mguler.weatherapp.frag

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.mguler.weatherapp.R
import net.mguler.weatherapp.databinding.FragmentWeatherBinding
import net.mguler.weatherapp.model.NextHours
import net.mguler.weatherapp.model.SelectedCity
import net.mguler.weatherapp.utils.Utils
import net.mguler.weatherapp.utils.WeatherViewModel

class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private val weatherVM : WeatherViewModel by activityViewModels()

    private lateinit var city: SelectedCity

    //private val args by navArgs<WeatherFragmentArgs>()
    private val nextHoursList = ArrayList<NextHours>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = weatherVM
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textCityName.setOnClickListener {
            findNavController().navigate(R.id.action_weatherFragment_to_editFragment) }

        //Get data
        //binding.textCityName.text = "Loading.."
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}