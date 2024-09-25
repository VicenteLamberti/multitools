CREATE SCHEMA IF NOT EXISTS crudcleanarchddd;

CREATE TABLE IF NOT EXISTS crudcleanarchddd.CATEGORY (
    ID VARCHAR(255) PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    DELETED BOOLEAN NOT NULL,
    UPDATED_AT TIMESTAMP(6),
    DELETED_AT TIMESTAMP(6)
);