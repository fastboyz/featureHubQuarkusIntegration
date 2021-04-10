package com.demo.featurehub.startup;

import io.featurehub.client.ClientFeatureRepository;
import io.featurehub.client.Readyness;
import io.featurehub.client.StaticFeatureContext;
import io.featurehub.client.jersey.JerseyClient;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.logging.Logger;


@ApplicationScoped
public class FeatureInit {
    private static final Logger LOGGER = Logger.getLogger("FeatureInit");
    @ConfigProperty(name = "feature.hub.url.suffix")
    String featureHubUrlSuffix;
    @ConfigProperty(name = "feature.hub.url.prefix")
    String featureHubUrlPrefix;

    public void init(@Observes StartupEvent ev) {
        ClientFeatureRepository featureRepository = new ClientFeatureRepository(5);
        StaticFeatureContext.repository = featureRepository;
        new JerseyClient(featureHubUrlPrefix + featureHubUrlSuffix, true, featureRepository);

        featureRepository.addReadynessListener((ready -> {
            if (ready == Readyness.Failed) {
                LOGGER.severe("Could not contact the feature server");
                Quarkus.asyncExit();
            } else if (ready == Readyness.Ready) {
                LOGGER.info("Connected to the feature server");
            }
        }));
    }

}
