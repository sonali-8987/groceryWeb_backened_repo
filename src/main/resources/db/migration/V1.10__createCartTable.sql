CREATE TABLE CART
(
ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
PRODUCT_ID BIGINT REFERENCES PRODUCT (ID) ,
QUANTITY INTEGER,
USER_ID BIGINT,
PRIMARY KEY (ID)
);