CREATE TABLE IF NOT EXISTS tag
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS article
(
    id                     SERIAL PRIMARY KEY,
    title                  VARCHAR(70)   NOT NULL,
    content                TEXT          NOT NULL,
    short_description      VARCHAR(1000) NOT NULL,
    demonstrative_image_id INT           NOT NULL,

    FOREIGN KEY (demonstrative_image_id) REFERENCES image (id)
);

CREATE TABLE IF NOT EXISTS tag_article
(
    id         SERIAL PRIMARY KEY,
    tag_id     INT NOT NULL,
    article_id INT NOT NULL,

    FOREIGN KEY (tag_id) REFERENCES tag (id),
    FOREIGN KEY (article_id) REFERENCES article (id)
);
