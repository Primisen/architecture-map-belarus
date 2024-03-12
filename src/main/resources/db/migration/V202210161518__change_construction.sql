CREATE TABLE architectural_style(
    id      SERIAL          PRIMARY KEY,
    name    VARCHAR(20)     NOT NULL
);

ALTER TABLE construction
ADD COLUMN architectural_style_id INT,
ADD COLUMN building_time VARCHAR(20);

ALTER TABLE construction
ADD CONSTRAINT fk_architectural_style_construction FOREIGN KEY (architectural_style_id)
REFERENCES architectural_style (id);