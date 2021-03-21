DROP TABLE IF EXISTS Address;
DROP TABLE IF EXISTS Employee;

CREATE TABLE Address (
 address_id INT AUTO_INCREMENT  PRIMARY KEY,
 line1 VARCHAR(255) NOT NULL,
 line2 VARCHAR(255) DEFAULT NULL,
 city VARCHAR(255) NOT NULL,
 state VARCHAR(255) NOT NULL,
 zipp_code INT NOT NULL
);

CREATE TABLE Employee (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  date_of_birth DATE NOT NULL,
  address_id INT NOT NULL,
  foreign key (address_id) references Address(address_id)
);

