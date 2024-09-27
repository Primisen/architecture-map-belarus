CREATE TABLE IF NOT EXISTS role
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS usr
(
    id             SERIAL PRIMARY KEY,
    username       VARCHAR(45) NOT NULL UNIQUE,
    enable         BOOLEAN     NOT NULL DEFAULT (false),
    password       VARCHAR(60) NOT NULL,
    name           VARCHAR(45),
    surname        VARCHAR(45),
    about_themself TEXT
);

CREATE TABLE IF NOT EXISTS role_user
(
    id      SERIAL PRIMARY KEY,
    role_id INT NOT NULL,
    user_id INT NOT NULL,

    FOREIGN KEY (role_id) REFERENCES role (id),
    FOREIGN KEY (user_id) REFERENCES usr (id)
);

CREATE TABLE IF NOT EXISTS email_verification_token
(
    id          SERIAL PRIMARY KEY,
    token       VARCHAR(36) NOT NULL,
    user_id     INT         NOT NULL,
    expiry_date TIMESTAMP   NOT NULL,

    FOREIGN KEY (user_id) REFERENCES usr (id)
);

CREATE TABLE IF NOT EXISTS user_image
(
    id                    SERIAL PRIMARY KEY,
    user_id               INT     NOT NULL,
    approved_by_admin     BOOLEAN NOT NULL DEFAULT (false),
    construction_image_id INT     NOT NULL,

    FOREIGN KEY (user_id) REFERENCES usr (id),
    FOREIGN KEY (construction_image_id) REFERENCES construction_image (image_id)
);
