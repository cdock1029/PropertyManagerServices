package com.idaeo.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class ServiceConfiguration extends Configuration {

    @NotEmpty
    @JsonProperty
    private String dynamoHost = "localhost";

    @NotEmpty
    @JsonProperty
    private String dynamoPort = "8000";

    @NotEmpty
    @JsonProperty
    private String awsAccessKey = "akey";

    @NotEmpty
    @JsonProperty
    private String awsSecretKey = "skey";


    public String getDynamoHost() {
        return dynamoHost;
    }

    public void setDynamoHost(String dynamoHost) {
        this.dynamoHost = dynamoHost;
    }

    public String getDynamoPort() {
        return dynamoPort;
    }

    public void setDynamoPort(String dynamoPort) {
        this.dynamoPort = dynamoPort;
    }

    public String getAwsAccessKey() {
        return awsAccessKey;
    }

    public void setAwsAccessKey(String awsAccesKey) {
        this.awsAccessKey = awsAccesKey;
    }

    public String getAwsSecretKey() {
        return awsSecretKey;
    }

    public void setAwsSecretKey(String awsSecretKey) {
        this.awsSecretKey = awsSecretKey;
    }
}
