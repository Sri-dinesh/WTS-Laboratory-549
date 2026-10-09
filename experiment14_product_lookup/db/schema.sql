CREATE DATABASE IF NOT EXISTS product_catalog;
USE product_catalog;

CREATE TABLE IF NOT EXISTS products (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(120) NOT NULL UNIQUE,
    product_description VARCHAR(500) NOT NULL,
    price DECIMAL(10, 2) NOT NULL CHECK (price >= 0)
);

INSERT INTO products (product_name, product_description, price)
VALUES ('Notebook', 'A5 ruled notebook for everyday notes.', 4.99)
ON DUPLICATE KEY UPDATE product_name = product_name;