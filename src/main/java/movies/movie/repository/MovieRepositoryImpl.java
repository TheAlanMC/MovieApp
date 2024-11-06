package movies.movie.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import movies.movie.Movie;
import movies.movie.exception.MovieException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris Alan Apaza Aguilar
 */

@Slf4j
public class MovieRepositoryImpl implements MovieRepository {
    private static final String MOVIES_FILE = "src/main/resources/movies.json";
    private final List<Movie> movies = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Movie> getMovies() {
        List<Movie> currentMovies = loadMoviesFromJson();
        movies.addAll(currentMovies);
        return currentMovies;
    }

    @Override
    public void addMovie(Movie movie) {
        movies.add(movie);
        saveMovieToJson(movie);
    }

    @Override
    public Movie getMovieById(Long id) {
        return movies.stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private List<Movie> loadMoviesFromJson() {
        try {
            if (!new File(MOVIES_FILE).exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(new File(MOVIES_FILE), new TypeReference<>() {});
        } catch (IOException e) {
            log.error("Error loading movies from JSON file", e);
            throw new MovieException("Error loading movies from JSON file");
        }
    }

    private void saveMovieToJson(Movie movie) {
        try {
            List<Movie> currentMovies = loadMoviesFromJson();
            if (currentMovies.stream().anyMatch(c -> c.getId().equals(movie.getId()))) {
                log.error("Movie id already exists");
                throw new MovieException("Movie id already exists");
            }
            currentMovies.add(movie);
            objectMapper.writeValue(new File(MOVIES_FILE), currentMovies);
        } catch (IOException e) {
            log.error("Error saving movie to JSON file", e);
            throw new MovieException("Error saving movie to JSON file");
        }
    }
}
