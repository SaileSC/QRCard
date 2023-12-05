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
import com.qrcard.domain.UserCredentials
import com.qrcard.iu.event.UserCreationListener

class UserViewModel(
) : ViewModel() {

    var userLogin = User("", "", "", "")
    val userRetrofit = UserRetrofit()

    fun setUserCreationListener(listener: UserCreationListener){
        userRetrofit.setUserCreationListener(listener)
    }


    fun getUser() : User {
        return userLogin
    }

    fun removeUser(){
        userLogin = User("", "", "", "")
    }

    fun singUpUser(user : User){
        userRetrofit.createUser(user)
    }

    suspend fun singInUser(name: String, password: String): Boolean {
        try {
            val response = userRetrofit.validateUser(UserCredentials(name, password))
            if (response.isSuccessful) {
                userLogin  = response.body()!!
                userLogin.name.isNotEmpty()
                return true
            } else {
                Log.e("Login error : ", response.code().toString())
            }
        } catch (e: Exception) {
            Log.e("Login error : ", e.toString())
            return false
        }
        return false
        }


    fun deleteUser() : Boolean{
        return userRetrofit.deleteUser(userLogin.id).isExecuted
    }

}


