package com.idaeo.dropwizard.data;

import com.idaeo.dropwizard.api.model.Building;
import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collection;
import java.util.List;

/**
 * @author conordockry on 2/23/14
 */
public class BuildingDAOImpl extends AbstractDAO<Building> implements BuildingDAO {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public BuildingDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Building findById(Long id) {
        return get(id);
    }

    @Override
    public long create(Building building) {
        return persist(building).getId();
    }

    @Override
    public void batchCreate(Collection<Building> buildings) { //TODO but not REALLY batch..
        int i = 0;
        for (Building building: buildings) {
            persist(building);
            i++;
            if (i % 3 == 0) {
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
    }

    @Override
    public List<Building> findAll() {
        String hql = "FROM Building";
        Query query = currentSession().createQuery(hql);
        return list(query);
    }

    @Override
    public void delete(Building building) {
        currentSession().delete(building);
    }

    @Override
    public void deleteById(Long id) {
        Query q = currentSession().createQuery("DELETE from Building where id = :id");
        q.setParameter("id", id);
        q.executeUpdate();
    }

    @Override
    public Session getCurrentSession() {
        return currentSession();
    }
}
