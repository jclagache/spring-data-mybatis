DROP TABLE address IF EXISTS;
DROP TABLE customer IF EXISTS;
CREATE MEMORY TABLE customer (
  id BIGINT IDENTITY PRIMARY KEY,
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  email_address VARCHAR(255));
CREATE UNIQUE INDEX ix_customer_email ON CUSTOMER (email_address ASC);
CREATE MEMORY TABLE address (
  id BIGINT IDENTITY PRIMARY KEY,
  customer_id BIGINT CONSTRAINT address_customer_ref REFERENCES customer (id),
  street VARCHAR(255),
  city VARCHAR(255),
  country VARCHAR(255));