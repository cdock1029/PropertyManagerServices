package com.idaeo.dropwizard;

import com.idaeo.dropwizard.api.model.Building;
import com.idaeo.dropwizard.api.model.wrapper.BuildingListWrapper;
import com.idaeo.dropwizard.api.model.Tenant;
import com.idaeo.dropwizard.data.BuildingDAOImpl;
import com.idaeo.dropwizard.data.TenantDAOImpl;
import com.idaeo.dropwizard.resources.BuildingResource;
import com.idaeo.dropwizard.resources.TenantResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.config.FilterBuilder;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import org.eclipse.jetty.servlets.CrossOriginFilter;

/**
 *
 */
public class TenantManagementService extends Service<ServiceConfiguration> {
    private final HibernateBundle<ServiceConfiguration> hibernateBundle = new HibernateBundle<ServiceConfiguration>
            (Tenant.class, Building.class, BuildingListWrapper.class) {
        @Override
        public DatabaseConfiguration getDatabaseConfiguration(ServiceConfiguration serviceConfiguration) {
            return serviceConfiguration.getDatabaseConfiguration();
        }
    };

    public static void main(String[] args) throws Exception {
        new TenantManagementService().run(args);
    }

    @Override
    public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {
        bootstrap.setName("tenant-service");
        bootstrap.addBundle(hibernateBundle);
        //bootstrap.getObjectMapperFactory().enable(SerializationFeature.WRAP_ROOT_VALUE);
        //bootstrap.getObjectMapperFactory().enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
    }

    @Override
    public void run(ServiceConfiguration serviceConfiguration, Environment environment) throws Exception {
        environment.addResource(new TenantResource(new TenantDAOImpl(hibernateBundle.getSessionFactory())));
        environment.addResource(new BuildingResource(new BuildingDAOImpl(hibernateBundle.getSessionFactory())));

        FilterBuilder filterConfig = environment.addFilter(CrossOriginFilter.class, "/*");
        filterConfig.setInitParam(CrossOriginFilter.PREFLIGHT_MAX_AGE_PARAM, String.valueOf(60*60*24)); // 1 day
    }
}
