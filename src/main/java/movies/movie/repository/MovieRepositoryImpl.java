package movies.movie.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import movies.common.repository.JsonRepositoryImpl;
import movies.movie.entity.Movie;
import movies.movie.exception.MovieException;

import java.util.List;

/**
 * @author Chris Alan Apaza Aguilar
 */

@Slf4j
public class MovieRepositoryImpl implements MovieRepository {
    private static MovieRepositoryImpl instance;
    private static final String MOVIES_FILE = "src/main/resources/movies.json";

    private final List<Movie> movies;
    private final JsonRepositoryImpl<Movie> jsonRepository;

    private MovieRepositoryImpl(JsonRepositoryImpl<Movie> jsonRepository) {
        this.jsonRepository = jsonRepository;
        this.movies = jsonRepository.loadAll();
    }

    public static MovieRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new MovieRepositoryImpl(new JsonRepositoryImpl<>(MOVIES_FILE, new TypeReference<>() {}));
        }
        return instance;
    }

    public static MovieRepositoryImpl getInstance(JsonRepositoryImpl<Movie> jsonRepository) {
        if (instance == null) {
            instance = new MovieRepositoryImpl(jsonRepository);
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }

    @Override
    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public void addMovie(Movie movie) {
        movies.add(movie);
        jsonRepository.saveAll(movies);
    }

    @Override
    public Movie getMovieById(Long id) {
        return movies.stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new MovieException("Movie not found"));
    }
}
