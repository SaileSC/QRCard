package com.qrcard.data

import com.qrcard.domain.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {
    @Headers("Content-Type: application/json")
    @POST("/user")
    fun createUser(@Body user: User): Call<Void>

    @POST("user/validarLogin/{login}/{password}")
    fun validarLogin(
        @Path("login") login: String,
        @Path("password") password: String
    ): Call<User>
}
