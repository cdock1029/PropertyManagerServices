package com.idaeo.dropwizard.health;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yammer.dropwizard.json.ObjectMapperFactory;
import com.yammer.metrics.core.HealthCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cdock
 * Date: 2/1/14
 * Time: 1:35 PM
 */
public class DynamoDBHealthCheck extends HealthCheck {

    private static final Logger LOG = LoggerFactory.getLogger(DynamoDBHealthCheck.class);

    private AmazonDynamoDBClient client;

    public DynamoDBHealthCheck(AmazonDynamoDBClient client) {
        super("DynamoDBHealthCheck");
        this.client = client;
    }

    @Override
    protected Result check() throws Exception {
        ListTablesResult listTablesResult = client.listTables();
        List<String> outputStringList = new ArrayList<>();
        ObjectMapperFactory objectMapperFactory = new ObjectMapperFactory();
        ObjectMapper objectMapper = objectMapperFactory.build(new JsonFactory());
        for (String table : listTablesResult.getTableNames()) {
            DescribeTableResult describeTableResult = client.describeTable(table);
            //String flatJson = describeTableResult.getTable().toString();
            //Object obj = objectMapper.readValue(flatJson, Object.class);
            String description = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(describeTableResult.getTable());
            outputStringList.add("\n************************\n\t" + table + "\n************************\n");
            outputStringList.add(description + "\n\n\n");
        }
        return Result.healthy("\n\tTables:\n" + Arrays.toString(outputStringList.toArray()) + "\n");
    }
}
