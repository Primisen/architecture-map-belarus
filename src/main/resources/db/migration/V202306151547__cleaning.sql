CREATE TABLE IF NOT EXISTS architectural_attribute
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(50) NOT NULL,
    description  TEXT         NOT NULL,
    url_ti_image VARCHAR(500) NOT NULL
);

CREATE TABLE IF NOT EXISTS architectural_style_architectural_attribute
(
    id                         SERIAL PRIMARY KEY,
    architectural_style_id     INT NOT NULL,
    architectural_attribute_id INT NOT NULL,

    FOREIGN KEY (architectural_style_id) REFERENCES architectural_style (id),
    FOREIGN KEY (architectural_attribute_id) REFERENCES architectural_attribute (id)
);

CREATE TABLE IF NOT EXISTS construction_architect
(
    id              SERIAL PRIMARY KEY,
    construction_id INT NOT NULL,
    architect_id    INT NOT NULL,

    FOREIGN KEY (construction_id) REFERENCES construction (id),
    FOREIGN KEY (architect_id) REFERENCES architect (id)
);

ALTER TABLE photo
    ADD COLUMN show BOOL DEFAULT TRUE;