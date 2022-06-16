package ru.gravitana.gitapp.ui.profile

import android.content.Intent
import ru.gravitana.gitapp.domain.entities.UserEntity

class ProfilePresenter(
    private val intent: Intent
) : ProfileContract.Presenter {
    private var view: ProfileContract.View? = null
    private var profile: UserEntity? = null

    override fun attach(view: ProfileContract.View) {
        this.view = view
        loadData()
        profile?.let { view.showProfile(it) }
    }

    override fun detach() {
        view = null
    }

    private fun loadData() {
        profile = UserEntity(
            intent.getStringExtra("login").toString(),
            intent.getLongExtra("id", 0).toLong(),
            intent.getStringExtra("avatar_url").toString()
        )

    }
}