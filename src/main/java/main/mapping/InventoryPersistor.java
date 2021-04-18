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
        Category category = process(new StringBuilder(), categoryMapping);
        categoryRepository.save(category);
    }

    private Category process(StringBuilder pathBuilder, CategoryMapping categoryMapping) {
        if(!categoryMapping.isRoot()) {
            pathBuilder.append("#").append(categoryMapping.getName());
        }
        Category category = new Category(categoryMapping.getName());
        categoryMapping.getProducts().forEach(p -> saveProduct(pathBuilder.toString(), p));

        for (CategoryMapping mapping : categoryMapping.getCategories()) {
            category.getCategories().add(process(new StringBuilder(pathBuilder.toString()), mapping));
        }
        return category;
    }

    private void saveProduct(String categoryPath, ProductMapping mapping) {
        productRepository.save(new Product(mapping.getName(), mapping.getPrice(), categoryPath));
    }
}
