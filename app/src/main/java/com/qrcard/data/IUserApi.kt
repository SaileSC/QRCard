package com.qrcard.data

import com.qrcard.domain.User
import com.qrcard.domain.UserCredentials
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface IUserApi {
    @Headers("Content-Type: application/json")
    @POST("register")
    fun createUser(@Body user: User): Call<User>

    @POST("login")
    suspend fun validateUser(@Body userCredentials: UserCredentials): Response<User>

    @DELETE("{id}")
    fun deleteUser(@Path("id") id :String): Call<Void>
}

