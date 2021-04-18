package main.repository;

import main.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface ProductRepository extends CrudRepository<Product, UUID> {

    public List<Product> findAllByCategory();

}
