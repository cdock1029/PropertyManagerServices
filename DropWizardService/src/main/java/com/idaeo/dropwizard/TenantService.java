package com.idaeo.dropwizard;

import com.idaeo.dropwizard.health.TemplateHealthCheck;
import com.idaeo.dropwizard.resources.TenantResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

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
        environment.addResource(new TenantResource(template, defaultName));
        environment.addHealthCheck(new TemplateHealthCheck(template));
    }
}
