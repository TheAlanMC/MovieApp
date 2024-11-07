package movies.common.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import movies.common.exception.JsonException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris Alan Apaza Aguilar
 */

@Slf4j
public class JsonRepositoryImpl<T> implements JsonRepository<T> {
    private final ObjectMapper objectMapper;
    private final String filePath;
    private final TypeReference<List<T>> typeReference;

    public JsonRepositoryImpl(String filePath, TypeReference<List<T>> typeReference) {
        this.filePath = filePath;
        this.typeReference = typeReference;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public List<T> loadAll() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, typeReference);
        } catch (Exception e) {
            log.error("Error loading data from JSON file", e);
            throw new JsonException("Error loading data from JSON file");
        }
    }

    @Override
    public void saveAll(List<T> entities) {
        try {
            objectMapper.writeValue(new File(filePath), entities);
        } catch (Exception e) {
            log.error("Error saving data to JSON file", e);
            throw new JsonException("Error saving data to JSON file");
        }
    }
}
