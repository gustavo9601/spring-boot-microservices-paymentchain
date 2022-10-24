package com.paymentchain.businessdomain.products.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
@RefreshScope
public class PropertiesConfig {

    @Value("${current.environment.title:valor_quemado}")
    private String environmentTitle;

    public String getEnvironmentTitle() {
        return environmentTitle;
    }

    public void setEnvironmentTitle(String environmentTitle) {
        this.environmentTitle = environmentTitle;
    }

}
