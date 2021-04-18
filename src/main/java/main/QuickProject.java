package main;

import lombok.extern.log4j.Log4j2;
import main.mapping.InventoryPersistor;
import main.repository.CategoryRepository;
import main.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@Log4j2
@SpringBootApplication
public class QuickProject {

    @Autowired
    private InventoryPersistor inventoryPersistor;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(QuickProject.class);
    }

    @Bean
    ApplicationRunner applicationRunner() throws IOException {
        return args -> {
            inventoryPersistor.persist();
            log.info(categoryRepository.findAll());
            log.info(productRepository.findAll());
        };
    }

}
