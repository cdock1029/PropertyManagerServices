package com.idaeo.dropwizard.core;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.yammer.dropwizard.lifecycle.Managed;

/**
 * @author cdock
 * Date: 2/1/14
 * Time: 1:56 PM
 */
public class DynamoManaged implements Managed {

    private AmazonDynamoDBClient client;

    public DynamoManaged(AmazonDynamoDBClient client) {
        this.client = client;
    }

    @Override
    public void start() throws Exception {
    }

    @Override
    public void stop() throws Exception {
        client.shutdown();
    }
}
