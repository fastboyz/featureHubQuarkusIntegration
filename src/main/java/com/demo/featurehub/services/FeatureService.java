package com.demo.featurehub.services;

import io.featurehub.client.ClientFeatureRepository;
import io.featurehub.client.Readyness;
import io.featurehub.client.jersey.JerseyClient;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.time.LocalDate;
import java.util.logging.Logger;


@ApplicationScoped
public class FeatureService {
    private static final Logger LOGGER = Logger.getLogger("FeatureService");
    @ConfigProperty(name = "feature.hub.url.suffix")
    String featureHubUrlSuffix;
    @ConfigProperty(name = "feature.hub.url.prefix")
    String featureHubUrlPrefix;

    private ClientFeatureRepository featureRepository;

    public void init(@Observes StartupEvent ev) {
        featureRepository = new ClientFeatureRepository(5);
//        featureRepository.clientContext().version("1.0.1").build();
        featureRepository.clientContext().attr("MTM", LocalDate.now().toString()).build();
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

    public ClientFeatureRepository getFeatureRepository() {
        return featureRepository;
    }
}
