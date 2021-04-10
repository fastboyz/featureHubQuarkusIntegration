package com.demo.featurehub;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class ExampleResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return String.valueOf(Features.FEATURE_CALCULATE_GOAL_AGGREGATE.isActive());
    }
}