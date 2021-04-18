package main.mapping;

import lombok.extern.log4j.Log4j2;
import main.domain.Category;
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
        Category category = process(categoryMapping);
        categoryRepository.save(category);
    }

    private Category process(CategoryMapping categoryMapping) {
        Category category = new Category(categoryMapping.getName());
        for (CategoryMapping mapping : categoryMapping.getCategories()) {
            category.getCategories().add(process(mapping));
        }
        return category;
    }
}
