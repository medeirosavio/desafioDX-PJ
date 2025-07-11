package br.com.duxusdesafio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - DesafioDxd")
                        .version("1.0")
                        .description("Documentação da API do sistema de controle de times")
                        .contact(new Contact()
                                .name("Sávio Medeiros")
                                .email("savio.medeiros@ccc.ufcg.edu.br")
                        )
                );
    }
}
