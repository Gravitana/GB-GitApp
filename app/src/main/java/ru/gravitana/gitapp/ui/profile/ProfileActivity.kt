package ru.gravitana.gitapp.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import ru.gravitana.gitapp.databinding.ActivityProfileBinding
import ru.gravitana.gitapp.domain.entities.UserEntity

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

     override fun onCreate(savedInstanceState: Bundle?) {
         binding = ActivityProfileBinding.inflate(layoutInflater)
         super.onCreate(savedInstanceState)
         setContentView(binding.root)

         val userEntity = UserEntity(
             intent.getStringExtra("login").toString(),
             intent.getLongExtra("id", 0).toLong(),
             intent.getStringExtra("avatar_url").toString()
         )

         binding.userLoginTextView.text = userEntity.login
         binding.userIdTextView.text = userEntity.id.toString()
         binding.userAvatarImageView.load(userEntity.avatarUrl)
    }
}