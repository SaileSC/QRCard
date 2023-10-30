package com.qrcard.iu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.qrcard.databinding.LoginFragmentBinding
import com.qrcard.databinding.SingUpFragmentBinding
import com.qrcard.iu.fragment.modelview.MainViewModel


class SingUpFragment : Fragment() {

    private val binding by lazy { SingUpFragmentBinding.inflate(layoutInflater) }
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
        binding.run {
            btnSingUp.setOnClickListener {
                val nome = etNome
                val email = etEmail
                Toast.makeText(context, "Usuario $nome : $email cadastrado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}