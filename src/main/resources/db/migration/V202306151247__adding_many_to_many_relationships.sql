CREATE TABLE construction_architect (
    id SERIAL PRIMARY KEY,
    construction_id INT NOT NULL,
    architect_id INT NOT NULL,

    FOREIGN KEY (construction_id) REFERENCES construction(id),
    FOREIGN KEY (architect_id) REFERENCES architect(id)
);

ALTER TABLE construction
DROP CONSTRAINT fk_architect_construction;

ALTER TABLE construction
DROP COLUMN architect_id;


CREATE TABLE architectural_style_attribute(
    id SERIAL PRIMARY KEY,
    architectural_style_id INT NOT NULL,
    attribute_id INT NOT NULL,

    FOREIGN KEY (architectural_style_id) REFERENCES architectural_style(id),
    FOREIGN KEY (attribute_id) REFERENCES attribute_of_architectural_style(id)
);

ALTER TABLE architectural_style
DROP CONSTRAINT fk_attribute_architectural_style;

ALTER TABLE architectural_style
DROP COLUMN attribute_id;
