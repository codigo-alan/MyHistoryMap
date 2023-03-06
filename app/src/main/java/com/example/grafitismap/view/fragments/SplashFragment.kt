package com.example.grafitismap.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.grafitismap.R
import com.example.grafitismap.database.ServiceLocator
import com.example.grafitismap.databinding.FragmentSplashBinding
import com.example.grafitismap.viewmodel.SplashViewModel
import com.example.grafitismap.viewmodel.SplashViewModelState

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private val viewModel : SplashViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.start()

        viewModel.nextFragment.observe(viewLifecycleOwner){
            if (it == SplashViewModelState.MAP) {
                //go to map
                findNavController().navigate(R.id.action_splashFragment_to_mapFragment)
            } else {
                //go to login
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }
        }

    }

}