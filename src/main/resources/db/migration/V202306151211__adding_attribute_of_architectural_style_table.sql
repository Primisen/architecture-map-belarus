CREATE TABlE attribute_of_architectural_style (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    url_to_image VARCHAR(500) NOT NULL
);

ALTER TABLE architectural_style
ADD COLUMN attribute_id INT;

ALTER TABLE architectural_style
ADD CONSTRAINT fk_attribute_architectural_style FOREIGN KEY (attribute_id)
REFERENCES attribute_of_architectural_style(id);
