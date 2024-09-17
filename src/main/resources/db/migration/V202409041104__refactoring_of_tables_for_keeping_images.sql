ALTER TABLE architect
    ADD COLUMN portrait_image_id INT;

ALTER TABLE architect
    ADD CONSTRAINT fk_image_architect FOREIGN KEY (portrait_image_id)
        REFERENCES image(id);

DROP TABLE architect_image;

ALTER TABLE architectural_attribute
    ADD COLUMN demonstrative_image_id INT;

ALTER TABLE architectural_attribute
    ADD CONSTRAINT fk_image_architectural_attribute FOREIGN KEY (demonstrative_image_id)
        REFERENCES image(id);

DROP TABLE architectural_attribute_image;
