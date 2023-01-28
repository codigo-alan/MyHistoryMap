package com.example.grafitismap.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.grafitismap.R
import com.example.grafitismap.adapters.OnClickListener
import com.example.grafitismap.databinding.FragmentMarkersListBinding
import com.example.grafitismap.models.Marker


class MarkersListFragment : Fragment(), OnClickListener {


    private lateinit var binding: FragmentMarkersListBinding

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
    }

    override fun onClick(marker: Marker) {
        //TODO("Not yet implemented")
    }

}