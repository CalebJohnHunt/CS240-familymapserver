DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Persons;
DROP TABLE IF EXISTS Events;
DROP TABLE IF EXISTS AuthTokens;

CREATE TABLE Users
(
	Username  VARCHAR NOT NULL,
	Password   VARCHAR NOT NULL,
	Email      VARCHAR NOT NULL,
	FirstName  VARCHAR NOT NULL,
	LastName   VARCHAR NOT NULL,
	Gender     CHAR(1) NOT NULL,
	PersonID   VARCHAR NOT NULL,

	PRIMARY KEY(Username),
	FOREIGN KEY(PersonID) REFERENCES Persons(PersonID)
);

CREATE TABLE Persons
(
	PersonID            VARCHAR NOT NULL,
	AssociatedUsername  Users   NOT NULL,
	FirstName           VARCHAR NOT NULL,
	LastName            VARCHAR NOT NULL,
	FatherID            Persons,
	MotherID            Persons,
	SpouseID            Persons,
	
	PRIMARY KEY(PersonID),
	FOREIGN KEY(AssociatedUsername) REFERENCES Users(Username),
	FOREIGN KEY(FatherID)           REFERENCES Persons(PersonID),
	FOREIGN KEY(MotherID)           REFERENCES Persons(PersonID),
	FOREIGN KEY(SpouseID)           REFERENCES Persons(PersonID)
);

CREATE TABLE Events
(
	EventID             VARCHAR NOT NULL,
	AssociatedUsername  Users   NOT NULL,
	PersonID            Persons NOT NULL,
	Latitude            REAL    NOT NULL,
	Longitude           REAL    NOT NULL,
	Country             VARCHAR NOT NULL,
	City                VARCHAR NOT NULL,
	EventType           VARCHAR NOT NULL,
	Year                INTEGER NOT NULL,
	
	PRIMARY KEY(EventID),
	FOREIGN KEY(AssociatedUsername) REFERENCES Users(Username),
	FOREIGN KEY(PersonID)           REFERENCES Persons(PersonID)
);

CREATE TABLE AuthTokens
(
	TokenID VARCHAR,
	AssociatedUsername Users,
	
	PRIMARY KEY(TokenID),
	FOREIGN KEY(AssociatedUsername) REFERENCES Users(Username)
);
