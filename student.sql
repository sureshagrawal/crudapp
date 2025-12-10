DROP DATABASE IF EXISTS cruddb;
CREATE DATABASE cruddb;

\c cruddb

DROP TABLE IF EXISTS student;
CREATE TABLE student(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    mobile VARCHAR(10) NOT NULL
);

INSERT INTO student(name,email,mobile) VALUES('Om ghodekar','om@gmail.com','1234567890');
INSERT INTO student(name,email,mobile) VALUES('Bhav bothare','bhav@gmail.com','9876543210');
INSERT INTO student(name,email,mobile) VALUES('Gaurav kadam','gaurav@gmail.com','1122334455');

SELECT * FROM student;