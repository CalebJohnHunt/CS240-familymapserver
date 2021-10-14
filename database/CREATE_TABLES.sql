DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS authToken;

CREATE TABLE user
(
	username   VARCHAR NOT NULL,
	password   VARCHAR NOT NULL,
	email      VARCHAR NOT NULL,
	first_name VARCHAR NOT NULL,
	last_name  VARCHAR NOT NULL,
	gender     CHAR(1) NOT NULL,
	person_id  VARCHAR NOT NULL,
	
	FOREIGN KEY(person_id) REFERENCES person(person_id)
);

CREATE TABLE person
(
	person_id           VARCHAR NOT NULL,
	associated_username USER    NOT NULL,
	first_name          VARCHAR NOT NULL,
	last_name           VARCHAR NOT NULL,
	father_id           PERSON,
	mother_id           PERSON,
	spouse_id           PERSON,
	
	PRIMARY KEY(person_id),
	FOREIGN KEY(associated_username) REFERENCES user(username),
	FOREIGN KEY(father_id)           REFERENCES person(person_id),
	FOREIGN KEY(mother_id)           REFERENCES person(person_id),
	FOREIGN KEY(spouse_id)           REFERENCES person(person_id)
);

CREATE TABLE event
(
	event_id            VARCHAR NOT NULL,
	associated_username USER    NOT NULL,
	person_id           PERSON  NOT NULL,
	latitude            REAL    NOT NULL,
	longitude           REAL    NOT NULL,
	country             VARCHAR NOT NULL,
	city                VARCHAR NOT NULL,
	eventType           VARCHAR NOT NULL,
	year                INTEGER NOT NULL,
	
	PRIMARY KEY(event_id),
	FOREIGN KEY(associated_username) REFERENCES user(username),
	FOREIGN KEY(person_id)           REFERENCES person(person_id)
);

CREATE TABLE authToken
(
	token_id            VARCHAR,
	associated_username USER,
	
	PRIMARY KEY(token_id),
	FOREIGN KEY(associated_username) REFERENCES user(username)
);
