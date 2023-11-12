package com.qrcard.data.Retrofit

import android.util.Log
import androidx.fragment.app.activityViewModels
import com.qrcard.data.UserApi
import com.qrcard.domain.User
import com.qrcard.iu.fragment.modelview.BuyItensViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UserRetrofit(
) {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://teste-web6584.onrender.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private val userService = retrofit.create(UserApi::class.java)

    fun postUser(user: User) {
        userService.createUser(user).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.e("Login User :", "Usuário salvo com sucesso")
                } else {
                    Log.e("Login User :", "Ocorreu um erro ao adicionar ${response.code()}\"")
                }
            }

            override fun onFailure(call: Call<Void>, erro: Throwable) {
                Log.e("Login User :", "Erro na requisição: ${erro.message}")
            }
        })
    }


    suspend fun validateUser(login: String, password: String): User {
        return withContext(Dispatchers.IO) {
            val response = userService.validarLogin(login, password).execute()

            if (response.isSuccessful) {
                response.body() ?: throw RuntimeException("Corpo da resposta nulo")
            } else {
                throw RuntimeException("Erro ao validar login. Código: ${response.code()}")
            }
        }

    }
}