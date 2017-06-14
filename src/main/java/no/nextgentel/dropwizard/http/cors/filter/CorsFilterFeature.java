package no.nextgentel.dropwizard.http.cors.filter;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

public class CorsFilterFeature implements DynamicFeature {

    private final CorsFilter corsFilter;

    public CorsFilterFeature(CorsFilter corsFilter) {
        this.corsFilter = corsFilter;
    }

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        context.register(corsFilter);
    }
}
