package com.qrcard.iu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.qrcard.R
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
            tvNome.text = "Nome : " + user.name
            tvEmail.text = "Email : " + user.email

            btnExitAcc.setOnClickListener {
                UserView.removeUser()
                findNavController().navigateUp()
            }

            btnReturn.setOnClickListener {
                findNavController().navigate(R.id.go_to_mainScreen)
            }

            btnDeleteAcc.setOnClickListener {
                if(UserView.deleteUser()){
                    Toast.makeText(context, "Usuario deletado com sucesso...", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }
            }
        }


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.go_to_mainScreen)
        }
    }
}