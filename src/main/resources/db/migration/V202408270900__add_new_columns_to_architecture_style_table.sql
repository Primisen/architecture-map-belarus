ALTER TABLE architectural_style
ADD COLUMN demonstrative_image_id INT,
ADD COLUMN short_description TEXT,
ADD COLUMN years_active VARCHAR(45);

ALTER TABLE architectural_style
    ADD CONSTRAINT fk_image_architectural_style FOREIGN KEY (demonstrative_image_id)
        REFERENCES image(id);