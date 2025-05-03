--liquibase formatted sql
--changeset marcos:29042025
--comment; boards table create

CREATE TABLE boards(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL 
)ENGINE=InnoDB;

