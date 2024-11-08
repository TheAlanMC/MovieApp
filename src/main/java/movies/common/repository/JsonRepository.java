package movies.common.repository;

import java.util.List;

/**
 * @author Chris Alan Apaza Aguilar
 */

public interface JsonRepository<T> {
    List<T> loadAll();
    void saveAll(List<T> entities);
}
