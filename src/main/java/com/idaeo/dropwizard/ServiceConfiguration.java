package com.idaeo.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
public class ServiceConfiguration extends Configuration {

    @NotBlank
    @JsonProperty
    @Getter @Setter
    private String dynamoHost = "localhost";

    @NotBlank
    @JsonProperty
    @Getter @Setter
    private String dynamoPort = "8000";

    @NotBlank
    @JsonProperty
    @Getter @Setter
    private String awsAccessKey = "akey";

    @NotBlank
    @JsonProperty
    @Getter @Setter
    private String awsSecretKey = "skey";

    @Valid
    @NotNull
    @JsonProperty
    @Getter @Setter
    private DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration();
}
