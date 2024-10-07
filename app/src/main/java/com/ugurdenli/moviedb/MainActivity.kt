package com.ugurdenli.moviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.ugurdenli.moviedb.model.Movie
import com.ugurdenli.moviedb.network.ApiService
import com.ugurdenli.moviedb.ui.MovieListScreen
import com.ugurdenli.moviedb.ui.theme.MovieDBTheme
import com.ugurdenli.moviedb.viewmodel.MovieViewModel
import com.ugurdenli.moviedb.viewmodel.MovieViewModelFactory
import com.ugurdenli.moviedb.repository.MovieRepository
import com.ugurdenli.moviedb.ui.MovieDetailScreen
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = ApiService.create()
        val repository = MovieRepository(apiService)
        val factory = MovieViewModelFactory(repository)
        val viewModel: MovieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        setContent {
            MovieDBTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "movieList") {
                    composable("movieList") {
                        MovieListScreen(viewModel) { movie ->
                            val movieJson = URLEncoder.encode(Gson().toJson(movie), StandardCharsets.UTF_8.toString())
                            navController.navigate("movieDetail/$movieJson")
                        }
                    }
                    composable(
                        "movieDetail/{movieJson}",
                        arguments = listOf(navArgument("movieJson") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val movieJson = backStackEntry.arguments?.getString("movieJson")
                        val decodedMovieJson = URLDecoder.decode(movieJson, StandardCharsets.UTF_8.toString())
                        val movie = Gson().fromJson(decodedMovieJson, Movie::class.java)
                        MovieDetailScreen(navController, movie)
                    }
                }
            }
        }
    }
}