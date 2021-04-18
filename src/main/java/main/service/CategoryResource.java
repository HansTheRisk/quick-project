package main.service;

import lombok.NoArgsConstructor;
import main.domain.Category;
import main.mapping.CategoryMapping;

import java.util.LinkedList;

@NoArgsConstructor
public class CategoryResource {

    private int id;
    private String name;
    private LinkedList<CategoryResource> categories = new LinkedList<>();
    private boolean isRoot = false;

    public CategoryResource(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryResource(int id,
                            String name,
                            LinkedList<CategoryResource> categories,
                            boolean isRoot) {
        this.name = name;
        this.categories = categories;
        this.isRoot = isRoot;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LinkedList<CategoryResource> getCategories() {
        return categories;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void addCategory(CategoryResource category) {
        categories.add(category);
    }

}
