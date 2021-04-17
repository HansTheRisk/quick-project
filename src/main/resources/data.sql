SET MODE MySQL;

CREATE TABLE IF NOT EXISTS categories (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(250) NOT NULL,
    UNIQUE(name),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS products (
    id VARCHAR(36) NOT NULL,
    name VARCHAR(250),
    price DECIMAL(10,2) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS products_categories_bridge (
    id INT NOT NULL AUTO_INCREMENT,
    product_id VARCHAR(36),
    category_id INT,
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (category_id) REFERENCES categories(id),
    PRIMARY KEY(id)
);