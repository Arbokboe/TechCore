CREATE TABLE author
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE book
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    title     VARCHAR(255) NOT NULL,
    year      INTEGER      NOT NULL,
    author_id BIGINT,
    CONSTRAINT fk_book_author FOREIGN KEY (author_id) REFERENCES author (id)
);

CREATE INDEX idx_book_author_id ON book (author_id);
CREATE INDEX idx_author_name ON author (name);