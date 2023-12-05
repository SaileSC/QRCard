package com.qrcard.data.Retrofit

import android.util.Log
import com.qrcard.data.IUserApi
import com.qrcard.domain.User
import com.qrcard.domain.UserCredentials
import com.qrcard.iu.event.UserCreationListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UserRetrofit() : IUserApi {
    private var userCreationListener: UserCreationListener? = null
    fun setUserCreationListener(listener: UserCreationListener) {
        this.userCreationListener = listener
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.10:8080/consumers/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val userService = retrofit.create(IUserApi::class.java)

    override fun createUser(user: User): Call<User> {
        val call = userService.createUser(user)
        call.enqueue(object : Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    Log.e("Create User :", "Usuário salvo com sucesso")
                    userCreationListener?.onUserCreatedSuccessfully()
                } else {
                    Log.e("Create User :", "Erro ao adicionar codigo : ${response.code()}")
                    userCreationListener?.onUserCreationFailed("Usuario já cadastrado")
                }
            }

            override fun onFailure(call: Call<User>, erro: Throwable) {
                val errorMessage = "Erro na requisição: ${erro.message}"
                Log.e("Create User :", errorMessage)
                userCreationListener?.onUserCreationFailed(errorMessage)
            }
        })
        return call
    }


    override suspend fun validateUser(userCredentials: UserCredentials): Response<User> {
        return withContext(Dispatchers.IO) {
            userService.validateUser(userCredentials)
        }
    }

    override fun deleteUser(id: String): Call<Void> {
        val call = userService.deleteUser(id)
        call.enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful){
                    Log.e("Delete User :", "Usuário deletado com sucesso")
                }else{
                    Log.e("Delete User :", "Erro ao deletar,  codigo : ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                val error = "Erro na requisição: ${t.message}"
            }

        })
        return call
    }


}
