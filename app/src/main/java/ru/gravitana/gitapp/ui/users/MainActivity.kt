package ru.gravitana.gitapp.ui.users

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import ru.gravitana.gitapp.app
import ru.gravitana.gitapp.domain.entities.UserEntity
import ru.gravitana.gitapp.databinding.ActivityMainBinding
import ru.gravitana.gitapp.ui.profile.ProfileActivity

class MainActivity : AppCompatActivity(), UsersContract.View {
    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter { userEntity: UserEntity -> userItemClicked(userEntity) }

    private lateinit var profileIntent: Intent

    private lateinit var presenter: UsersContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()

        presenter = takePresenter()
        presenter.attach(this)

        profileIntent = Intent(this, ProfileActivity::class.java)
    }

    private fun takePresenter(): UsersContract.Presenter {
        return lastCustomNonConfigurationInstance as? UsersContract.Presenter
            ?: UsersPresenter(app.usersRepo)
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun onRetainCustomNonConfigurationInstance(): UsersContract.Presenter {
        return presenter
    }

    private fun initViews() {
        binding.refreshButton.setOnClickListener {
            presenter.onRefresh()
        }

        initRecyclerView()
        showProgress(false)
    }

    override fun showUsers(users: List<UserEntity>) {
        adapter.setData(users)
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.usersRecyclerview.isVisible = !inProgress
    }

    private fun initRecyclerView() {
        binding.usersRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.usersRecyclerview.adapter = adapter
    }

    private fun userItemClicked(userEntity: UserEntity) {
//        Toast.makeText(this, "Clicked on: ${userEntity.login}", Toast.LENGTH_LONG).show()
        startActivity(profileIntent)
    }
}