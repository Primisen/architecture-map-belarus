CREATE TABLE address(
    id                          SERIAL              PRIMARY KEY,
    locality                    VARCHAR(20)         NOT NULL,
    district                    VARCHAR(15)         NOT NULL,
    region                      VARCHAR(15)         NOT NULL,

    street                      VARCHAR(20),
    house_number                VARCHAR(5)
);

CREATE TABLE construction(
    id                          SERIAL              PRIMARY KEY,
    name                        VARCHAR(50)         NOT NULL,
    address_id                  INT                 NOT NULL,

    FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE photo(
    id                          SERIAL              PRIMARY KEY,
    url_address_to_photo        VARCHAR(140)        NOT NULL,
    construction_id             INT                 NOT NULL,

    FOREIGN KEY (construction_id) REFERENCES construction(id)
);
