package com.ugurdenli.moviedb.model

data class MovieResponse(
    val results: List<Movie> = emptyList()
)