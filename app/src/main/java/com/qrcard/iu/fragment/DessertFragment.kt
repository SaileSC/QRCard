package com.qrcard.iu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.qrcard.R
import com.qrcard.data.ItensApi
import com.qrcard.domain.Item
import com.qrcard.iu.fragment.adapter.ItemAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DessertFragment : Fragment() {
    lateinit var listaItens : RecyclerView
    lateinit var itensApi : ItensApi

    var itensArray : ArrayList<Item> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.all_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRetrofit()
        setupView(view)
    }


    override fun onResume() {
        super.onResume()
        getAllItens()
    }



    fun setupRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://sailesc.github.io/teste-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        itensApi = retrofit.create(ItensApi::class.java)
    }

    fun getAllItens() {
        itensApi.getAllItens().enqueue(object : Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        setupList(it)
                    }
                }else{
                    Toast.makeText(context, R.string.response_erro, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                Toast.makeText(context, R.string.response_erro, Toast.LENGTH_LONG).show()            }

        })
    }


    fun setupList(lista : List<Item>){
        val itemAdapter = ItemAdapter(lista)

        listaItens.apply {
            isVisible = true
            adapter = itemAdapter
        }
    }

    fun setupView(view: View){
        view.apply {
            listaItens = findViewById(R.id.rv_all_itens)
        }
    }
}