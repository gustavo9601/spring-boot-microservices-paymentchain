package com.paymentchain.businessdomain.transactions.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;

public class ExceptionFeignClientErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    private static final Logger log = LoggerFactory.getLogger(ExceptionFeignClientErrorDecoder.class);


    @Override
    public Exception decode(String methodKey, Response response) {
        Object message = null;

        if (response.body() != null) {
            try (InputStream bodyIs = response.body().asInputStream()) {
                ObjectMapper mapper = new ObjectMapper();
                message = mapper.readValue(bodyIs, Object.class);
            } catch (IOException e) {
                return new Exception(e.getMessage());
            }
        }


        log.info("Mensaje error:\t{}", message);

        switch (response.status()) {
            case 400:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Algun campo mal enviado === ");
            case 404:
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontrado === ");
            default:
                return errorDecoder.decode(methodKey, response);
        }
    }
}
