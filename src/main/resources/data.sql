SET MODE MySQL;

CREATE TABLE IF NOT EXISTS categories (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(250) NOT NULL,
    parentId INT NOT NULL,
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
    productId VARCHAR(36),
    categoryId INT,
    FOREIGN KEY (productId) REFERENCES products(id),
    FOREIGN KEY (categoryId) REFERENCES categories(id),
    PRIMARY KEY(id)
);