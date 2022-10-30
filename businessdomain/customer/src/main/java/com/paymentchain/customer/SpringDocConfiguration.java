package com.paymentchain.customer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.List;

@Configuration
@OpenAPIDefinition(info = @Info(title = "My API Customers", version = "v1"))
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class SpringDocConfiguration {
    /*
     * Cofiguración de Spring Doc
     * */
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-apis")
                .packagesToScan("com.paymentchain.customer.controller")
                .pathsToExclude("/actuator/**")
                .build();
    }

    /*@Bean
    public GroupedOpenApi actuatorApi() {
        return GroupedOpenApi.builder()
                .group("actuators")
                .pathsToMatch("/actuators/**")
                .build();
    }*/

   /* @Bean
    public OpenAPI springShopOpenAPI() {
        OAuthFlow oAuthFlow = new OAuthFlow();
        oAuthFlow.authorizationUrl("http://localhost:8080/oauth/authorize");

        return new OpenAPI()
                .info(new Info().title("MS Customer API")
                        .description("Application for customers")
                        .version("v0.0.1")
                        .contact(new Contact().name("Gustavo Marquez").email("tavo9601@gmail.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Full Application for customers")
                        .url("https://springshop.wiki.github.org/docs"))
                .tags(List.of(new Tag().name("customers").description("Customers API")))

                // Spring Security
                .components(new Components().addSecuritySchemes("basiAuth", new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .in(SecurityScheme.In.HEADER)
                        .name("Authorization")
                        .flows(new OAuthFlows()
                                .implicit(oAuthFlow))))
                .addSecurityItem(new SecurityRequirement().addList("basiAuth"));

    }*/
}
