package com.qrcard.iu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.qrcard.R
import com.qrcard.databinding.OrderFinalizeFragmentBinding
import com.qrcard.iu.fragment.adapter.ItemBuyAdapter
import com.qrcard.iu.fragment.modelview.BuyItensViewModel


class OrderFragment : Fragment() {

    private val binding by lazy { OrderFinalizeFragmentBinding.inflate(layoutInflater) }
    private val buyItens : BuyItensViewModel by activityViewModels()
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

        buyItens.buyItemList.observe(viewLifecycleOwner) {
            loadScreen()
        }
    }


    private fun loadScreen(){
        binding.apply {
            tvListValues.text = buyItens.getBuyItensCheck()
            tvTotalValue.text = buyItens.getTotal()
        }
    }
    private fun setupActions(){
        val navController = findNavController()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navController.navigateUp()
        }

        binding.ivClose.setOnClickListener {
            navController.navigateUp()
        }

        binding.btnOrderPayment.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.go_to_paymentMethodFragment)
        }

        var isExpanded = false
        binding.tvListValues.setOnClickListener {
            if (isExpanded) {
                binding.tvListValues.maxLines = 2
            } else {
                binding.tvListValues.maxLines = Integer.MAX_VALUE
            }
            isExpanded = !isExpanded
        }
    }
    fun setupList(){
        val lista = buyItens.getBuyItemList().toList()
        val itemBuyAdapter = ItemBuyAdapter(lista,buyItens)

        binding.rvOrderItens.apply {
            isVisible = true
            adapter = itemBuyAdapter
        }
    }
}