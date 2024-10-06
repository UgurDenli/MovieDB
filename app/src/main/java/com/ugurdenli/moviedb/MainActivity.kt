package com.ugurdenli.moviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.ugurdenli.moviedb.network.ApiService
import com.ugurdenli.moviedb.ui.MovieListScreen
import com.ugurdenli.moviedb.ui.theme.MovieDBTheme
import com.ugurdenli.moviedb.viewmodel.MovieViewModel
import com.ugurdenli.moviedb.viewmodel.MovieViewModelFactory
import com.ugurdenli.moviedb.repository.MovieRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = ApiService.create()
        val repository = MovieRepository(apiService)
        val factory = MovieViewModelFactory(repository)
        val viewModel: MovieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        setContent {
            MovieDBTheme {
                viewModel.fetchPopularMovies("ee1d5e7bab063649084ba04149ea8600")
                MovieListScreen(viewModel)
            }
        }
    }
}