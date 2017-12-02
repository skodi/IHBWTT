--<ScriptOptions statementTerminator=";"/>

CREATE TABLE accountItems (
	id INT NOT NULL,
	accountBalance DOUBLE,
	amount DOUBLE,
	avgPrice DOUBLE,
	betId BIGINT,
	betSize DOUBLE,
	betType VARCHAR(255),
	betCategoryType VARCHAR(255),
	commissionRate VARCHAR(255),
	eventId INT,
	eventTypeId INT,
	fullMarketName VARCHAR(255),
	grossBetAmount DOUBLE,
	marketName VARCHAR(255),
	marketType TINYBLOB,
	placedDate DATETIME,
	selectionId INT,
	selectionName VARCHAR(255),
	settledDate DATETIME,
	startDate DATETIME,
	transactionType VARCHAR(255),
	transactionId BIGINT,
	winLose VARCHAR(255),
	PRIMARY KEY (id)
);

