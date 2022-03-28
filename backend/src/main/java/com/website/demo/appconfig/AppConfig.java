package com.website.demo.appconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "app")
@Data
public class AppConfig {

    @NotNull
    private String url;


}
