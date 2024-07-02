DROP DATABASE IF EXISTS bookstore;

CREATE DATABASE bookstore;

USE bookstore;

CREATE TABLE roles(
	id INT PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

ALTER TABLE roles 
MODIFY COLUMN name ENUM('admin','user');

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username NVARCHAR(255) NOT NULL ,
    password NVARCHAR(255) NOT NULL ,
    fullname NVARCHAR(255) DEFAULT '',
    gender NVARCHAR(255) DEFAULT '',
    dob DATE,
    phoneNumber NVARCHAR(255) NOT NULL,
    address NVARCHAR(255) DEFAULT '',
    photoUrl NVARCHAR(255) DEFAULT '',
    is_active TINYINT(1) DEFAULT 1,
    createAt DATE,
    updateAt DATE,
    role_id INT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE tokens(
	id INT PRIMARY KEY AUTO_INCREMENT,
    token VARCHAR(255) UNIQUE NOT NULL,
    token_type VARCHAR(255) NOT NULL,
    expiration_date DATETIME,
    revoked TINYINT(1) NOT NULL,
    expired TINYINT(1) NOT NULL,
    user_id INT,
    FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE categories(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL DEFAULT ''
);

CREATE TABLE books(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name NVARCHAR(255) NOT NULL,
    price FLOAT NOT NULL CHECK(price >= 0),
    photoUrl VARCHAR(255) NOT NULL ,
    author NVARCHAR(255) NOT NULL ,
    description LONGTEXT NOT NULL,
    sumofproduct INT NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    rating FLOAT DEFAULT 0,
    quantityrating INT DEFAULT 0,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE orders(
	id INT PRIMARY KEY AUTO_INCREMENT,
    user_id int,
    FOREIGN KEY(user_id) REFERENCES users(id),
    fullname NVARCHAR(255) DEFAULT '',
    email VARCHAR(255) DEFAULT '',
    phoneNumber VARCHAR(255) NOT NULL,
    address NVARCHAR(255) NOT NULL,
    note VARCHAR(255) DEFAULT '',
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(255) ,
    total_paid FLOAT NOT NULL CHECK(total_paid >= 0 ),
    shipping_method VARCHAR(255),
    payment_method VARCHAR(255),
    shipping_date DATE
);

ALTER TABLE orders
MODIFY COLUMN status ENUM('pending','processing','shipped','cancelled') COMMENT 'Trạng thái đơn hàng';

CREATE TABLE order_details(
	id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    book_id INT,
    FOREIGN KEY(book_id) REFERENCES books(id),
    price FLOAT NOT NULL CHECK(price >=0 ),
    num_of_book INT NOT NULL CHECK(num_of_book >= 0),
    total_money FLOAT NOT NULL CHECK(total_money >= 0)
);

