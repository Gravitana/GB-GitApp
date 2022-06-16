package ru.gravitana.gitapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.gravitana.gitapp.R
import ru.gravitana.gitapp.domain.entities.UserEntity
import ru.gravitana.gitapp.databinding.ItemUserBinding

class UserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {
    private val binding = ItemUserBinding.bind(itemView)

    fun bind(userEntity: UserEntity, userClickListener: (UserEntity) -> Unit) {
        binding.userAvatarImageView.load(userEntity.avatarUrl)
        binding.userLoginTextView.text = userEntity.login
        binding.userIdTextView.text = userEntity.id.toString()
        binding.root.setOnClickListener { userClickListener(userEntity) }
    }
}