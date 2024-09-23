CREATE TABLE IF NOT EXISTS role
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS usr
(
    id             SERIAL PRIMARY KEY,
    username       VARCHAR(16) NOT NULL UNIQUE,
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
