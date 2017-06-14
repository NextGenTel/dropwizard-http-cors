package no.nextgentel.dropwizard.http.cors;


import io.dropwizard.setup.Environment;
import no.nextgentel.dropwizard.http.cors.filter.CorsFilter;
import no.nextgentel.dropwizard.http.cors.filter.CorsFilterFeature;

import java.util.List;

public class HttpCorsSupport {
    private HttpCorsSupport() {
    }

    public static void register(Environment environment, List<String> allowedOrigins, List<String> allowedHeaders) {
        CorsFilter corsFilter = new CorsFilter(allowedOrigins, allowedHeaders);
        CorsFilterFeature component = new CorsFilterFeature(corsFilter);
        environment.jersey().register(component);
    }

    public static void register(Environment environment, HttpCorsConfiguration httpCorsConfiguration) {
        register(environment, httpCorsConfiguration.getAllowedOrigins(), httpCorsConfiguration.getAllowedHeaders());
    }
}
