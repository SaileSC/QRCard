package com.qrcard.data

import com.qrcard.domain.Item
import com.qrcard.domain.Restaurant
import retrofit2.Call
import retrofit2.http.GET

interface ItensApi {
    @GET("itens.json")
    fun getAllItens(): Call<List<Item>>

    @GET("restaurant.json")
    fun getRestaurant(): Call<Restaurant>
}