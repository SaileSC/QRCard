package com.qrcard.iu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.qrcard.R
import com.qrcard.databinding.LoginFragmentBinding
import com.qrcard.iu.fragment.modelview.UserViewModel
import kotlinx.coroutines.launch


class SingInFragment : Fragment() {

    private val binding by lazy { LoginFragmentBinding.inflate(layoutInflater) }
    private val userView : UserViewModel by activityViewModels()


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
                val email = etEmail.text.toString()
                val senha = etSenha.text.toString()


                lifecycleScope.launch {
                    if(userView.singInUser(email, senha)){
                        navController.navigate(R.id.go_to_perfilFragment)
                    }else{
                        Toast.makeText(context, "Erro de login ou senha", Toast.LENGTH_SHORT).show()
                    }
                }
                }

        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navController.navigateUp()
        }
    }
}