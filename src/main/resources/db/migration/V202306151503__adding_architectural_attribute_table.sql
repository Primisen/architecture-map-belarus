CREATE TABlE attribute_of_architectural_style
(
    id              SERIAL         PRIMARY KEY,
    name            VARCHAR(50)    NOT NULL,
    description     TEXT           NOT NULL,
    url_to_image    VARCHAR(500)   NOT NULL
);