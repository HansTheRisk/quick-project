package main.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "products_categories_bridge")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCategoryBridge {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private UUID productId;
    private int categoryId;

    public ProductCategoryBridge(UUID productId, int categoryId) {
        this.productId = productId;
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ProductCategoryBridge{" +
                "id=" + id +
                ", productId=" + productId +
                ", categoryId=" + categoryId +
                '}';
    }
}
