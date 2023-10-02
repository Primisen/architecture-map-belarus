DROP TABLE IF EXISTS construction_image;
DROP TABLE IF EXISTS architectural_attribute_image;
DROP TABLE IF EXISTS image;


CREATE TABLE image
(
    id        SERIAL PRIMARY KEY,
    url       VARCHAR(1000) NOT NULL,
    show      BOOL DEFAULT TRUE,
    source_id INT           NOT NULL,

    FOREIGN KEY (source_id) REFERENCES source (id)
);

CREATE TABLE construction_image
(
    image_id        SERIAL PRIMARY KEY REFERENCES image (id),
    construction_id INT NOT NULL,
    year            VARCHAR(100),

    FOREIGN KEY (construction_id) REFERENCES construction (id)
);

CREATE TABLE architectural_attribute_image
(
    image_id                   SERIAL PRIMARY KEY REFERENCES image (id),
    architectural_attribute_id INT NOT NULL,

    FOREIGN KEY (architectural_attribute_id) REFERENCES architectural_attribute (id)
);
