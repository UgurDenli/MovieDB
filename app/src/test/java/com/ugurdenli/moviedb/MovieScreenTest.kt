package com.ugurdenli.moviedb

import com.ugurdenli.moviedb.model.Movie
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MovieTest {
// Given a Movie object
    // When the object is created

    @Test
    fun createMovieObject() {
        val movie = Movie(
            id = 1,
            title = "Test Movie",
            overview = "This is a test movie.",
            posterPath = "/test.jpg"
        )
// Then the object should have the correct properties
        assertEquals(1, movie.id)
        assertEquals("Test Movie", movie.title)
        assertEquals("This is a test movie.", movie.overview)
        assertEquals("/test.jpg", movie.posterPath)
    }
}