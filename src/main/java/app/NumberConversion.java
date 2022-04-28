package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = "entity")
@EnableJpaRepositories(basePackages = "repository")
@ComponentScan(basePackages = {"service", "controller", "exception"})
public class NumberConversion {
    public static void main(String[] args) {
        SpringApplication.run(NumberConversion.class, args);
    }
}