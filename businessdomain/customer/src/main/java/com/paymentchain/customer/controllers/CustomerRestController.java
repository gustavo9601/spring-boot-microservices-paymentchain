package com.paymentchain.customer.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.paymentchain.customer.dtos.CustomerInDto;
import com.paymentchain.customer.dtos.CustomerOutDto;
import com.paymentchain.customer.entities.Customer;
import com.paymentchain.customer.mappers.CustomerInMapper;
import com.paymentchain.customer.mappers.CustomerOutMapper;
import com.paymentchain.customer.repositories.CustomerRepository;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Tag(name = "customers")
@SecurityRequirement(name = "basicAuth") // Usara spring doc para generar la documentación y mostrar el input del auth
@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

    @Value("${api-url.transacions}")
    private String apiUrlTransactions;

    @Value("${api-url.products}")
    private String apiUrlProducts;

    private static final Logger log = LoggerFactory.getLogger(CustomerRestController.class);


    /*
     * Mappers
     * */
    @Autowired
    private CustomerInMapper customerInMapper;
    @Autowired
    private CustomerOutMapper customerOutMapper;

    @Autowired
    private CustomerRepository customerRepository;

    // Para consumir un servicio REST
    private final WebClient.Builder webClientBuilder;

    /*
     * Configuracion del timeout de los diferentes requests
     * */
    TcpClient tcpClient = TcpClient
            .create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
            .doOnDisconnected(connection -> {
                connection.addHandlerFirst(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
                connection.addHandlerFirst(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
            });

    public CustomerRestController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Operation(summary = "Get all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the customers"),
            @ApiResponse(responseCode = "404", description = "Customers not found")
    })
    @GetMapping
    public ResponseEntity<List<CustomerOutDto>> findAll() {
        List<Customer> customers = this.customerRepository.findAll();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(this.customerOutMapper.customerListToCustomerOutDtoList(customers), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody CustomerInDto customer) {
        return new ResponseEntity<>(this.customerRepository.save(this.customerInMapper.customerInDtoToCustomer(customer)), HttpStatus.CREATED);
    }

    @GetMapping("/full/{id}")
    public ResponseEntity<Customer> getByCode(@PathVariable("id") String id) {
        Customer customer = this.customerRepository.findByCode(id);
        customer.getProducts()
                .stream()
                .forEach(p -> {
                    String productName = this.getProductName(p.getProductId());
                    log.info("Product Name: " + productName);
                    p.setProductName(productName);
                });

        List<Object> transactions = this.getTransactions(customer.getIban());
        log.info("Transactions: " + transactions);
        customer.setTransactions(transactions);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/iban/{iban}")
    public ResponseEntity<Customer> getCustomerByIban(@PathVariable String iban) {
        Optional<Customer> customerOpt = this.customerRepository.findByIban(iban);
        return customerOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private <T> List<T> getTransactions(String accountIban) {
        // Creating the headers
        WebClient client = this.webClientBuilder.clientConnector(new ReactorClientHttpConnector(HttpClient.from(this.tcpClient)))
                .baseUrl(this.apiUrlTransactions)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", this.apiUrlTransactions))
                .build();

        // Creating the request y retrieving the response
        List<Object> block = client.method(HttpMethod.GET)
                .uri(uriBuilder -> {
                    return uriBuilder
                            .path("transactions")
                            .pathSegment(accountIban)
                            .build();
                })
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();

        // Parsing the response
        List<T> transacctions = (List<T>) block;
        return transacctions;
    }

    private String getProductName(Long id) {
        // Creating the headers
        WebClient client = this.webClientBuilder.clientConnector(new ReactorClientHttpConnector(HttpClient.from(this.tcpClient)))
                .baseUrl(this.apiUrlProducts + "products")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", this.apiUrlProducts + "products"))
                .build();

        // Creating the request y retrieving the response
        JsonNode block = client.method(HttpMethod.GET)
                .uri("/" + id)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        // Parsing the response
        String name = block.get("name").asText();
        return name;
    }


}
