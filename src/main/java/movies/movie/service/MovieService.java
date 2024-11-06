package movies.movie.service;

import lombok.extern.slf4j.Slf4j;
import movies.movie.entity.Movie;
import movies.movie.repository.MovieRepository;
import movies.movie.repository.MovieRepositoryImpl;
import movies.common.type.MovieType;

import java.util.List;

/**
 * @author Chris Alan Apaza Aguilar
 */

@Slf4j
public class MovieService {
    private static MovieService instance;

    private final MovieRepository movieRepository;

    private MovieService() {
        this.movieRepository = MovieRepositoryImpl.getInstance();
    }

    public static MovieService getInstance() {
        if (instance == null) {
            instance = new MovieService();
        }
        return instance;
    }

    public void addMovie(Long id, String title, MovieType type) {
        log.info("Adding movie: title={}, type={}", title, type);
        movieRepository.addMovie(new Movie(id, title, type));
        log.info("Movie added successfully: title={}, type={}", title, type);
    }

    public Movie getMovieById(Long id) {
        log.info("Getting movie by ID: id={}", id);
        Movie movie = movieRepository.getMovieById(id);
        if (movie == null) {
            log.error("Movie ID {} not found", id);
        }
        return movie;
    }

    public List<Movie> getMovies() {
        log.info("Getting all movies");
        return movieRepository.getMovies();
    }
}
