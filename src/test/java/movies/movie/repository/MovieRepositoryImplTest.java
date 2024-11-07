package movies.movie.repository;

import movies.common.repository.JsonRepositoryImpl;
import movies.common.type.MovieType;
import movies.movie.entity.Movie;
import movies.movie.exception.MovieException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author Chris Alan Apaza Aguilar
 */

class MovieRepositoryImplTest {

    @Mock
    private JsonRepositoryImpl<Movie> jsonRepository;

    private MovieRepositoryImpl movieRepository;

    private List<Movie> mockMovies;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Arrange
        mockMovies = new ArrayList<>();
        mockMovies.add(new Movie(1L, "Movie 1", MovieType.REGULAR));
        when(jsonRepository.loadAll()).thenReturn(mockMovies);
        MovieRepositoryImpl.resetInstance();
        movieRepository = MovieRepositoryImpl.getInstance(jsonRepository);
    }

    @Test
    void getMoviesShouldReturnMovies() {
        // Act
        List<Movie> movies = movieRepository.getMovies();
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
        // Act
        movieRepository.addMovie(movie);
        // Assert
        assertEquals(2, mockMovies.size());
        assertEquals(2L, mockMovies.get(1).getId());
        assertEquals("Movie 2", mockMovies.get(1).getTitle());
        assertEquals(MovieType.NEW_RELEASE, mockMovies.get(1).getType());
    }

    @Test
    void getMovieByIdShouldReturnMovie() {
        // Act
        Movie movie = movieRepository.getMovieById(1L);
        // Assert
        assertEquals(1L, movie.getId());
        assertEquals("Movie 1", movie.getTitle());
        assertEquals(MovieType.REGULAR, movie.getType());
    }

    @Test
    void getMovieByIdShouldThrowMovieException() {
        // Act
        MovieException movieException = assertThrows(MovieException.class, () -> movieRepository.getMovieById(2L));
        // Assert
        assertEquals("Movie not found", movieException.getMessage());
    }
}