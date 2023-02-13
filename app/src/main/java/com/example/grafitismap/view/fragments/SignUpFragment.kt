package com.example.grafitismap.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.grafitismap.R
import com.example.grafitismap.databinding.FragmentSignUpBinding
import com.example.grafitismap.view.MainActivity
import com.example.grafitismap.viewmodel.GrafitisViewModel

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: GrafitisViewModel by activityViewModels()

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
        binding.signUpBtnId.setOnClickListener {
            val name = binding.nameEt.text
            val surname = binding.surnameEt.text
            val email = binding.emailEt.text
            val password = binding.passwordEt.text
            //viewModel.realmRepo.value.register(email.toString(), password.toString()) //TODO modify from viewModel
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish() //TODO consultar esto
        }


    }
}