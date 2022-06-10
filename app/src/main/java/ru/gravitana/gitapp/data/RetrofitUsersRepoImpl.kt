package ru.gravitana.gitapp.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.gravitana.gitapp.domain.entities.UserEntity
import ru.gravitana.gitapp.domain.repos.UsersRepo

class RetrofitUsersRepoImpl : UsersRepo {
    private val apiInterface = GithubInterface.create().getUsers()

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        apiInterface.enqueue(object : Callback<List<UserEntity>>{
            override fun onResponse(
                call: Call<List<UserEntity>>,
                response: Response<List<UserEntity>>
            ) {
                response.body()?.let { onSuccess(it) }
            }

            override fun onFailure(call: Call<List<UserEntity>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}