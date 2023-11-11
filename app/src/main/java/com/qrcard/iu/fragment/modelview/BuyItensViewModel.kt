package com.qrcard.iu.fragment.modelview

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.qrcard.data.FavoriteItens
import com.qrcard.domain.BuyItem
import com.qrcard.domain.Item
import com.qrcard.domain.User

class BuyItensViewModel(
) : ViewModel() {
    private val _buyItemList = MutableLiveData<List<BuyItem>>()
    val buyItemList : LiveData<List<BuyItem>> = _buyItemList

    fun setItemBuy(context: Context, item: BuyItem) {
        val buyItens = _buyItemList.value.orEmpty().toMutableList()


        if (buyItens.none { it.id == item.id }) {
            buyItens.add(item)
            _buyItemList.value = buyItens
            Toast.makeText(context, "Item adicionado ao carrinho...", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Item já existe, altere a quantidade no carrinho...", Toast.LENGTH_SHORT).show()
        }
    }


    fun getBuyItemList() : List<BuyItem>{
        return _buyItemList.value.orEmpty().toMutableList()
    }

    fun getBuyItensCheck() : String {
        val buyItens = _buyItemList.value.orEmpty().toMutableList()
        var string =""

        for(item in buyItens){
           string += "${item.quantidade}x R$ %.2f\n".format((item.quantidade * item.preco.replace(",", ".").toFloat()))
        }

        return string
    }

    fun getTotal() : String {
        val buyItens = _buyItemList.value.orEmpty().toMutableList()
        var total = 0.00

        for(item in buyItens){
            total += item.preco.replace(",", ".").toFloat() * item.quantidade
        }

        return "R$ $total"
    }


    fun increment(itemId : Int){
        val buyItens = _buyItemList.value.orEmpty().toMutableList()
        buyItens.find { it.id.toInt() == itemId }?.increment()
        _buyItemList.value = buyItens
    }


    fun decrement(itemId : Int){
        val buyItens = _buyItemList.value.orEmpty().toMutableList()
        buyItens.find { it.id.toInt() == itemId }?.decrement()
        _buyItemList.value = buyItens
    }
    fun deleteBuyItem(itemId : Int){
        val buyItens = _buyItemList.value.orEmpty().toMutableList()
        buyItens.remove(
            buyItens.find { it.id.toInt() == itemId }
        )
        _buyItemList.value = buyItens
    }

    fun resetBuyList(){
        val buyItens = mutableListOf<BuyItem>()
        _buyItemList.value = buyItens
    }
}
