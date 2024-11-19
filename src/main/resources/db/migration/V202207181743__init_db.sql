CREATE TABLE IF NOT EXISTS source
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(50)  NOT NULL,
    url         VARCHAR(500) NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS image
(
    id        SERIAL PRIMARY KEY,
    url       VARCHAR(1000) NOT NULL,
    show      BOOL          NOT NULL DEFAULT TRUE,
    source_id INT           NOT NULL,
    author    VARCHAR(100),

    FOREIGN KEY (source_id) REFERENCES source (id)
);

CREATE TABLE IF NOT EXISTS address
(
    id           SERIAL PRIMARY KEY,
    locality     VARCHAR(20),
    district     VARCHAR(20),
    region       VARCHAR(20) NOT NULL,
    street       VARCHAR(20),
    house_number VARCHAR(5)
);

CREATE TABLE IF NOT EXISTS architectural_attribute
(
    id                     SERIAL PRIMARY KEY,
    name                   VARCHAR(50) NOT NULL,
    description            TEXT,
    demonstrative_image_id INT,

    FOREIGN KEY (demonstrative_image_id) REFERENCES image (id)
);

CREATE TABLE IF NOT EXISTS architectural_style
(
    id                     SERIAL PRIMARY KEY,
    name                   VARCHAR(40) NOT NULL,
    short_description      VARCHAR(1000),
    description            TEXT,
    demonstrative_image_id INT,
    years_active           VARCHAR(45),

    FOREIGN KEY (demonstrative_image_id) REFERENCES image (id)
);

CREATE TABLE IF NOT EXISTS architectural_style_architectural_attribute
(
    id                         SERIAL PRIMARY KEY,
    architectural_style_id     INT NOT NULL,
    architectural_attribute_id INT NOT NULL,

    FOREIGN KEY (architectural_style_id) REFERENCES architectural_style (id),
    FOREIGN KEY (architectural_attribute_id) REFERENCES architectural_attribute (id)
);

CREATE TABLE IF NOT EXISTS architect
(
    id                     SERIAL PRIMARY KEY,
    name                   VARCHAR(50)   NOT NULL,
    surname                VARCHAR(50)   NOT NULL,
    middle_name            VARCHAR(50),
    years_of_life          VARCHAR(50)   NOT NULL,
    short_work_description VARCHAR(1000) NOT NULL,
    biographical_article   TEXT,
    portrait_image_id      INT,

    FOREIGN KEY (portrait_image_id) REFERENCES image (id)
);

CREATE TABLE IF NOT EXISTS construction
(
    id                     SERIAL PRIMARY KEY,
    name                   VARCHAR(100) NOT NULL,
    address_id             INT          NOT NULL,
    description            TEXT,
    architectural_style_id INT          NOT NULL,
    building_date          VARCHAR(20),
    building_century       SMALLINT,

    FOREIGN KEY (address_id) REFERENCES address (id),
    FOREIGN KEY (architectural_style_id) REFERENCES architectural_style (id)
);

CREATE TABLE IF NOT EXISTS construction_image
(
    image_id        SERIAL PRIMARY KEY REFERENCES image (id),
    construction_id INT NOT NULL,
    taken_time      VARCHAR(100),

    FOREIGN KEY (construction_id) REFERENCES construction (id)
);

CREATE TABLE IF NOT EXISTS construction_architect
(
    id              SERIAL PRIMARY KEY,
    construction_id INT NOT NULL,
    architect_id    INT NOT NULL,

    FOREIGN KEY (construction_id) REFERENCES construction (id),
    FOREIGN KEY (architect_id) REFERENCES architect (id)
);
