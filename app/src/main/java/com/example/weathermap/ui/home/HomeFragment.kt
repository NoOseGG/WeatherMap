package com.example.weathermap.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.weathermap.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHomeBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAllCountries.setOnClickListener {
            val actionToMap = HomeFragmentDirections.actionHomeFragmentToMapFragment()
            findNavController().navigate(actionToMap)
        }

        with(binding) {
            btnSend.setOnClickListener {
                if(edCountry.text.isEmpty()) {
                    showToast()
                    return@setOnClickListener
                }
                val country = edCountry.text.toString().lowercase()
                val actionToCountryDetails = HomeFragmentDirections.actionHomeFragmentToCountryDetailsFragment(country)
                edCountry.text.clear()
                findNavController().navigate(actionToCountryDetails)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showToast() {
        Toast.makeText(requireContext(), "Enter the name of the country", Toast.LENGTH_SHORT)
            .show()
    }
}