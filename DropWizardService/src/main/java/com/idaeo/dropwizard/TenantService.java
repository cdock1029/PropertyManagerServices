package com.idaeo.dropwizard;

import com.idaeo.dropwizard.health.TemplateHealthCheck;
import com.idaeo.dropwizard.resources.TenantsResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.config.FilterBuilder;
import org.eclipse.jetty.servlets.CrossOriginFilter;

/**
 *
 */
public class TenantService extends Service<ServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new TenantService().run(args);
    }

    @Override
    public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {
        bootstrap.setName("tenant-service");
    }

    @Override
    public void run(ServiceConfiguration serviceConfiguration, Environment environment) throws Exception {
        final String template = serviceConfiguration.getTemplate();
        final String defaultName = serviceConfiguration.getDefaultName();
        environment.addResource(new TenantsResource(template, defaultName));
        environment.addHealthCheck(new TemplateHealthCheck(template));
        FilterBuilder filterConfig = environment.addFilter(CrossOriginFilter.class, "/*");
        filterConfig.setInitParam(CrossOriginFilter.PREFLIGHT_MAX_AGE_PARAM, "http://localhost:8080"); // 1 day
    }
}
