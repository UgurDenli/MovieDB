package com.ugurdenli.moviedb.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.ugurdenli.moviedb.model.Movie
import com.ugurdenli.moviedb.viewmodel.MovieViewModel

@Composable
fun MovieListScreen(viewModel: MovieViewModel) {
    val movies = viewModel.movies.collectAsState().value
    Log.d("MovieListScreen", "Movies: $movies")

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(movies.size) { index ->
            val movie = movies[index]
            MovieItem(movie) {
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movie.posterPath}"),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = movie.title, style = MaterialTheme.typography.bodyLarge)
        Text(text = movie.overview, style = MaterialTheme.typography.bodyLarge)
    }
}