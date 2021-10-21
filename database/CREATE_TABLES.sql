DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Persons;
DROP TABLE IF EXISTS Events;
DROP TABLE IF EXISTS AuthTokens;

CREATE TABLE Users
(
	Username   VARCHAR NOT NULL,
	Password   VARCHAR NOT NULL,
	Email      VARCHAR NOT NULL,
	FirstName  VARCHAR NOT NULL,
	LastName   VARCHAR NOT NULL,
	Gender     CHAR(1) NOT NULL,
	PersonID   VARCHAR NOT NULL,

	PRIMARY KEY(Username)
-- 	FOREIGN KEY(PersonID) REFERENCES Persons(PersonID) ON DELETE CASCADE
);

CREATE TABLE Persons
(
	PersonID            VARCHAR NOT NULL,
	AssociatedUsername  VARCHAR NOT NULL,
	FirstName           VARCHAR NOT NULL,
	LastName            VARCHAR NOT NULL,
	Gender              CHAR(1) NOT NULL,
	FatherID            VARCHAR,
	MotherID            VARCHAR,
	SpouseID            VARCHAR,

	PRIMARY KEY(PersonID)
-- 	FOREIGN KEY(AssociatedUsername) REFERENCES Users(Username)   ON DELETE CASCADE,
-- 	FOREIGN KEY(FatherID)           REFERENCES Persons(PersonID) ON DELETE CASCADE,
-- 	FOREIGN KEY(MotherID)           REFERENCES Persons(PersonID) ON DELETE CASCADE,
-- 	FOREIGN KEY(SpouseID)           REFERENCES Persons(PersonID) ON DELETE CASCADE
);

CREATE TABLE Events
(
	EventID             VARCHAR NOT NULL,
	AssociatedUsername  VARCHAR NOT NULL,
	PersonID            VARCHAR NOT NULL,
	Latitude            REAL    NOT NULL,
	Longitude           REAL    NOT NULL,
	Country             VARCHAR NOT NULL,
	City                VARCHAR NOT NULL,
	EventType           VARCHAR NOT NULL,
	Year                INTEGER NOT NULL,

	PRIMARY KEY(EventID)
-- 	FOREIGN KEY(AssociatedUsername) REFERENCES Users(Username)   ON DELETE CASCADE,
-- 	FOREIGN KEY(PersonID)           REFERENCES Persons(PersonID) ON DELETE CASCADE
);

CREATE TABLE AuthTokens
(
	TokenID            VARCHAR,
	AssociatedUsername VARCHAR,

	PRIMARY KEY(TokenID)
-- 	FOREIGN KEY(AssociatedUsername) REFERENCES Users(Username) ON DELETE CASCADE
);
