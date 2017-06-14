package no.nextgentel.dropwizard.http.cors;

import java.util.List;

public interface HttpCorsConfiguration {
    List<String> getAllowedOrigins();
    List<String> getAllowedHeaders();
}
