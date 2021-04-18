package main.mapping;

import lombok.extern.log4j.Log4j2;
import main.domain.Category;
import main.domain.Product;
import main.repository.CategoryRepository;
import main.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@Log4j2
public class InventoryPersistor {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private InventoryJsonLoader inventoryJsonLoader;

    public void persist() {
        if(categoryRepository.count() == 0) {
            try {
                processCategory(inventoryJsonLoader.readInventory());
            } catch (IOException e) {
                log.error("Could not read inventory from file: ", e);
            }
        }
    }

    private void processCategory(CategoryMapping categoryMapping) {
        process(0, categoryMapping);
    }

    private void process(int parentId, CategoryMapping categoryMapping) {
        int id = 0;
        if(!categoryMapping.isRoot()) {
            categoryRepository.save(new Category(parentId, categoryMapping.getName()));
            for(ProductMapping productMapping : categoryMapping.getProducts()) {
                processProduct(id, productMapping);
            }
        }
        for(CategoryMapping child : categoryMapping.getCategories()) {
            process(id, child);
        }
    }

    private void processProduct(int categoryId, ProductMapping productMapping) {
        try {
            UUID uuid = productRepository.save(new Product(productMapping.getName(), productMapping.getPrice()))
                                         .getId();
        } catch (Exception e) {
            // Revert if fails
        }
    }

}
