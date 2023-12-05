package com.qrcard.iu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.qrcard.R
import com.qrcard.databinding.SingInFragmentBinding
import com.qrcard.iu.fragment.modelview.UserViewModel
import kotlinx.coroutines.launch


class SingInFragment : Fragment() {

    private val binding by lazy { SingInFragmentBinding.inflate(layoutInflater) }
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
                val name = etName.text.toString()
                val password = etSenha.text.toString()

                if(checkValues(name, password)){
                    lifecycleScope.launch {
                        pbLoader.isVisible = true
                        ivPerfil.isInvisible = true

                        if(userView.singInUser(name, password)){
                            pbLoader.isVisible = false
                            ivPerfil.isVisible = true
                            navController.navigate(R.id.go_to_perfilFragment)
                        }else{
                            Toast.makeText(context, "Erro de login ou senha", Toast.LENGTH_SHORT).show()
                            pbLoader.isVisible = false
                            ivPerfil.isVisible = true
                        }

                    }
                }
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navController.navigateUp()
        }
    }

    private fun SingInFragmentBinding.checkValues(
        name: String,
        password: String
    ) : Boolean {
        var status = true
        if (name == "") {
            etName.error = "Campo vazio."
            status = false
        }

        if (password == "") {
            etSenha.error = "Campo vazio."
            status = false
        }
        return status
    }
}