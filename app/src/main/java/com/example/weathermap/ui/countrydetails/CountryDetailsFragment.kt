package com.example.weathermap.ui.countrydetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.weathermap.databinding.FragmentCountryDetailsBinding
import com.example.weathermap.model.LceState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CountryDetailsFragment : Fragment() {

    private var _binding: FragmentCountryDetailsBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val args: CountryDetailsFragmentArgs by navArgs()
    private val viewModel: CountryDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCountryDetailsBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.countryFlow.onEach { lce ->
            when(lce) {
                is LceState.Content -> {
                    Log.i("MyTag", "${lce.value}")
                    binding.tvCountryName.text = lce.value.name
                    binding.imgFlag.load(lce.value.flag)
                    binding.beach.text = checkLandLocked(lce.value.isLandLocked)

                    //send coordinates in view model, and get weather from coordinates
                    viewModel.sendCoordinates(lce.value.latlng)
                }
                is LceState.Error -> {
                    Toast.makeText(requireContext(), lce.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.weatherFlow.onEach { lce ->
            when(lce) {
                is LceState.Content -> {
                    binding.country.text = lce.value.country
                    binding.temperature.text = lce.value.temperature.toString()
                }
                is LceState.Error -> {
                    println("ERROR: ${lce.throwable.message}")
                    Toast.makeText(requireContext(), lce.throwable.message, Toast.LENGTH_SHORT).show()
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkLandLocked(flag: Boolean): String {
        return if(!flag) "Да" else "Нет"
    }
}