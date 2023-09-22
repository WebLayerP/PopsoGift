package it.popso.popsogift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;



@SpringBootApplication
@EnableAutoConfiguration
public class PopsogiftApplication {

    public static void main(String[] args) {
        SpringApplication.run(PopsogiftApplication.class, args);
    }
}
