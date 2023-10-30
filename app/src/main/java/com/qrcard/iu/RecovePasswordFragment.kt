package com.qrcard.iu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.qrcard.databinding.LoginFragmentBinding
import com.qrcard.databinding.RecoverPasswordFragmentBinding
import com.qrcard.iu.fragment.modelview.MainViewModel


class RecovePasswordFragment : Fragment() {

    private val binding by lazy { RecoverPasswordFragmentBinding.inflate(layoutInflater) }
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
        val email = binding.etEmail.text

        binding.btnRecover.setOnClickListener {
            Toast.makeText(context, "link de recuperação enviado para $email", Toast.LENGTH_SHORT).show()
        }
    }
}