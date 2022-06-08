package ru.gravitana.gitapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import ru.gravitana.gitapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter()
    private val usersRepo: UsersRepo = FakeUsersRepoImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showProgress(false)

        binding.refreshButton.setOnClickListener {
            showProgress(true)
            usersRepo.getUsers(
                onSuccess = {
                    showProgress(false)
                    adapter.setData(it)
                },
                onError = {
                    showProgress(false)
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            )
        }

        initRecyclerView()
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