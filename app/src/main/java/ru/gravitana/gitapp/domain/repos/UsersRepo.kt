package ru.gravitana.gitapp.domain.repos

import ru.gravitana.gitapp.domain.entities.UserEntity

interface UsersRepo {
    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}