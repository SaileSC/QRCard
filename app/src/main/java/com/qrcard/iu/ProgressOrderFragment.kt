package com.qrcard.iu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.qrcard.databinding.ProgressOrderFragmentBinding
import com.qrcard.iu.fragment.modelview.BuyItensViewModel
import com.qrcard.iu.fragment.modelview.RestaurantViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ProgressOrderFragment : Fragment() {

    private val binding by lazy { ProgressOrderFragmentBinding.inflate(layoutInflater) }

    private val buyItensView : BuyItensViewModel by activityViewModels()
    private val restaurantView : RestaurantViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadScreen()
        setupListener()
    }

    private fun setupListener() {
        val navController = findNavController()
        binding.apply {
            ivClose.setOnClickListener {
                navController.navigateUp()
            }

            btnNewOrder.setOnClickListener{
                navController.navigateUp()
            }

        }
    }

    private fun loadScreen(){
        binding.apply {
            tvOrderId.text = "1"

            tvListValues.text = buyItensView.getBuyItensCheckNames()
            tvTotalValue.text = buyItensView.getTotal()


            tvTableValue.text = restaurantView.restaurantTable()
        }

        lifecycleScope.launch {
            progressExample()
        }
    }

    private suspend fun progressExample() {
        binding.apply {
            ivProgressOrder.progress = 0
            tvState.text = "Aguardando Pedido ser Aceito!"
        }

        delay(3000)
        binding.apply {
            ivProgressOrder.progress = 25
            tvState.text = "Pedido Aceito!"
        }

        delay(3000)
        binding.apply {
            ivProgressOrder.progress = 50
            tvState.text = "Em Preparação!"
        }

        delay(3000)
        binding.apply {
            ivProgressOrder.progress = 75
            tvState.text = "Pedido Preparado!"
        }

        delay(3000)
        binding.apply {
            ivProgressOrder.progress = 100
            tvState.text = "A caminho da mesa!"
        }

        buyItensView.orderOff()
        buyItensView.resetBuyList()
    }
}