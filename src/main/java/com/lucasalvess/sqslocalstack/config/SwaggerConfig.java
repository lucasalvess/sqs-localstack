package com.lucasalvess.sqslocalstack.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public Info info(){
        return new Info()
                .title("SQS localstack example")
                .description("API - Producer and consumer for SQS ");
    }

    @Bean
    public Components components(){
        return new Components();
    }

    @Bean
    public OpenAPI customOpenAPI(@Autowired Info info, @Autowired Components components) {
        return new OpenAPI()
                .components(components)
                .info(info);
    }
}
