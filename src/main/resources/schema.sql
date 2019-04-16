CREATE TABLE players (
    playerId INTEGER NOT NULL auto_increment,
    firstName VARCHAR(20),
    surName VARCHAR(20),
    age INTEGER,
    experience INTEGER,
    isInjured BOOLEAN,
    rating INTEGER,
    teamId INTEGER,
    CONSTRAINT PK_Players PRIMARY KEY (playerId)
);

CREATE TABLE teams (
    teamId INTEGER NOT NULL auto_increment,
    name VARCHAR(16),
    totalScore INTEGER,
    CONSTRAINT PK_Teams PRIMARY KEY (teamId),
    FOREIGN KEY (teamId) REFERENCES players(playerId)

);

CREATE TABLE games (
    gameId INTEGER NOT NULL auto_increment,
    teamHomeId INTEGER UNIQUE,
    teamAwayId INTEGER UNIQUE,
    teamHomeName VARCHAR(16) UNIQUE,
    teamAwayName VARCHAR(16) UNIQUE,
    teamHomeScore INTEGER,
    teamAwayScore INTEGER,
    isTeamHomeWin BOOLEAN,
    isTeamAwayWin BOOLEAN,
    date TIMESTAMP,
    CONSTRAINT PK_Games PRIMARY KEY (gameId),
    FOREIGN KEY (teamHomeId) REFERENCES teams(teamId),
    FOREIGN KEY (teamAwayId) REFERENCES teams(teamId)
);

CREATE TABLE games_teams (
    gameId INTEGER NOT NULL REFERENCES games (gameId),
    teamHomeId INTEGER NOT NULL REFERENCES teams (teamId),
    teamAwayId INTEGER NOT NULL REFERENCES teams (teamId),
    PRIMARY KEY (gameId)
);