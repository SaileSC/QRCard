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

    lateinit var itensApi : ItensApi

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRetrofit()
        getAllItens()
        lifecycleScope.launch {
            netxScreen()
        }
    }

//    override fun onResume() {
//        super.onResume()
//        setupRetrofit()
//        getAllItens()
//        lifecycleScope.launch {
//            netxScreen()
//        }
//    }

    private suspend fun netxScreen() {
        delay(1000)
        val navController = findNavController()
        navController.navigate(R.id.go_to_mainScreen)
    }

    fun setupRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://sailesc.github.io/teste-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        itensApi = retrofit.create(ItensApi::class.java)
    }

    private fun getAllItens() {
        itensApi.getAllItens().enqueue(object : Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                if(response.isSuccessful){
                    binding.pbLoader.isGone = true
                    response.body()?.let {
                        ListItensView.setListItens(it)
                    }
                }else{
                    Toast.makeText(context, R.string.response_erro, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                Toast.makeText(context, R.string.response_erro, Toast.LENGTH_LONG).show()
            }

        })
    }
}