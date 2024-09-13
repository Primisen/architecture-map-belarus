ALTER TABLE construction
    ADD COLUMN building_century smallint;

ALTER TABLE construction
    RENAME COLUMN building_time TO building_date;
