package movies.movie.service;

import movies.common.type.MovieType;
import movies.movie.entity.Movie;
import movies.movie.exception.MovieException;
import movies.movie.repository.MovieRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Chris Alan Apaza Aguilar
 */

class MovieServiceTest {

    @Mock
    private MovieRepositoryImpl movieRepository;

    private MovieService movieService;

    private List<Movie> mockMovies;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Arrange
        mockMovies = new ArrayList<>();
        mockMovies.add(new Movie(1L, "Movie 1", MovieType.REGULAR));
        MovieService.resetInstance();
        movieService = MovieService.getInstance(movieRepository);
    }

    @Test
    public void getMoviesShouldReturnMovies() {
        // Arrange
        when(movieRepository.getMovies()).thenReturn(mockMovies);
        // Act
        List<Movie> movies = movieService.getMovies();
        // Assert
        assertEquals(1, movies.size());
        assertEquals(1L, movies.get(0).getId());
        assertEquals("Movie 1", movies.get(0).getTitle());
        assertEquals(MovieType.REGULAR, movies.get(0).getType());
    }

    @Test
    void addMovieShouldAddMovie() {
        // Arrange
        Movie movie = new Movie(2L, "Movie 2", MovieType.NEW_RELEASE);
        when(movieRepository.addMovie(any(Movie.class))).thenReturn(movie);
        // Act
        Movie addedMovie = movieService.addMovie("Movie 2", MovieType.NEW_RELEASE);
        // Assert
        assertEquals(2, addedMovie.getId());
        assertEquals("Movie 2", addedMovie.getTitle());
        assertEquals(MovieType.NEW_RELEASE, addedMovie.getType());
    }

    @Test
    void getMovieByIdShouldReturnMovie() {
        // Arrange
        when(movieRepository.getMovieById(1L)).thenReturn(mockMovies.get(0));
        // Act
        Movie movie = movieService.getMovieById(1L);
        // Assert
        assertEquals(1L, movie.getId());
        assertEquals("Movie 1", movie.getTitle());
        assertEquals(MovieType.REGULAR, movie.getType());
    }

    @Test
    void getMovieByIdShouldThrowMovieExceptionWhenMovieIsNull() {
        // Arrange
        when(movieRepository.getMovieById(1L)).thenReturn(null);
        // Act and Assert
        MovieException movieException = assertThrows(MovieException.class, () -> movieService.getMovieById(1L));
        assertEquals("Movie not found", movieException.getMessage());
    }
}