package com.qrcard.iu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.qrcard.R
import com.qrcard.data.ItensApi
import com.qrcard.databinding.LoadFragmentBinding
import com.qrcard.databinding.OrderFinalizeFragmentBinding
import com.qrcard.databinding.PaymentMethodFragmentBinding
import com.qrcard.domain.Item
import com.qrcard.iu.fragment.adapter.ItemBuyAdapter
import com.qrcard.iu.fragment.modelview.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PaymentMethodFragment : Fragment() {

    private val binding by lazy { PaymentMethodFragmentBinding.inflate(layoutInflater) }
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