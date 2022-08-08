CREATE TABLE construction(
    id                          UUID                PRIMARY KEY,
    name                        VARCHAR(50)         NOT NULL,
    address                     VARCHAR(50)         NOT NULL
);

CREATE TABLE photo(

    id                          UUID                PRIMARY KEY,
    url_address_to_photo        VARCHAR(140)        NOT NULL,
    id_construction             UUID                NOT NULL,

    FOREIGN KEY (id_construction) REFERENCES construction(id)
);
