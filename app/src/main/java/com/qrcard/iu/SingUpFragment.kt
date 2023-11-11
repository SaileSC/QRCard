package com.qrcard.iu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.qrcard.databinding.SingUpFragmentBinding
import com.qrcard.domain.User
import com.qrcard.iu.fragment.modelview.UserViewModel


class SingUpFragment : Fragment() {

    private val binding by lazy { SingUpFragmentBinding.inflate(layoutInflater) }

    private val userView: UserViewModel by activityViewModels()

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

        binding.run {
            btnSingUp.setOnClickListener {
                val user = User(
                    etNome.text.toString(),
                    etEmail.text.toString(),
                    etEmail.text.toString(),
                    etSenha.text.toString(),
                )

                if(checkUser(user)){
                    userView.singUpUser(user)
                    Toast.makeText(context, "Usuario inserido com sucesso...", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }
            }
        }
    }


    private fun checkUser(user : User) : Boolean{
        var stateOk = true

        binding.apply {
            if(user.nome == ""){
                etNome.error = "nome é obrigatorio"
                stateOk = false
            }

            if(!user.email.contains("@") || user.email == ""){
                etEmail.error = "email valido é obrigatorio"
                stateOk = false
            }

            if(user.senha.length < 6){
                tilSenha.error = "senha obrigatorio de 6 digitos"
                stateOk = false
            }else{
                tilSenha.error = null
            }
        }
        return stateOk
    }
}