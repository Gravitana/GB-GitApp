package ru.gravitana.gitapp.data

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.gravitana.gitapp.domain.entities.UserEntity

interface GithubInterface {

    @GET("users")
    fun getUsers() : Call<List<UserDto>>

    companion object {

        var BASE_URL = "https://api.github.com/"

        fun create() : GithubInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(GithubInterface::class.java)

        }
    }
}