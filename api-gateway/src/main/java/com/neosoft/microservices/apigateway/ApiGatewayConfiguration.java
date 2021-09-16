package com.neosoft.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/get")
						.filters(f -> f.addRequestHeader("MyHeader", "MyURI").addRequestParameter("Param", "MyValue"))
						.uri("http://httpbin.org:80"))
				.route(p -> p.path("/currency-exchange/**").uri("lb://currency-exchange"))
				.route(p -> p.path("/temperature-exchange/**").uri("lb://temperature-exchange"))
				.route(p -> p.path("/currency-conversion/**").uri("lb://currency-conversion"))
				.route(p -> p.path("/temperature-conversion/**").uri("lb://temperature-conversion"))
				.route(p -> p.path("/currency-conversion-feign/**").uri("lb://currency-conversion")) // lb-load balancer
				.route(p -> p.path("/temperature-conversion-feign/**").uri("lb://temperature-conversion"))
				.route(p -> p.path("/currency-conversion-new/**")
						.filters(f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)",
								"/currency-conversion-feign/${segment}"))
						.uri("lb://currency-conversion"))
				.route(p -> p.path("/temperature-conversion-new/**")
						.filters(f -> f.rewritePath("/temperature-conversion-new/(?<segment>.*)",
								"/temperature-conversion-feign/${segment}"))
						.uri("lb://temperature-conversion"))
				.build();
	}
}