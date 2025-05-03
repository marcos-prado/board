--liquibase formatted sql
--changeset marcos:29042025
--comment; cards table create

CREATE TABLE cards (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    board_column_id BIGINT NOT NULL,
    CONSTRAINT boards_colmuns__cards_fk FOREIGN KEY (board_column_id) REFERENCES boards_columns (id) ON DELETE CASCADE
) ENGINE = InnoDB;

--rollback DROP TABLE CARDS;