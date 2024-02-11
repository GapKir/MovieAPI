package com.example.movieapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapi.adapter.MovieAdapter
import com.example.movieapi.databinding.ActivityMainBinding
import com.example.movieapi.network.RetrofitClient
import com.example.movieapi.network.response.dto.PopularResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()

        binding.btnGetPopularFilms.setOnClickListener {
            getPopularFilms()
        }
    }

    private fun initAdapter() {
        adapter = MovieAdapter()
        val layoutManager = LinearLayoutManager(this)
        binding.apply {
            recycler.layoutManager = layoutManager
            recycler.adapter = adapter
        }
    }

    private fun getPopularFilms() {
        binding.btnGetPopularFilms.visibility = View.GONE
        binding.progressBer.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.IO) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                val request = RetrofitClient.movieApi.getPopularFilms()
                delay(2000)

                if (request.isSuccessful) {
                    showSuccessResult(request)
                } else {
                    showError()
                }
            }
        }
    }


    private suspend fun showSuccessResult(response: Response<PopularResponse>) {
        withContext(Dispatchers.Main) {
            response.body()?.results?.let {
                adapter.movies = it.map { result ->
                    result.toMovie()
                }
            }
            binding.progressBer.visibility = View.GONE
        }
    }

    private suspend fun showError() {
        withContext(Dispatchers.Main) {
            binding.progressBer.visibility = View.GONE
            Toast.makeText(this@MainActivity, getString(R.string.error), Toast.LENGTH_SHORT).show()
            binding.btnGetPopularFilms.apply {
                text = getString(R.string.try_again)
                visibility = View.VISIBLE
            }
        }
    }
}
