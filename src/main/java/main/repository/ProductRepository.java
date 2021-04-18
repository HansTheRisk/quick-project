package main.repository;

import main.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends CrudRepository<Product, UUID> {

    List<Product> findByCategoryPathContainingIgnoreCase(String path);

}
