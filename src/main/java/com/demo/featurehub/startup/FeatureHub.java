package com.demo.featurehub.startup;

import io.featurehub.client.ClientContext;
import io.featurehub.client.FeatureRepositoryContext;
import io.featurehub.client.EdgeService;

public interface FeatureHub {
    ClientContext newContext();
}
