package main.mapping;

import lombok.extern.log4j.Log4j2;
import main.domain.Category;
import main.domain.Product;
import main.repository.CategoryRepository;
import main.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Log4j2
public class InventoryPersistor {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private InventoryLoader inventoryLoader;

    public void persist() {
        if(categoryRepository.count() == 0) {
            try {
                processCategory(inventoryLoader.readInventory());
            } catch (IOException e) {
                log.error("Could not read inventory from file: ", e);
            }
        }
    }

    private void processCategory(CategoryMapping categoryMapping) {
        process(0, new StringBuilder(), categoryMapping);
    }

    private void process(int parentId, StringBuilder pathBuilder, CategoryMapping categoryMapping) {
        int id = 0;
        if(!categoryMapping.isRoot()) {
            pathBuilder.append("#").append(categoryMapping.getName());
            id = categoryRepository.save(new Category(parentId, categoryMapping.getName()))
                                   .getId();
            for(ProductMapping productMapping : categoryMapping.getProducts()) {
                processProduct(pathBuilder.toString(), productMapping);
            }
        }
        for(CategoryMapping child : categoryMapping.getCategories()) {
            process(id, new StringBuilder(pathBuilder.toString()), child);
        }
    }

    private void processProduct(String path, ProductMapping productMapping) {
        try {
           productRepository.save(new Product(productMapping.getName(), productMapping.getPrice(), path));
        } catch (Exception e) {
            // Revert if fails
        }
    }

}
