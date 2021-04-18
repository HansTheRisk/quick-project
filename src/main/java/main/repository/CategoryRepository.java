package main.repository;

import main.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    LinkedList<Category> findAll();
}
