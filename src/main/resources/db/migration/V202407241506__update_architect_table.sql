ALTER TABLE architect
    ADD COLUMN biographical_article TEXT;

ALTER TABLE architect
    RENAME COLUMN work_description TO short_work_description;