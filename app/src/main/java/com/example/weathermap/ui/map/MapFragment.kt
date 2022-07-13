package com.example.weathermap.ui.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.weathermap.R
import com.example.weathermap.databinding.FragmentMapBinding
import com.example.weathermap.model.LceState
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var _map: GoogleMap? = null
    private val map get() = requireNotNull(_map)
    private val mapFragment by lazy {
        childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
    }
    private val viewModel: MapViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMapBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapFragment.getMapAsync(this)

        viewModel.countriesFlow.onEach { lce ->
            when (lce) {
                is LceState.Content -> {

                    lce.value.filter {
                        it.latlng.isNotEmpty()
                    }
                        .forEach {
                            val latitude = it.latlng.first()
                            val longitude = it.latlng.last()

                            map.addMarker(
                                MarkerOptions()
                                    .position(
                                        LatLng(latitude, longitude)
                                    )
                                    .title(it.name)
                            )
                        }
                }
                is LceState.Error -> {
                    Toast.makeText(requireContext(), lce.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        _map = googleMap

        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isRotateGesturesEnabled = true
        map.uiSettings.isMapToolbarEnabled = true

        map.setOnMarkerClickListener {
            val countryName = it.title ?: ""
            val action = MapFragmentDirections.actionMapFragmentToCountryDetailsFragment(countryName)
            findNavController().navigate(action)

            true
        }
    }
}