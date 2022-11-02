package com.paymentchain.infraestructuradomain.apigateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

// Con solo la anotacion ya se habilita la escritura del log con "log"
@Slf4j
@Component
public class GlobalPreFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("Se ejecuto el PRE filtro global");

        return chain.filter(exchange);

    }
}
