package main.mapping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryMapping {

    private LinkedList<CategoryMapping> categories = new LinkedList<>();
    private String name;
    private LinkedList<ProductMapping> products = new LinkedList<>();
    private boolean isRoot = false;

}
