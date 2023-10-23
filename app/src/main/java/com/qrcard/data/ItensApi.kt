package com.qrcard.data

import com.qrcard.domain.Item
import retrofit2.Call
import retrofit2.http.GET

interface ItensApi {
    @GET("itens.json")
    fun getAllItens(): Call<List<Item>>
}