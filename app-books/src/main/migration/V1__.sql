CREATE TABLE books
(
    isbn    VARCHAR(255) NOT NULL,
    title   VARCHAR(255),
    price   DECIMAL,
    version INTEGER,
    CONSTRAINT pk_books PRIMARY KEY (isbn)
);

DROP TABLE authors CASCADE;