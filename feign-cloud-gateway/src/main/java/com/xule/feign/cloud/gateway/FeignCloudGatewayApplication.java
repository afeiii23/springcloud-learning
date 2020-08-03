package com.xule.feign.cloud.gateway;

import com.xule.feign.cloud.gateway.config.HostAddrKeyResolver;
import com.xule.feign.cloud.gateway.config.UriKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringCloudApplication
@RestController
public class FeignCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignCloudGatewayApplication.class, args);
    }


    @Bean
    public HostAddrKeyResolver hostAddrKeyResolver() {
        return new HostAddrKeyResolver();
    }
    @Bean
    public UriKeyResolver uriKeyResolver() {
        return new UriKeyResolver();
    }
    @Bean
    @Primary
    KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));    }

//    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//        String httpUri = "http://httpbin.org:80/get";
//        return builder.routes()
//                .route(p -> p.path("/get")
//                .filters(f -> f.addRequestHeader("Hello","World"))
//                .uri(httpUri))
//                .route(p -> p.host("*.hystrix.com")
//                        .filters(f -> f.hystrix(config -> config.setName("mycmd").setFallbackUri("forward:/fallback")))
//                .uri(httpUri))
//                .build();
//
//    }

    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }
}
