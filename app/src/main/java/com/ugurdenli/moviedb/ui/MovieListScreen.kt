package com.ugurdenli.moviedb.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ugurdenli.moviedb.model.Movie
import com.ugurdenli.moviedb.viewmodel.MovieViewModel

@Composable
fun MovieListScreen(viewModel: MovieViewModel, onMovieClick: (Movie) -> Unit) {
    val movies = viewModel.movies.collectAsState().value

    if (movies.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "No movies available", style = MaterialTheme.typography.bodyLarge)
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            items(movies) { movie ->
                MovieItem(movie) {
                    onMovieClick(movie)
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            val imageUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 16.dp)
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = movie.overview, style = MaterialTheme.typography.bodyMedium, maxLines = 3)
            }
        }
    }
}