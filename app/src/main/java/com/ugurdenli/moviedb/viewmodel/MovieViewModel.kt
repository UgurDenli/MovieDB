package com.ugurdenli.moviedb.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurdenli.moviedb.model.Movie
import com.ugurdenli.moviedb.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> get() = _movies

    init {
        fetchPopularMovies("ee1d5e7bab063649084ba04149ea8600")
    }

    private fun fetchPopularMovies(apiKey: String) {
        viewModelScope.launch {
            try {
                val movieList = repository.fetchPopularMovies(apiKey)
                _movies.value = movieList
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error fetching movies", e)
            }
        }
    }
}