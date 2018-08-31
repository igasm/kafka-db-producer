CREATE USER demo IDENTIFIED BY demo;
GRANT CONNECT TO demo;
GRANT CREATE SESSION TO demo;
GRANT CREATE TABLE TO demo;
GRANT UNLIMITED TABLESPACE TO demo;

ALTER SESSION SET CURRENT_SCHEMA = demo;

CREATE TABLE clients (
    id NUMBER(5) PRIMARY KEY,
    first_name VARCHAR2(15) NOT NULL,
    last_name VARCHAR2(15) NOT NULL
);

CREATE TABLE orders (
    id NUMBER(5) PRIMARY KEY,
    price NUMBER(5, 2) NOT NULL,
    client_id NUMBER(5) NOT NULL CONSTRAINT orders_clients_fk REFERENCES clients (id)
);

INSERT INTO clients (id, first_name, last_name) VALUES (0, 'Max', 'Smith');
INSERT INTO clients (id, first_name, last_name) VALUES (1, 'John', 'Snow');

INSERT INTO ORDERS (id, price, client_id) VALUES (0, 15.76, 0);
INSERT INTO ORDERS (id, price, client_id) VALUES (1, 267.26, 1);