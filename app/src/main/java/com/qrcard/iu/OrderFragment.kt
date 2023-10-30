package com.qrcard.iu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.qrcard.R
import com.qrcard.databinding.OrderFinalizeFragmentBinding
import com.qrcard.iu.fragment.adapter.ItemBuyAdapter
import com.qrcard.iu.fragment.modelview.MainViewModel


class OrderFragment : Fragment() {

    private val binding by lazy { OrderFinalizeFragmentBinding.inflate(layoutInflater) }
    private val viewMain : MainViewModel by activityViewModels()
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
        setupList()

        viewMain.buyItemList.observe(viewLifecycleOwner) {
            loadScreen()
        }
    }


    private fun loadScreen(){
        binding.tvListValues.text = viewMain.getBuyItensCheck()
        binding.tvTotalValue.text = viewMain.getTotal()
    }
    private fun setupActions(){
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.go_to_mainScreen)
        }

        binding.ivClose.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.go_to_mainScreen)
        }

        binding.btnOrderPayment.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.go_to_paymentMethodFragment)
        }

        binding.run {
            tvListValues.setOnClickListener {
                tvListValues.maxLines = Integer.MAX_VALUE
            }

            tvListValues.setOnClickListener {
                tvListValues.maxLines = 2
            }
        }
    }
    fun setupList(){
        val lista = viewMain.getBuyItemList().toList()
        val itemBuyAdapter = ItemBuyAdapter(lista,viewMain)

        binding.rvOrderItens.apply {
            isVisible = true
            adapter = itemBuyAdapter
        }
    }
}