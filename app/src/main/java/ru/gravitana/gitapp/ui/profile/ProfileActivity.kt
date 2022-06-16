package ru.gravitana.gitapp.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import ru.gravitana.gitapp.databinding.ActivityProfileBinding
import ru.gravitana.gitapp.domain.entities.UserEntity

class ProfileActivity : AppCompatActivity(), ProfileContract.View {
    private lateinit var binding: ActivityProfileBinding

    private lateinit var presenter: ProfileContract.Presenter

     override fun onCreate(savedInstanceState: Bundle?) {
         binding = ActivityProfileBinding.inflate(layoutInflater)
         super.onCreate(savedInstanceState)
         setContentView(binding.root)

         presenter = takePresenter()
         presenter.attach(this)
    }

    private fun takePresenter(): ProfileContract.Presenter {
        return lastCustomNonConfigurationInstance as? ProfileContract.Presenter
            ?: ProfilePresenter(intent)
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun onRetainCustomNonConfigurationInstance(): ProfileContract.Presenter {
        return presenter
    }

    override fun showProfile(profile: UserEntity) {
        binding.userLoginTextView.text = profile.login
        binding.userIdTextView.text = profile.id.toString()
        binding.userAvatarImageView.load(profile.avatarUrl)
    }
}