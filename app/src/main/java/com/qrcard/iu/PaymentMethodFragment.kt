package com.qrcard.iu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.qrcard.R
import com.qrcard.databinding.PaymentMethodFragmentBinding


class PaymentMethodFragment : Fragment() {

    private val binding by lazy { PaymentMethodFragmentBinding.inflate(layoutInflater) }


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
        val navController = findNavController()

        binding.apply {
            btnAttableMethod.setOnClickListener {
                navController.navigate(R.id.go_to_sucessOrderFragment)
            }

            btnCreditMethod.setOnClickListener {
                navController.navigate(R.id.go_to_sucessOrderFragment)
            }

            btnDebitMethod.setOnClickListener {
                navController.navigate(R.id.go_to_sucessOrderFragment)
            }

            btnPixMethod.setOnClickListener {
                navController.navigate(R.id.go_to_sucessOrderFragment)
            }

            ivClose.setOnClickListener {
                navController.navigateUp()
            }
        }

    }
}