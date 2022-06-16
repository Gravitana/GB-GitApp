package ru.gravitana.gitapp.ui.profile

import ru.gravitana.gitapp.domain.entities.UserEntity

interface ProfileContract {

    interface View {
        fun showProfile(profile: UserEntity)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
    }
}