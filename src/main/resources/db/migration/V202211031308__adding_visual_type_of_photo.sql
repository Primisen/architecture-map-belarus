CREATE TABLE photo_visual_type
(
    id   SERIAL         PRIMARY KEY,
    name VARCHAR(20)    NOT NULL
);

ALTER TABLE photo
    ADD COLUMN visual_type_id INT;

ALTER TABLE photo
    ADD CONSTRAINT fk_visual_type_photo FOREIGN KEY (visual_type_id)
        REFERENCES photo_visual_type (id);