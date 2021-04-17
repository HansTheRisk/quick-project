package main.mapping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryMapping {

    private List<CategoryMapping> categories = new ArrayList<>();
    private String name;
    private List<ProductMapping> products = new ArrayList<>();
    private boolean isRoot = false;

    public void addCategory(CategoryMapping categoryMapping) {
        categories.add(categoryMapping);
    }

    public void addProduct(ProductMapping productMapping) {
        products.add(productMapping);
    }

}
