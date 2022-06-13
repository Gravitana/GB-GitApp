package ru.gravitana.gitapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import ru.gravitana.gitapp.data.FakeUsersRepoImpl
import ru.gravitana.gitapp.data.RetrofitUsersRepoImpl
import ru.gravitana.gitapp.domain.repos.UsersRepo

class App : Application() {
    val usersRepo: UsersRepo by lazy { RetrofitUsersRepoImpl() }
//    val usersRepo: UsersRepo by lazy { FakeUsersRepoImpl() }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App