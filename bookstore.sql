SET DEFINE OFF;

DROP TABLE order_details;

DROP TABLE books;

DROP TABLE orders;

DROP TABLE categories;

DROP TABLE tokens;

DROP TABLE users;

DROP TABLE roles;


CREATE TABLE roles(
	id NUMBER GENERATED BY DEFAULT AS IDENTITY,
    name NVARCHAR2(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT chk_name_roles CHECK (name IN ('admin','user'))
);

INSERT INTO roles (name) VALUES ('admin');
INSERT INTO roles (name) VALUES ('user');

CREATE TABLE users (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY,
    username NVARCHAR2(255) NOT NULL ,
    password NVARCHAR2(255) NOT NULL ,
    fullname NVARCHAR2(255) DEFAULT '',
    gender NVARCHAR2(255) DEFAULT '',
    dob DATE,
    phonenumber NVARCHAR2(255) NOT NULL,
    address NVARCHAR2(255) DEFAULT '',
    photourl NVARCHAR2(255) DEFAULT '',
    is_active NUMBER(1) DEFAULT 1,
    created_at DATE,
    updated_at DATE,
    role_id NUMBER NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uc_username UNIQUE (username),
    CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE tokens(
	id NUMBER GENERATED BY DEFAULT AS IDENTITY,
    token VARCHAR2(255) UNIQUE NOT NULL,
    token_type VARCHAR2(255) NOT NULL,
    expiration_date DATE,
    revoked NUMBER(1) NOT NULL,
    expired NUMBER(1) NOT NULL,
    user_id NUMBER,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_id_tokens FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE categories(
	id NUMBER GENERATED BY DEFAULT AS IDENTITY,
    name VARCHAR2(255) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO categories(name) VALUES('IT');

CREATE TABLE books (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY,
    name NVARCHAR2(255) NOT NULL,
    price NUMBER NOT NULL CHECK (price >= 0),
    photourl VARCHAR2(255) NOT NULL,
    author NVARCHAR2(255) NOT NULL,
    description CLOB NOT NULL,
    sumofproduct NUMBER NOT NULL,
    created_at DATE,
    updated_at DATE,
    rating FLOAT DEFAULT 0,
    quantityrating NUMBER DEFAULT 0,
    category_id NUMBER,
    PRIMARY KEY (id),
    CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES categories(id)
);


INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 1', 19.99, 'http://example.com/photos/book1.jpg', 'Author 1', 'Description of Book 1', 10, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 2', 29.99, 'http://example.com/photos/book2.jpg', 'Author 2', 'Description of Book 2', 15, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 3', 39.99, 'http://example.com/photos/book3.jpg', 'Author 3', 'Description of Book 3', 20, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 4', 49.99, 'http://example.com/photos/book4.jpg', 'Author 4', 'Description of Book 4', 25, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 5', 59.99, 'http://example.com/photos/book5.jpg', 'Author 5', 'Description of Book 5', 30, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 6', 69.99, 'http://example.com/photos/book6.jpg', 'Author 6', 'Description of Book 6', 35, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 7', 79.99, 'http://example.com/photos/book7.jpg', 'Author 7', 'Description of Book 7', 40, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 8', 89.99, 'http://example.com/photos/book8.jpg', 'Author 8', 'Description of Book 8', 45, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 9', 99.99, 'http://example.com/photos/book9.jpg', 'Author 9', 'Description of Book 9', 50, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 10', 109.99, 'http://example.com/photos/book10.jpg', 'Author 10', 'Description of Book 10', 55, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 11', 19.99, 'http://example.com/photos/book11.jpg', 'Author 11', 'Description of Book 11', 10, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 12', 29.99, 'http://example.com/photos/book12.jpg', 'Author 12', 'Description of Book 12', 15, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 13', 39.99, 'http://example.com/photos/book13.jpg', 'Author 13', 'Description of Book 13', 20, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 14', 49.99, 'http://example.com/photos/book14.jpg', 'Author 14', 'Description of Book 14', 25, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 15', 59.99, 'http://example.com/photos/book15.jpg', 'Author 15', 'Description of Book 15', 30, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 16', 69.99, 'http://example.com/photos/book16.jpg', 'Author 16', 'Description of Book 16', 35, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 17', 79.99, 'http://example.com/photos/book17.jpg', 'Author 17', 'Description of Book 17', 40, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 18', 89.99, 'http://example.com/photos/book18.jpg', 'Author 18', 'Description of Book 18', 45, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 19', 99.99, 'http://example.com/photos/book19.jpg', 'Author 19', 'Description of Book 19', 50, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 20', 109.99, 'http://example.com/photos/book20.jpg', 'Author 20', 'Description of Book 20', 55, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 21', 19.99, 'http://example.com/photos/book21.jpg', 'Author 21', 'Description of Book 21', 10, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 22', 29.99, 'http://example.com/photos/book22.jpg', 'Author 22', 'Description of Book 22', 15, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 23', 39.99, 'http://example.com/photos/book23.jpg', 'Author 23', 'Description of Book 23', 20, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 24', 49.99, 'http://example.com/photos/book24.jpg', 'Author 24', 'Description of Book 24', 25, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 25', 59.99, 'http://example.com/photos/book25.jpg', 'Author 25', 'Description of Book 25', 30, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 26', 69.99, 'http://example.com/photos/book26.jpg', 'Author 26', 'Description of Book 26', 35, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 27', 79.99, 'http://example.com/photos/book27.jpg', 'Author 27', 'Description of Book 27', 40, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 28', 89.99, 'http://example.com/photos/book28.jpg', 'Author 28', 'Description of Book 28', 45, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 29', 99.99, 'http://example.com/photos/book29.jpg', 'Author 29', 'Description of Book 29', 50, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 30', 109.99, 'http://example.com/photos/book30.jpg', 'Author 30', 'Description of Book 30', 55, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 31', 19.99, 'http://example.com/photos/book31.jpg', 'Author 31', 'Description of Book 31', 10, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 32', 29.99, 'http://example.com/photos/book32.jpg', 'Author 32', 'Description of Book 32', 15, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 33', 39.99, 'http://example.com/photos/book33.jpg', 'Author 33', 'Description of Book 33', 20, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 34', 49.99, 'http://example.com/photos/book34.jpg', 'Author 34', 'Description of Book 34', 25, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 35', 59.99, 'http://example.com/photos/book35.jpg', 'Author 35', 'Description of Book 35', 30, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 36', 69.99, 'http://example.com/photos/book36.jpg', 'Author 36', 'Description of Book 36', 35, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 37', 79.99, 'http://example.com/photos/book37.jpg', 'Author 37', 'Description of Book 37', 40, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 38', 89.99, 'http://example.com/photos/book38.jpg', 'Author 38', 'Description of Book 38', 45, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 39', 99.99, 'http://example.com/photos/book39.jpg', 'Author 39', 'Description of Book 39', 50, 1);
INSERT INTO books (name, price, photourl, author, description, sumofproduct, category_id) VALUES
('Book 40', 109.99, 'http://example.com/photos/book40.jpg', 'Author 40', 'Description of Book 40', 55, 1);

CREATE TABLE orders (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY,
    user_id NUMBER,
    fullname NVARCHAR2(255) DEFAULT '',
    email VARCHAR2(255) DEFAULT '',
    phonenumber VARCHAR2(255) NOT NULL,
    address NVARCHAR2(255) NOT NULL,
    note VARCHAR2(255) DEFAULT '',
    order_date DATE DEFAULT SYSDATE,
    status VARCHAR2(255),
    total_paid NUMBER CHECK (total_paid >= 0),
    shipping_method VARCHAR2(255),
    payment_method VARCHAR2(255),
    shipping_date DATE,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT chk_orders_status CHECK (status IN ('pending', 'processing', 'shipped', 'cancelled'))
);


CREATE TABLE order_details (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY,
    order_id NUMBER,
    book_id NUMBER,
    price NUMBER NOT NULL CHECK (price >= 0),
    num_of_book NUMBER NOT NULL CHECK (num_of_book >= 0),
    total_money NUMBER NOT NULL CHECK (total_money >= 0),
    CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES orders (id),
    CONSTRAINT fk_book_id FOREIGN KEY (book_id) REFERENCES books (id)
);


