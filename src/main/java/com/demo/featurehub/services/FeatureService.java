package com.demo.featurehub.services;

import io.featurehub.client.ClientContext;
import io.featurehub.client.ClientFeatureRepository;
import io.featurehub.client.EdgeFeatureHubConfig;
import io.featurehub.client.FeatureHubConfig;
import io.featurehub.client.Readyness;
import io.featurehub.client.jersey.JerseyClient;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import java.util.logging.Level;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import java.time.LocalDate;
import java.util.logging.Logger;


@Startup
@ApplicationScoped
public class FeatureService {
    private static final Logger LOGGER = Logger.getLogger("FeatureService");
    @ConfigProperty(name = "feature.hub.url.suffix")
    String featureHubUrlSuffix;
    @ConfigProperty(name = "feature.hub.url.prefix")
    String featureHubUrlPrefix;

    /**
     * We need a FeatureHubConfig bean available for all sundry uses, the health check and any other incoming
     * calls. So we create it at startup and seed it into the CDI Context.
     *
     * @return FeatureHubConfig - the config ready for use.
     */
    @Startup
    @Produces
    @ApplicationScoped
    public FeatureHubConfig fhConfig() {
        final EdgeFeatureHubConfig config = new EdgeFeatureHubConfig(featureHubUrlSuffix, featureHubUrlPrefix);
        config.init();
        LOGGER.info("FeatureHub started");
        return config;
    }

    /**
     * This lets us create the ClientContext, which will always be empty, or the AuthFilter will add the user if it
     * discovers it.
     *
     * @param config - the FeatureHub Config
     * @return - a blank context usable by any resource.
     */
    @Produces
    @RequestScoped
    public ClientContext ctx(FeatureHubConfig config) {
        try {
            return config.newContext().build().get();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Cannot create context!", e);
            throw new RuntimeException(e);
        }
    }

//    private ClientFeatureRepository featureRepository;
//
//    public void init(@Observes StartupEvent ev) {
//        featureRepository = new ClientFeatureRepository(5);
////        featureRepository.clientContext().version("1.0.1").build();
//        featureRepository.clientContext().attr("MTM", LocalDate.now().toString()).build();
//        new JerseyClient(featureHubUrlPrefix + featureHubUrlSuffix, true, featureRepository);
//
//        featureRepository.addReadynessListener((ready -> {
//            if (ready == Readyness.Failed) {
//                LOGGER.severe("Could not contact the feature server");
//                Quarkus.asyncExit();
//            } else if (ready == Readyness.Ready) {
//                LOGGER.info("Connected to the feature server");
//            }
//        }));
//    }
//
//    public ClientFeatureRepository getFeatureRepository() {
//        return featureRepository;
//    }
}
