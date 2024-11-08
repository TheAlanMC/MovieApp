package movies.movie.repository;

import movies.movie.entity.Movie;

import java.util.List;

/**
 * @author Chris Alan Apaza Aguilar
 */

public interface MovieRepository {
    List<Movie> getMovies();
    Movie addMovie(Movie movie);
    Movie getMovieById(Long id);
}
