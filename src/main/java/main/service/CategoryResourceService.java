package main.service;

import main.domain.Category;
import main.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryResourceService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResource getCategories() {
        LinkedList<Category> categories = categoryRepository.findAll();

        CategoryResource root = new CategoryResource(0, "root", new LinkedList<>(), true);
        processCategory(root, categories);
        return root;
    }

    private void processCategory(CategoryResource root, LinkedList<Category> categories) {
        List<CategoryResource> level;
        if(root.isRoot()) {
            level = categories.stream().filter(c -> c.getParentId() == 0)
                              .map(c -> new CategoryResource(c.getId(), c.getName())).collect(Collectors.toList());
        } else {
            level = categories.stream().filter(c -> c.getParentId() == root.getId())
                    .map(c -> new CategoryResource(c.getId(), c.getName()))
                    .collect(Collectors.toList());
        }
        root.getCategories().addAll(level);
        for(CategoryResource resource : level) {
            processCategory(resource, categories);
        }
    }

}
