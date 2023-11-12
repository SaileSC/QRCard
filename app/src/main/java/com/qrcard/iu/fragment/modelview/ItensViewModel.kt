package com.qrcard.iu.fragment.modelview


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.qrcard.data.Retrofit.RestaurantRetrofit
import com.qrcard.domain.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ItensViewModel(
    private val initialList: List<Item> = emptyList(),
) : ViewModel() {
    //itemList
    private var itemList: MutableLiveData<List<Item>> = MutableLiveData()
    val restaurant = RestaurantRetrofit()


    fun setListItens() {
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScope.launch {
            try {
                val items = restaurant.getAllItens()
                itemList.postValue(items)
            } catch (e: Exception) {
                Log.e("response Itens : ", e.toString())
            }
        }
    }

    fun getListItens() : List<Item>{
        return itemList.value.orEmpty().toMutableList()
    }

    fun getItemListLiveData(): LiveData<List<Item>> {
        return itemList
    }

    fun updateAdapters(){
        itemList.value = itemList.value
    }
}
