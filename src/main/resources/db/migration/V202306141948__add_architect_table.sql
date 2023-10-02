CREATE TABLE architect (
    id                  SERIAL          PRIMARY KEY,
    name                VARCHAR(50)     NOT NULL,
    surname             VARCHAR(50)     NOT NULL,
    years_of_life       VARCHAR(50)     NOT NULL,
    work_description    TEXT            NOT NULL
);

ALTER TABLE construction ADD COLUMN architect_id INT;

ALTER TABLE construction
ADD CONSTRAINT fk_architect_construction FOREIGN KEY (architect_id)
REFERENCES architect(id);
