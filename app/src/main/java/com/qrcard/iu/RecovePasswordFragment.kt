package com.qrcard.iu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.qrcard.databinding.RecoverPasswordFragmentBinding


class RecovePasswordFragment : Fragment() {

    private val binding by lazy { RecoverPasswordFragmentBinding.inflate(layoutInflater) }

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
        binding.apply {
            btnRecover.setOnClickListener {
                val email = etEmail.text.toString()

                if(!email.contains("@") || email == ""){
                    tilEmail.error = "Email invalido"
                }else{
                    Toast.makeText(context, "link de recuperação enviado para $email", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}