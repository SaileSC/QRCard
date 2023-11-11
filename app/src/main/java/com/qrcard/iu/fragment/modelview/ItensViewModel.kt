package com.qrcard.iu.fragment.modelview


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.qrcard.domain.Item


class ItensViewModel(
    private val initialList: List<Item> = emptyList(),
) : ViewModel() {
    //itemList
    private var itemList: MutableLiveData<List<Item>> = MutableLiveData()

    fun setListItens(items: List<Item>) {
        itemList.value = items
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
