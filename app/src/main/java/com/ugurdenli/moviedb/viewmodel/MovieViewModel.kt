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

    fun fetchPopularMovies(apiKey: String) {
        viewModelScope.launch {
            try {
                val movieList = repository.fetchPopularMovies(apiKey)
                Log.d("MovieViewModel", "Fetched movies: $movieList")
                _movies.value = movieList
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error fetching movies", e)
            }
        }
    }
}