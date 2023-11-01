package net.mguler.weatherapp.frag

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.material.snackbar.Snackbar
import net.mguler.weatherapp.R
import net.mguler.weatherapp.databinding.FragmentEditBinding
import net.mguler.weatherapp.model.SelectedCity
import net.mguler.weatherapp.utils.GeoViewModel
import net.mguler.weatherapp.utils.Utils
import net.mguler.weatherapp.utils.WeatherViewModel
import net.mguler.weatherapp.utils.WeatherViewModel.WeatherStatus.DONE
import net.mguler.weatherapp.utils.WeatherViewModel.WeatherStatus.LOADING

class EditFragment : Fragment() {
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!
    private val geoVM: GeoViewModel by activityViewModels()
    private val weatherVM: WeatherViewModel by activityViewModels()

    private var selectedCity: SelectedCity? = null
    private var geoList = ArrayList<String>()

    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private val permCoarse = Manifest.permission.ACCESS_COARSE_LOCATION

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerUserLocation()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.autoTextCities.addTextChangedListener {
            if ( it.toString().length > 2) { searchCity(it.toString()) }
        }

        observeCityData()

        binding.fabSelect.setOnClickListener {
            selectedCity?.let { saveSelectedCity(it) }
        }

        binding.fabFindMe.setOnClickListener { askLocation(it) }
    }

    private fun searchCity(name: String) { geoVM.getGeoData(name) }

    private fun observeCityData() {
        geoVM.geoData.observe(viewLifecycleOwner) { geo->
            geoList.clear()
            geo.results?.forEach { item ->
                item?.let {
                    val name = "${it.name}, ${it.admin1}"
                    geoList.add(name)
                }
            }

            val citiesAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, geoList)
            binding.autoTextCities.setAdapter(citiesAdapter)
            binding.autoTextCities.showDropDown()

            binding.autoTextCities.setOnItemClickListener { _, _, i, _ ->
                geo.results?.get(i)?.let {
                    selectedCity = SelectedCity(it.name!!, it.latitude!!, it.longitude!!)
                }
                // TODO: auto edittext update
            }
        }
    }

    private fun askLocation(v: View) {
        val permOK = PackageManager.PERMISSION_GRANTED
        when {
            ContextCompat.checkSelfPermission(requireContext(), permCoarse) == permOK -> {
                getUserLocation()
            }
            shouldShowRequestPermissionRationale(permCoarse) -> {
                Snackbar.make(v, "Permission Needed!", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Give It!") { permissionLauncher.launch(permCoarse) }.show()
            }
            else -> {
                permissionLauncher.launch(permCoarse)
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun registerUserLocation() {
        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                when (it) {
                    true -> { getUserLocation() }
                    else -> {
                        Toast.makeText(requireContext(), "Permission Denied!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    @SuppressLint("MissingPermission")
    private fun getUserLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY, null)
            .addOnSuccessListener { loc ->
                selectedCity = SelectedCity("Custom", loc.latitude, loc.longitude)
                saveSelectedCity(selectedCity!!)
                findNavController().navigate(R.id.action_editFragment_to_weatherFragment)
            }
            .addOnFailureListener { e -> println(e.localizedMessage) }
    }

    private fun saveSelectedCity(city: SelectedCity) {
        val loading = Dialog(requireContext()).apply { setContentView(R.layout.dialog_loading) }

        weatherVM.getWeatherData(city)
        weatherVM.status.observe(viewLifecycleOwner) {
            when (it) {
                LOADING -> loading.show()
                DONE -> {
                    loading.dismiss()
                    val sp = requireContext().getSharedPreferences(Utils.PREFS, Context.MODE_PRIVATE)
                    val prefStr = "${city.cityName}:${city.lat}:${city.lng}"
                    sp.edit().putString("location", prefStr).apply()
                    //findNavController().navigate(R.id.action_editFragment_to_weatherFragment)
                    findNavController().navigateUp()
                }
                else -> loading.dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //Hide keyboard
        val inputMethodManager = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }
}