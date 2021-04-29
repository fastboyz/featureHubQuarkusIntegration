package com.demo.featurehub;

import com.demo.featurehub.services.FeatureService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.logging.Logger;

@Path("/hello")
public class ExampleResource {
    private static final Logger LOGGER = Logger.getLogger("FeatureInit");

    @Inject
    FeatureService featureService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {

        LOGGER.info(featureService.getFeatureRepository().getFeatureState("TEST_STRING").getString());
        return featureService.getFeatureRepository().getFeatureState("TEST_STRING").getString();
    }
}