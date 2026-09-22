-- Run this once before using the Java app:
--   mysql -u root -p < setup.sql

CREATE DATABASE IF NOT EXISTS jdbc_demo;
USE jdbc_demo;

CREATE TABLE IF NOT EXISTS users (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    email    VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    city     VARCHAR(100),
    phone    VARCHAR(20)
);
