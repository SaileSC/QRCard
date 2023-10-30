package com.qrcard.iu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.qrcard.R
import com.qrcard.databinding.LoginFragmentBinding
import com.qrcard.iu.fragment.modelview.MainViewModel


class SingInFragment : Fragment() {

    private val binding by lazy { LoginFragmentBinding.inflate(layoutInflater) }
    private val viewMain: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActions()
    }

    private fun setupActions() {
        val navController = findNavController()
        binding.apply {
            tvForgotPassword.setOnClickListener {
                navController.navigate(R.id.go_to_recovePasswordFragment)
            }

            tvSingUp.setOnClickListener {
                navController.navigate(R.id.go_to_singUpFragment)
            }

            btnLogin.setOnClickListener{
                val nome = "Generico"
                val email = etEmail.text

                viewMain.setPerfil(nome = nome, email = email.toString())

                navController.navigate(R.id.go_to_perfilFragment)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.go_to_mainScreen)
        }
    }
}