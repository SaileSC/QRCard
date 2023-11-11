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

class FavoriteItensViewModel(
) : ViewModel() {
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
