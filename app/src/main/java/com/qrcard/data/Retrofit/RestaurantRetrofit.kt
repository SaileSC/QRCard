package com.qrcard.data.Retrofit

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.view.isGone
import com.qrcard.R
import com.qrcard.data.ItensApi
import com.qrcard.domain.Item
import com.qrcard.domain.Restaurant
import com.qrcard.iu.fragment.modelview.ItensViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class RestaurantRetrofit(
) {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://sailesc.github.io/teste-api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var itensApi = retrofit.create(ItensApi::class.java)

    suspend fun getRestaurant(): Restaurant = suspendCoroutine { continuation ->
        itensApi.getRestaurant().enqueue(object : Callback<Restaurant>
        {
            override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>) {
                if (response.isSuccessful) {
                    response.body()?.let {result->
                        continuation.resume(result)
                    }
                } else {
                    continuation.resumeWithException(Exception("Response error"))
                }
            }

            override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                continuation.resumeWithException(t)
            }
        })
    }

    suspend fun getAllItens(): List<Item> = suspendCoroutine { continuation ->
        itensApi.getAllItens().enqueue(object : Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                if (response.isSuccessful) {
                    response.body()?.let {result->
                        continuation.resume(result)
                    }
                } else {
                    continuation.resumeWithException(Exception("Response error"))
                }
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                continuation.resumeWithException(t)
            }
        })
    }

}