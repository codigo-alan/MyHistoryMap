package com.example.grafitismap.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.grafitismap.R
import com.example.grafitismap.databinding.FragmentAddMarkerBinding
import com.example.grafitismap.models.MarkerModel
import com.example.grafitismap.viewmodel.GrafitisViewModel


class AddMarkerFragment : Fragment() {

    private lateinit var binding: FragmentAddMarkerBinding
    private val viewModel : GrafitisViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddMarkerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //arguments from start fragment
        //TODO view wrong assignment 0.0 when are null
        /*val latitude = arguments?.getFloat("latitude_to_add")?.toDouble()
        val longitude = arguments?.getFloat("longitude_to_add")?.toDouble()*/



        /*if (latitude != null && longitude != null) {
            binding.latitudeEt.setText(latitude.toString())
            binding.longitudeEt.setText(longitude.toString())
        }*/

        binding.nameEt.setText(viewModel.newMarkerTemp.name)
        binding.categoryEt.setText(viewModel.newMarkerTemp.category)
        binding.latitudeEt.setText(viewModel.newMarkerTemp.latitude.toString())
        binding.longitudeEt.setText(viewModel.newMarkerTemp.longitude.toString())

        binding.takePhtoBtn.setOnClickListener {
            viewModel.newMarkerTemp.name = binding.nameEt.text.toString()
            viewModel.newMarkerTemp.category = binding.categoryEt.text.toString()
            viewModel.newMarkerTemp.latitude = binding.latitudeEt.text.toString().toDouble()
            viewModel.newMarkerTemp.longitude = binding.longitudeEt.text.toString().toDouble()
            findNavController().navigate(R.id.action_addMarkerFragment_to_cameraFragment)
        }

        binding.addMarkerBtn.setOnClickListener {

            if (getFormState(binding)) {
                val name = binding.nameEt.text.toString()
                val category = binding.categoryEt.text.toString()
                val newLatitude = binding.latitudeEt.text.toString().toDouble()
                val newLongitude = binding.longitudeEt.text.toString().toDouble()

                viewModel.newMarkerTemp.name = name
                viewModel.newMarkerTemp.category = category
                viewModel.newMarkerTemp.latitude = newLatitude
                viewModel.newMarkerTemp.longitude = newLongitude

                Log.d("NEW", "${viewModel.newMarkerTemp}")
                viewModel.addMarker(viewModel.newMarkerTemp)
                Toast.makeText(context,"Agregado nuevo Marker",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addMarkerFragment_to_mapFragment)
            } else {
                Toast.makeText(context,"Debes completar todos los campos con *",Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun getFormState(binding: FragmentAddMarkerBinding): Boolean {
        return  binding.nameEt.text.isNotEmpty() &&
                binding.categoryEt.text.isNotEmpty() &&
                binding.latitudeEt.text.isNotEmpty() &&
                binding.longitudeEt.text.isNotEmpty()
    }

}