package com.example.weathermap.ui.countrydetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.weathermap.R
import com.example.weathermap.databinding.FragmentCountryDetailsBinding
import com.example.weathermap.model.LceState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CountryDetailsFragment : Fragment() {

    private var _binding: FragmentCountryDetailsBinding? = null
    private val binding get() = requireNotNull(_binding)
    /*private val args: CountryDetailsFragmentArgs by navArgs()*/
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
                    println(lce.value)
                    binding.tvCountry.text = lce.value.name
                    binding.imgFlag.load(lce.value.flag ?: resources.getDrawable(R.drawable.ic_icon_null))
                    binding.tvPopulation.text = getString(R.string.population, lce.value.population)
                    binding.tvContinent.text = getString(R.string.continent, lce.value.continents)

                    viewModel.sendCoordinates(lce.value.latlng)
                }
                is LceState.Error -> {
                    Toast.makeText(requireContext(), getString(R.string.toast_no_country), Toast.LENGTH_SHORT)
                        .show()
                    findNavController().popBackStack()
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.weatherFlow.onEach { lce ->
            when(lce) {
                is LceState.Content -> {
                    binding.tvTemperature.text = getString(R.string.temperature, lce.value.temperature)
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

    private fun checkLandLocked(flag: Boolean): String {
        return if(!flag) "Да" else "Нет"
    }
}