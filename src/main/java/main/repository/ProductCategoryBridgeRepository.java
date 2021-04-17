package main.repository;

import main.domain.ProductCategoryBridge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductCategoryBridgeRepository extends CrudRepository<ProductCategoryBridge, Integer> {
}
