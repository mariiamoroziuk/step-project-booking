package airport.dao;

import airport.entity.AbstractEntity;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public abstract class AbstractAirportDao<T extends AbstractEntity> implements IAirportDao<T> {
    protected List<T> list;

    public AbstractAirportDao(List<T> entities) {
        this.list = entities;
    }

    @Override
    public T save(T entity) {
        if (entity.getId() == null) {
            entity.setId(new Date().getTime());
            list.add(entity);
        } else {
            int index = list.indexOf(entity);
            list.set(index, entity);
        }

        return entity;
    }

    @Override
    public List<T> saveAll(List<T> entities) {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).setId(new Date().getTime()+i);
        }
        list.addAll(entities);

        return entities;
    }

    @Override
    public boolean delete(T entity) {
        return list.remove(entity);
    }

    @Override
    public void deleteAll(List<T> entities) {
        list.clear();
    }

    @Override
    public List<T> findAll() {
        return Collections.unmodifiableList(list);
    }

    @Override
    public boolean deleteById(long id) {
        return list.removeIf(el -> el.getId() == id);
    }

    @Override
    public T getOne(long id) {
        return list.stream()
                .filter(el -> el.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public long count() {
        return list.size();
    }
}

