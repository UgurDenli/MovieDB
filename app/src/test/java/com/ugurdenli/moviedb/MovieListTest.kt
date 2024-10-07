package com.ugurdenli.moviedb

import com.ugurdenli.moviedb.model.Movie
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieListTest {

    @Test
    fun createMovieList() {
        val movie1 = Movie(
            id = 1,
            title = "Test Movie 1",
            overview = "This is the first test movie.",
            posterPath = "/test1.jpg"
        )
        val movie2 = Movie(
            id = 2,
            title = "Test Movie 2",
            overview = "This is the second test movie.",
            posterPath = "/test2.jpg"
        )
        val movieList = listOf(movie1, movie2)

        assertEquals(2, movieList.size)
        assertEquals("Test Movie 1", movieList[0].title)
        assertEquals("Test Movie 2", movieList[1].title)
    }
}