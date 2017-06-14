package no.nextgentel.dropwizard.http.cors.filter;

import com.google.common.base.Joiner;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CorsFilter implements ContainerResponseFilter {

    private final List<String> allowedOrigins;
    private final List<String> allowedHeaders;

    public CorsFilter(List<String> allowedOrigins, List<String> allowedHeaders) {
        this.allowedOrigins = allowedOrigins;
        this.allowedHeaders = allowedHeaders;
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        final String requestOrigin = Optional.ofNullable(requestContext.getHeaderString("Origin")).orElse("na");

        if (allowedOrigins.contains(requestOrigin)) {
            responseContext.getHeaders().put("Access-Control-Allow-Origin", Collections.singletonList(requestOrigin));
        } else {
            responseContext.getHeaders().put("Access-Control-Allow-Origin", Collections.singletonList(allowedOrigins.get(0)));
        }

        responseContext.getHeaders().put("Access-Control-Allow-Credentials", Collections.singletonList("true"));
        responseContext.getHeaders().put("Access-Control-Allow-Headers", Collections.singletonList(Joiner.on(", ").skipNulls().join(allowedHeaders)));

        responseContext.getHeaders().put("Access-Control-Allow-Methods", Collections.singletonList("GET, POST, OPTIONS"));
    }
}
