package com.assessment.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.core.Configuration;
import io.dropwizard.db.DataSourceFactory;
import jakarta.validation.constraints.NotEmpty;

public class ConferenceServiceConfiguration extends Configuration {
    private DataSourceFactory database = new DataSourceFactory();

    @NotEmpty
    private String apiKey;

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty("apiKey")
    public String getApiKey() {
        return apiKey;
    }

    @JsonProperty("apiKey")
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }
}