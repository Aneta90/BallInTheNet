INSERT INTO TEAMS
(name,totalScore)
 VALUES 
('Rzeszow',30),
('Warsaw',40);

INSERT INTO Players
(firstName,surName,age,experience,isInjured,rating,team_entity_team_id)
VALUES
('Aneta','Wroble',20,20,true,100,1),
('Ana','Wro',30,30,true,100,2),
('Seb','Lub',30,10,false ,90,1),
('Ana','Wro',35,20,true,99,2);

INSERT INTO GAMES
(teamHomeId,teamAwayId,teamHomeName,teamAwayName,teamHomeScore,
teamAwayScore,isTeamHomeWin,isTeamAwayWin,date)
VALUES 
(2,1,'Warsaw','Rzeszow',2,3,false,true,2019-04-01),
(2,1,'Warsaw','Rzeszow',23,33,false,true,2018-02-11),
(1,2,'Rzeszow','Warsaw',22,443,false,true,2017-01-21);
