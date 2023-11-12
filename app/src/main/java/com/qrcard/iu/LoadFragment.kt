package com.qrcard.iu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.qrcard.R
import com.qrcard.data.ItensApi
import com.qrcard.databinding.LoadFragmentBinding
import com.qrcard.domain.Item
import com.qrcard.iu.fragment.modelview.ItensViewModel
import com.qrcard.iu.fragment.modelview.RestaurantViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoadFragment : Fragment() {

    private val binding by lazy { LoadFragmentBinding.inflate(layoutInflater) }

    private val ListItensView : ItensViewModel by activityViewModels()
    private val RestaurantView : RestaurantViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ListItensView.setListItens()

        lifecycleScope.launch {
            netxScreen()
        }
    }

    private suspend fun netxScreen() {
        delay(1000)
        val navController = findNavController()
        //navController.navigate(R.id.go_to_mainScreen)
       navController.navigate(R.id.go_to_readQRCodeFragment)
    }
}