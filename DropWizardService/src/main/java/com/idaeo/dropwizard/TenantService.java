package com.idaeo.dropwizard;

import com.idaeo.dropwizard.resources.HelloWorldResource;
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
    public void initialize(Bootstrap<ServiceConfiguration> serviceConfigurationBootstrap) {

    }

    @Override
    public void run(ServiceConfiguration serviceConfiguration, Environment environment) throws Exception {
        environment.addResource(new HelloWorldResource());
    }
}
