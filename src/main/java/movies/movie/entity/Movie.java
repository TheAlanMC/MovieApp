package movies.movie.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import movies.common.type.MovieType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("type")
    private MovieType type;
}
