package com.hse.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
  @Bean
  public RestTemplate template() {
    return new RestTemplate();
  }

  @Bean
  public RouteLocator routeLocator(RouteLocatorBuilder builder) {
    return builder
        .routes()
        .route(
            r ->
                r.path("/skill-service/v3/api-docs")
                    .and()
                    .method(HttpMethod.GET)
                    .uri("lb://skill-service"))
        .route(
            r ->
                r.path("/auth-service/v3/api-docs")
                    .and()
                    .method(HttpMethod.GET)
                    .uri("lb://auth-service"))
        .build();
  }
}
