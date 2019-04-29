CREATE TABLE players (
    playerId BIGINT NOT NULL auto_increment,
    firstName VARCHAR(255),
    surName VARCHAR(255),
    age INTEGER,
    experience INTEGER,
    isInjured BOOLEAN,
    rating INTEGER,
    teamId BIGINT,
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
    gameId BIGINT NOT NULL auto_increment,
    teamHomeId INTEGER UNIQUE,
    teamAwayId INTEGER UNIQUE,
    teamHomeName VARCHAR(255) UNIQUE,
    teamAwayName VARCHAR(255) UNIQUE,
    teamHomeScore INTEGER,
    teamAwayScore INTEGER,
    isTeamHomeWin BOOLEAN,
    isTeamAwayWin BOOLEAN,
    date DATE,
    CONSTRAINT PK_Games PRIMARY KEY (gameId)
);

# CREATE TABLE games_teams (
#     gameId INTEGER NOT NULL REFERENCES games (gameId),
#     teamHomeId INTEGER NOT NULL REFERENCES teams (teamId),
#     teamAwayId INTEGER NOT NULL REFERENCES teams (teamId),
#     PRIMARY KEY (gameId)
# );
