package com.example.grafitismap.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.example.grafitismap.R
import com.example.grafitismap.adapters.MarkerAdapter
import com.example.grafitismap.adapters.OnClickListener
import com.example.grafitismap.databinding.FragmentMarkersListBinding
import com.example.grafitismap.models.Marker
import com.example.grafitismap.viewmodel.GrafitisViewModel


class MarkersListFragment : Fragment(), OnClickListener {


    private lateinit var binding: FragmentMarkersListBinding
    private lateinit var markerAdapter: MarkerAdapter
    private lateinit var myLayoutManager: RecyclerView.LayoutManager
    private val viewModel: GrafitisViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMarkersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        markerAdapter = MarkerAdapter(viewModel.data.value!!, this)

        viewModel.data.observe(viewLifecycleOwner){
            markerAdapter.setMarkers(it)
        }

        myLayoutManager = GridLayoutManager(context, 3)

        binding.recyclerListMarkers.apply {
            setHasFixedSize(true)
            layoutManager = myLayoutManager
            adapter = markerAdapter
        }

    }

    override fun onClick(marker: Marker) {
        viewModel.selectMarker(marker)
        findNavController().navigate(R.id.action_markersListFragment_to_detailMarkerFragment)
    }

}