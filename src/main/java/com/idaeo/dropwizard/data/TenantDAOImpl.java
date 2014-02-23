package com.idaeo.dropwizard.data;

import com.idaeo.dropwizard.api.model.Tenant;
import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collection;
import java.util.List;

/**
 * @author conordockry on 2/22/14
 */
public class TenantDAOImpl extends AbstractDAO<Tenant> implements TenantDAO {

    public TenantDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Tenant findById(Long id) {
        return get(id);
    }

    @Override
    public long create(Tenant tenant) {
        return persist(tenant).getId();
    }

    @Override
    public void batchCreate(Collection<Tenant> tenants) {
        int i = 0;
        for (Tenant tenant : tenants) {
            persist(tenant);
            i++;
            if (i % 3 == 0) {
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
    }

    @Override
    public List<Tenant> findAll() {
        String hql = "FROM Tenant";
        Query query = currentSession().createQuery(hql);
        return list(query);
    }

    @Override
    public void delete(Tenant tenant) {
        currentSession().delete(tenant);
    }

    @Override
    public void deleteById(Long id) {
        Query q = currentSession().createQuery("DELETE from Tenant where id = :id");
        q.setParameter("id", id);
        q.executeUpdate();
    }

    @Override
    public Session getCurrentSession() {
        return currentSession();
    }
}
