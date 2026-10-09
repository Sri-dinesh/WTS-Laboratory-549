CREATE DATABASE IF NOT EXISTS jdbc_demo;
USE jdbc_demo;

CREATE TABLE IF NOT EXISTS swap_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(120) NOT NULL,
    category VARCHAR(60) NOT NULL,
    description VARCHAR(255) NOT NULL,
    item_condition VARCHAR(40) NOT NULL,
    contact_email VARCHAR(120) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'Available',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
