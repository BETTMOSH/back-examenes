package es.roberto.gestionexamenesbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * @Author Roberto Carlos Ledezma
 */
@ConfigurationPropertiesScan
@SpringBootApplication
public class GestionExamenesBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(GestionExamenesBackendApplication.class, args);
    }

}
