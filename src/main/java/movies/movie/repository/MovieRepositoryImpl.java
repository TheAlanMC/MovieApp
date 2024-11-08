package movies.movie.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import movies.common.repository.JsonRepositoryImpl;
import movies.movie.entity.Movie;
import movies.movie.exception.MovieException;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Chris Alan Apaza Aguilar
 */

@Slf4j
public class MovieRepositoryImpl implements MovieRepository {
    private static MovieRepositoryImpl instance;
    private static final String MOVIES_FILE = "src/main/resources/movies.json";

    private final List<Movie> movies;
    private final JsonRepositoryImpl<Movie> jsonRepository;
    private final AtomicLong nextId;

    private MovieRepositoryImpl(JsonRepositoryImpl<Movie> jsonRepository) {
        this.jsonRepository = jsonRepository;
        this.movies = jsonRepository.loadAll();
        this.nextId = new AtomicLong(movies.stream().mapToLong(Movie::getId).max().orElse(0) + 1);
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
    public Movie addMovie(Movie movie) {
        movie.setId(nextId.getAndIncrement());
        movies.add(movie);
        jsonRepository.saveAll(movies);
        return movie;
    }

    @Override
    public Movie getMovieById(Long id) {
        return movies.stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new MovieException("Movie not found"));
    }
}
