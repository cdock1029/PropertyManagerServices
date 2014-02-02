package com.idaeo.dropwizard;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.idaeo.dropwizard.core.DynamoManaged;
import com.idaeo.dropwizard.health.DynamoDBHealthCheck;
import com.idaeo.dropwizard.resources.BuildingResource;
import com.idaeo.dropwizard.resources.TenantResource;
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
        String awsAccesKey = serviceConfiguration.getAwsAccessKey();
        String awsSecretKey = serviceConfiguration.getAwsSecretKey();
        String dynamoHost = serviceConfiguration.getDynamoHost();
        String dynamoPort = serviceConfiguration.getDynamoPort();

        AWSCredentials credentials = new BasicAWSCredentials(awsAccesKey, awsSecretKey);

        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(credentials);
        dynamoDBClient.setEndpoint("http://" + dynamoHost + ":" + dynamoPort);
        DynamoManaged dynamoManaged = new DynamoManaged(dynamoDBClient);
        environment.manage(dynamoManaged);
        environment.addHealthCheck(new DynamoDBHealthCheck(dynamoDBClient));

        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(dynamoDBClient);
        environment.addResource(new TenantResource(dynamoDBMapper));
        environment.addResource(new BuildingResource(dynamoDBMapper));

        FilterBuilder filterConfig = environment.addFilter(CrossOriginFilter.class, "/*");
        filterConfig.setInitParam(CrossOriginFilter.PREFLIGHT_MAX_AGE_PARAM, "http://localhost:8080"); // 1 day
    }
}
