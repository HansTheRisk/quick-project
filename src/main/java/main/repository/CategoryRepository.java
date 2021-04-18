package main.repository;

import main.domain.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.LinkedList;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    @Query("SELECT c FROM Category c ORDER BY c.parent_id ASC")
    LinkedList<Category> findAll();

}
