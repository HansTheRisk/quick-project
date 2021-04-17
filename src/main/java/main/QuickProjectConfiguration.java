package main;

import main.mapping.InventoryJsonLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties(InventoryProperties.class)
public class QuickProjectConfiguration {

    @Autowired
    private InventoryProperties inventoryProperties;

    @Bean
    public InventoryJsonLoader inventoryJsonLoader() throws IOException {
        return new InventoryJsonLoader(new ClassPathResource(inventoryProperties.getFileName()).getFile());
    }

}
