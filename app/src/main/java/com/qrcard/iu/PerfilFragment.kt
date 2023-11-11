package com.qrcard.iu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.qrcard.databinding.PerfilFragmentBinding
import com.qrcard.iu.fragment.modelview.UserViewModel


class PerfilFragment : Fragment() {

    private val binding by lazy { PerfilFragmentBinding.inflate(layoutInflater) }
    private val UserView : UserViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = UserView.getUser()
        binding.apply {
            tvNome.text = user.nome
            tvEmail.text = user.email
        }
    }
}