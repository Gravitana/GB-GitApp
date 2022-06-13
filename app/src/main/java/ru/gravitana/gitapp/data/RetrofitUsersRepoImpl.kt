package ru.gravitana.gitapp.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.gravitana.gitapp.data.UserDto
import ru.gravitana.gitapp.domain.entities.UserEntity
import ru.gravitana.gitapp.domain.repos.UsersRepo
import ru.gravitana.gitapp.utils.convertDtoToUserEntity

class RetrofitUsersRepoImpl : UsersRepo {
    private val apiInterface = GithubInterface.create().getUsers()

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        apiInterface.enqueue(object : Callback<List<UserDto>>{
            override fun onResponse(
                call: Call<List<UserDto>>,
                response: Response<List<UserDto>>
            ) {
                response.body()?.let { element -> onSuccess(element.map { convertDtoToUserEntity(it) }) }
            }

            override fun onFailure(call: Call<List<UserDto>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}