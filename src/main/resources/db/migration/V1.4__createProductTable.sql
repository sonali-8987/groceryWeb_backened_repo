CREATE TABLE PRODUCT (
ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
CATEGORY VARCHAR(255) NOT NULL,
ITEM VARCHAR(255) NOT NULL,
PRICE NUMERIC(8,2) NOT NULL ,
PRIMARY KEY(ID)
);
