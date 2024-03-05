CREATE TABLE architect_image
(
    image_id        SERIAL PRIMARY KEY REFERENCES image (id),
    architect_id INT NOT NULL,

    FOREIGN KEY (architect_id) REFERENCES architect (id)
);