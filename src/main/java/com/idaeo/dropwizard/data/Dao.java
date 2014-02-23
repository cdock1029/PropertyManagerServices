package com.idaeo.dropwizard.data;

import org.hibernate.Session;

import java.util.Collection;
import java.util.List;

/**
 * @author conordockry on 2/23/14
 */
public interface Dao<T> {
    T findById(Long id);

    long create(T entity);

    void batchCreate(Collection<T> entities);

    List<T> findAll();

    void delete(T entity);

    void deleteById(Long id);

    Session getCurrentSession();
}
