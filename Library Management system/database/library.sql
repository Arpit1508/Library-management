CREATE DATABASE library;

USE library;

CREATE TABLE books(
    id INT PRIMARY KEY AUTO_INCREMENT,
    book_title VARCHAR(100),
    author VARCHAR(100),
    price DOUBLE,
    quantity INT
);
select * from books