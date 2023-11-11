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
import com.qrcard.databinding.SucessOrderFragmentBinding
import com.qrcard.iu.fragment.modelview.BuyItensViewModel


class SucessOrderFragment : Fragment() {

    private val binding by lazy { SucessOrderFragmentBinding.inflate(layoutInflater) }
    private val buyItensView : BuyItensViewModel by activityViewModels()


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

    private fun setupActions(){
        buyItensView.resetBuyList()
        val navController = findNavController()
        binding.ivClose.setOnClickListener{
            navController.navigate(R.id.go_to_mainScreen)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navController.navigate(R.id.go_to_mainScreen)
        }
    }
}