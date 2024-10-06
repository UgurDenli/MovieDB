package com.ugurdenli.moviedb.network

import com.ugurdenli.moviedb.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/latest")
    suspend fun getLatestMovies(@Query("api_key") apiKey: String): List<Movie>
}