CREATE TABLE construction(
    id                  UUID                PRIMARY KEY,
    name                VARCHAR(50)         NOT NULL,
    address             VARCHAR(50)         NOT NULL,
    path_to_photo       VARCHAR(70)         NOT NULL
);