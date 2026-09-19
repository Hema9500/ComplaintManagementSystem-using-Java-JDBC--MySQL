CREATE DATABASE complaint_management;

USE COMPLAINT_MANAGEMENT;

CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'USER'
);

CREATE TABLE complaints (
    complaint_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    title VARCHAR(200) NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_complaint_user
        FOREIGN KEY (user_id)
        REFERENCES users(user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

select * from users;

select * from complaints;

drop table complaints;

drop table users;

INSERT INTO users (name, email, password, role)
VALUES
('Hemalatha', 'hema@gmail.com', '1234', 'Admin'),
('Divya', 'Divya@gmail.com', '1234', 'User'),
('Girija', 'Girija@gmail.com', '1234', 'User');





