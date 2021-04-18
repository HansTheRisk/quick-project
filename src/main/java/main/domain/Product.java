package main.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

//@RedisHash("Product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "products")
public class Product {

    @Id
    private UUID id;
    private String name;
    private BigDecimal price;
    private String categoryPath;

    public Product(String name, BigDecimal price, String categoryPath) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.categoryPath = categoryPath;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", categoryPath='" + categoryPath + '\'' +
                '}';
    }
}
