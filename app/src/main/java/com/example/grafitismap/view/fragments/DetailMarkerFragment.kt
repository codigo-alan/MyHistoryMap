package com.example.grafitismap.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.grafitismap.R
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.grafitismap.databinding.FragmentDetailMarkerBinding
import com.example.grafitismap.viewmodel.GrafitisViewModel


class DetailMarkerFragment : Fragment() {

    private lateinit var binding: FragmentDetailMarkerBinding
    private val viewModel: GrafitisViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailMarkerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val marker = viewModel.selectedMarkerModel.value
        binding.nameTv.text = marker?.name
        binding.categoryTv.text = marker?.category
        binding.latitudeTv.text = marker?.latitude.toString()
        binding.longitudeTv.text = marker?.longitude.toString()
        binding.photoIv.setImageURI(marker?.photo?.toUri())

        binding.deleteBtn.setOnClickListener {
            viewModel.deleteMarker(viewModel.selectedMarkerModel.value!!.id)
            //Toast.makeText(context,"Delete button not implemented yet",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_detailMarkerFragment_to_markersListFragment)
        }
    }

}