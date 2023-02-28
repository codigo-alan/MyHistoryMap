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
import com.example.grafitismap.databinding.FragmentLoginBinding
import com.example.grafitismap.view.MainActivity
import com.example.grafitismap.viewmodel.GrafitisViewModel
import com.example.grafitismap.viewmodel.RegisterViewModel


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel : RegisterViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toRegisterBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        binding.loginBtnId.isEnabled = binding.emailEt.text.isNotEmpty() && binding.passwordEt.text.isNotEmpty()

        binding.loginBtnId.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()

            viewModel.login(email, password)

            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish() //TODO consultar esto
        }
    }


}