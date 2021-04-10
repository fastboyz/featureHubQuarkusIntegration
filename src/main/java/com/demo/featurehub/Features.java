package com.demo.featurehub;

import io.featurehub.client.Feature;
import io.featurehub.client.StaticFeatureContext;

public enum Features  implements Feature {
    FEATURE_CALCULATE_GOAL_AGGREGATE;

    private Features() {
    }

    public boolean isActive() {
        return StaticFeatureContext.getInstance().isActive(this);
    }
}
