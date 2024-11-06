package movies.customer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;
}