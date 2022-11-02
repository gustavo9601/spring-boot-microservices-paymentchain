package com.paymentchain.infraestructuradomain.apigateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class GlobalPostFilter {

  /*  @Bean
    public GlobalFilter globalPostFilter() {
        return (exchange, chain) -> {
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        log.info("Se ejecuto el POST filtro global");
                    }));
        };
    }*/


}
