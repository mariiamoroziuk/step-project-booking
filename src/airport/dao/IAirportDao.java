package airport.dao;

import java.util.List;

public interface IAirportDao<T> {
    T save(T entity);
    boolean delete(T entity);
    void deleteAll(List<T> entities);
    List<T> saveAll(List<T> entities);
    List<T> findAll();
    boolean deleteById(long id);
    T getOne(long id);
    long count();
}
