package com.qrcard.iu.fragment.modelview

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.qrcard.data.FavoriteItens
import com.qrcard.data.Retrofit.UserRetrofit
import com.qrcard.domain.BuyItem
import com.qrcard.domain.Item
import com.qrcard.domain.User

class UserViewModel(
) : ViewModel() {

    var userLogin = User("", "", "", "")
    val userRetrofit = UserRetrofit()

    fun getUser() : User {
        return userLogin
    }

    fun removeUser(){
        userLogin = User("", "", "", "")
    }

    fun singUpUser(user : User){
        userRetrofit.postUser(user)
    }

    suspend fun singInUser(email: String, senha: String): Boolean {
        return try {
            userLogin = userRetrofit.validateUser(email, senha)
            userLogin.email.isNotEmpty()
            true
        } catch (e: Exception) {
            false
        }
    }

}
