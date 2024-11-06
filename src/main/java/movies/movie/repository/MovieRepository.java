package movies.movie.repository;

import movies.movie.Movie;

import java.util.List;

/**
 * @author Chris Alan Apaza Aguilar
 */

public interface MovieRepository {
    List<Movie> getMovies();
    void addMovie(Movie movie);
    Movie getMovieById(Long id);
}
