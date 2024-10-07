package com.ugurdenli.moviedb.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ugurdenli.moviedb.model.Movie

@Composable
fun MovieDetailScreen(navController: NavController, movie: Movie) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        val imageUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = movie.title, style = MaterialTheme.typography.titleLarge)
        Text(text = movie.overview, style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Back")
        }
    }
}