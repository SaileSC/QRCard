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

class ActionsViewModel(
) : ViewModel() {
    //navigation para o adapter (para resolver)
    private var navController: NavController? = null
    fun setNav(navController: NavController){
        this.navController = navController
    }
    fun getNav() : NavController?{
        return this.navController
    }

    //string de busca
    val searchString = MutableLiveData<String>()
}
