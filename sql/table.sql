create database MySqlTest;
use MySqlTest;
CREATE TABLE IF NOT EXISTS BigTable 
(	Id INT(11),
	rollno INT,
	name VARCHAR(256),
	salary INT,
	marks INT,
	address VARCHAR(256),
	city VARCHAR(256),
	country VARCHAR(256),
	religion VARCHAR(256),
	Status VARCHAR(256),
	Age INT ,
	PRIMARY KEY(Id)
);

