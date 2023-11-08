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

class MainViewModel(
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


    //navigation para o adapter (para resolver)
    private var navController: NavController? = null
    fun setNav(navController: NavController){
        this.navController = navController
    }
    fun getNav() : NavController?{
        return this.navController
    }


    //listBuy
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


    // user
    val user =  User(
        nome = "",
        email = ""
    )

    fun setPerfil(nome : String, email : String){
        user.nome = nome
        user.email = email
    }

    fun getPerfil() : User{
        return user
    }


    //string de busca
    val searchString = MutableLiveData<String>()

    //lista de ids favoritados
    private val _itensFavoritos =  FavoriteItens()

    val itensFavoritos : LiveData<FavoriteItens> = MutableLiveData(_itensFavoritos)

    fun observerFavoriteList() : LiveData<FavoriteItens>  {
        return itensFavoritos
    }

    fun getFavoriteList() : FavoriteItens  {
        return itensFavoritos.value ?: FavoriteItens()
    }
}
