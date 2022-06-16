package ru.gravitana.gitapp.utils

import ru.gravitana.gitapp.data.UserDto
import ru.gravitana.gitapp.domain.entities.UserEntity

fun convertDtoToUserEntity(userDto: UserDto): UserEntity {
    return UserEntity(
        userDto.login!!,
        userDto.id!!,
        userDto.avatar_url!!
    )
}