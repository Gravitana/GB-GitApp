package ru.gravitana.gitapp.ui.users

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import ru.gravitana.gitapp.app
import ru.gravitana.gitapp.domain.entities.UserEntity
import ru.gravitana.gitapp.domain.repos.UsersRepo
import ru.gravitana.gitapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter()
    private val usersRepo: UsersRepo by lazy { app.usersRepo }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.refreshButton.setOnClickListener {
            loadData()
        }

        initRecyclerView()
        showProgress(false)
    }

    private fun loadData() {
        showProgress(true)
        usersRepo.getUsers(
            onSuccess = {
                showProgress(false)
                onDataLoaded(it)
            },
            onError = {
                showProgress(false)
                onError(it)
            }
        )
    }

    private fun onDataLoaded(data: List<UserEntity>) {
        adapter.setData(data)
    }

    private fun onError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun initRecyclerView() {
        binding.usersRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.usersRecyclerview.adapter = adapter
    }

    private fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.usersRecyclerview.isVisible = !inProgress
    }
}