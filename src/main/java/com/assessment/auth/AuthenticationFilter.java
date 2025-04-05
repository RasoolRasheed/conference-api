package com.assessment.auth;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);
    private final String apiKey;

    public AuthenticationFilter(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String headerApiKey = requestContext.getHeaderString("X-API-Key");
        if (headerApiKey == null || !headerApiKey.equals(apiKey)) {
            LOGGER.warn("Unauthorized access attempt");
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build());
        }
    }
}
