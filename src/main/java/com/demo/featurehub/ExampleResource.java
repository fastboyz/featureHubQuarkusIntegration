package com.demo.featurehub;

import io.featurehub.client.ClientContext;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

@Path("/hello")
public class ExampleResource {
    private static final Logger LOGGER = Logger.getLogger("FeatureInit");

    private final Provider<ClientContext> contextProvider;

    @Inject
    public ExampleResource(Provider<ClientContext> contextProvider) {
        this.contextProvider = contextProvider;
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        LOGGER.info(String.valueOf(contextProvider.get().feature(Features.FEATURE_CALCULATE_GOAL_AGGREGATE).isEnabled()));
        return "Hi";
    }
}