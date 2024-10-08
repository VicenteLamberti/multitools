CREATE SCHEMA IF NOT EXISTS crudmvc;


CREATE TABLE IF NOT EXISTS crudmvc.EMPLOYEE (
    ID BIGINT PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    OCCUPATION_ID BIGINT NOT NULL,

    FOREIGN KEY (OCCUPATION_ID) REFERENCES crudmvc.OCCUPATION(ID)
);

