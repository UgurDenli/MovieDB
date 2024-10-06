package com.ugurdenli.moviedb.repository

import com.ugurdenli.moviedb.model.Movie
import com.ugurdenli.moviedb.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val apiService: ApiService) {
    suspend fun fetchPopularMovies(apiKey: String): List<Movie> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getPopularMovies(apiKey)
            response.results
        }
    }
}