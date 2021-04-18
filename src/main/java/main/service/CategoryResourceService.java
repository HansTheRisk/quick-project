package main.service;

import main.domain.Category;
import main.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CategoryResourceService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResource getCategories() {
        LinkedList<Category> categories = categoryRepository.findAll();
        Map<Integer, Category> categoryMap = categories.stream().collect(Collectors.toMap(Category::getId, Function.identity()));

        CategoryResource root = new CategoryResource(0, "root", new LinkedList<>(), true);
        processCategory(root, categoryMap);
        return null;
    }

    private void processCategory(CategoryResource root, Map<Integer, Category> categories) {
        List<CategoryResource> level;
        if(root.isRoot()) {
            level = categories.values()
                              .stream().filter(c -> c.getParentId() == 0)
                              .map(c -> new CategoryResource(c.getId(), c.getName())).collect(Collectors.toList());
        } else {
            level = categories.values()
                    .stream().filter(c -> c.getParentId() == root.getId())
                    .map(c -> new CategoryResource(c.getId(), c.getName()))
                    .collect(Collectors.toList());
        }
        root.getCategories().addAll(level);
        for(CategoryResource resource : level) {
            processCategory(resource, categories);
        }
    }

}
