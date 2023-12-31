package com.example.grafitismap.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.grafitismap.R
import com.example.grafitismap.databinding.FragmentSignUpBinding
import com.example.grafitismap.view.MainActivity
import com.example.grafitismap.viewmodel.GrafitisViewModel
import com.example.grafitismap.viewmodel.RegisterViewModel

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: RegisterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toLoginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        if (binding.emailEt.text.isNotEmpty() && binding.passwordEt.text.isNotEmpty()
            && binding.repeatPasswordEt.text.isNotEmpty()) {
            if (binding.passwordEt.text != binding.repeatPasswordEt.text){
                binding.spanTv.visibility = View.VISIBLE
                //binding.signUpBtnId.isEnabled = false
            }else{
                binding.spanTv.visibility = View.GONE
                //binding.signUpBtnId.isEnabled = true
            }
        }else{
            binding.spanTv.visibility = View.GONE
            //binding.signUpBtnId.isEnabled = false
        }

        binding.signUpBtnId.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()

            viewModel.register(email, password)
            viewModel.login(email, password)

        }

        viewModel.userState.observe(viewLifecycleOwner){
            if (it) findNavController().navigate(R.id.action_signUpFragment_to_mapFragment)
            else Log.d("logged", " CAN`T REGISTER ")
        }


    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.hide() //hides action bar
    }

    override fun onStop() {
        super.onStop()
        (requireActivity() as AppCompatActivity).supportActionBar?.show() //shows again action bar when this fragment finish
    }
}