-- 1. ??????? ?? ???????
SELECT * From PLAYER;
SELECT * From COMMAND;
SELECT * From CUP;
SELECT * From cup_command;
SELECT * From game_command;
SELECT * From GAME;
SELECT * From STAT;

-- 2. ?????? ?? ????????
DROP TABLE PLAYER;
DROP TABLE COMMAND;
DROP TABLE CUP;
DROP TABLE cup_command;
DROP TABLE game_command;;
DROP TABLE GAME;
DROP TABLE STAT;

--3. ?????? ?? ???????? ??????

-- ???????
create table command (
  id int Primary Key auto_increment,
  name_command varchar2(50) not null,
  coach varchar2(60) not null,
  sponsor varchar2(150),
  sex boolean not null
);

-- ?????
create table player (
  id int Primary Key auto_increment,
  name_player varchar2(20) not null,
  surname varchar2(20) not null,
  patronymic varchar2(20) not null,
  number int(3),
  id_command int
);

-- ??????
create table cup (
  id int Primary Key auto_increment,
  name_cup varchar2(100) not null,
  sponsor VARCHAR2(100),
  start_cup DATE not null,
  end_cup DATE not null
);

-- ??????_???????
create table cup_command(
  id_cup int not null,
  id_command int NOT NULL,
  PRIMARY KEY (id_cup, id_command)
);

-- ????
create table game (
  id int PRIMARY KEY auto_increment,
  date_game DATE NOT NULL,
  id_cup int not null,
);

-- ????_???????
create table game_command(
  id_game int not null,
  id_command int not null,
  set_1 int not null,
  set_2 int not null,
  set_3 int not null,
  set_4 int,
  set_5 int,
  PRIMARY KEY (id_game, id_command)
);

--?????
create table stat(
  id_game int not null,
  id_player int not null,
  columnPointTot int,
  columnPointWon int,
  columnPointWonLose int,
  columnServeTot int,
  columnServeError int,
  columnServePoint int,
  columnReceptionTot int,
  columnReceptionError int,
  columnReceptionGood int,
  columnReceptionNice int,
  columnAttackTot int,
  columnAttackError int,
  columnAttackBlock int,
  columnAttackPoint int,
  columnBlockPoint int,
  columnBlockError int,
  PRIMARY KEY (id_game,id_player)
);

-- 4. ??????? ?? ???????? ??????

-- ??????? ???? ??? ??????? ?????
alter table player add foreign key(id_command) references command(id) on update cascade on delete cascade;

-- ??????? ???? ??? ??????? ??????-???????
alter table cup_command  add foreign key(id_cup) references cup(id) on update cascade on delete cascade;
alter table cup_command add foreign key(id_command) references command(id) on update cascade on delete cascade;

-- ??????? ???? ??? ??????? ????
alter table game add foreign key(id_cup) references cup(id) on update cascade on delete cascade;

-- ??????? ???? ??? ??????? ?????
alter table stat add foreign key(id_game) references game(id) on update cascade on delete cascade;
alter table stat add foreign key(id_player) references player(id) on update cascade on delete cascade;

-- ??????? ???? ??? ??????? ????-???????
alter table game_command add foreign key(id_game) references game(id) on update cascade on delete cascade;
alter table game_command add foreign key(id_command) references command(id) on update cascade on delete cascade;

-- 5. ?????? ?? ???????
-- ???????
insert into command(name_command, coach, sponsor, sex) values('????', '??????? ????? ????????????', '????', true);
insert into command(name_command, coach, sponsor, sex) values('?????????', '???????????', '?????????', true);
insert into command(name_command, coach, sponsor, sex) values('????', '?? ?????', '????', true);

insert into command(name_command, coach, sponsor, sex) values('?????????', '???????', '?????????', false);
insert into command(name_command, coach, sponsor, sex) values('????', '??????? ???????', '????', false);
insert into command(name_command, coach, sponsor, sex) values('????', '??????? ????? ????????????', '????', false);

-- ??????? ????
insert into player(name_player, surname, patronymic, id_command) values('????????', '??????', '?????????', (SELECT id FROM command WHERE name_command = '????' and sex=true));
insert into player(name_player, surname, patronymic, id_command) values('??????', '????????', '?????????', (SELECT id FROM command WHERE name_command = '????' and sex=true));
insert into player(name_player, surname, patronymic, id_command) values('?????', '?????', '', (SELECT id FROM command WHERE name_command = '????' and sex=true));
insert into player(name_player, surname, patronymic, id_command) values('??????', '??????', '', (SELECT id FROM command WHERE name_command = '????' and sex=true));
-- ??????? ????
insert into player(name_player, surname, patronymic, id_command) values('???????', '???????', '', (SELECT id FROM command WHERE name_command = '????' and sex=true));
insert into player(name_player, surname, patronymic, id_command) values('?????', '????????', '', (SELECT id FROM command WHERE name_command = '????' and sex=true));
insert into player(name_player, surname, patronymic, id_command) values('????', '??????', '', (SELECT id FROM command WHERE name_command = '????' and sex=true));
insert into player(name_player, surname, patronymic, id_command) values('??????', '??????', '', (SELECT id FROM command WHERE name_command = '????' and sex=true));
-- ??????? ?????????
insert into player(name_player, surname, patronymic, id_command) values('???????', '????????', '', (SELECT id FROM command WHERE name_command = '?????????' and sex=true));
insert into player(name_player, surname, patronymic, id_command) values('?????', '???????????', '', (SELECT id FROM command WHERE name_command = '?????????' and sex=true));
insert into player(name_player, surname, patronymic, id_command) values('???????', '?????', '', (SELECT id FROM command WHERE name_command = '?????????' and sex=true));
insert into player(name_player, surname, patronymic, id_command) values('????', '????????', '', (SELECT id FROM command WHERE name_command = '?????????' and sex=true));
-- ??????? ?????????
insert into player(name_player, surname, patronymic, id_command) values('?????', '??????', '', (SELECT id FROM command WHERE name_command = '?????????' and sex=false));
insert into player(name_player, surname, patronymic, id_command) values('???????', '', '', (SELECT id FROM command WHERE name_command = '?????????' and sex=false));
insert into player(name_player, surname, patronymic, id_command) values('???????', '?????????', '', (SELECT id FROM command WHERE name_command = '?????????' and sex=false));
insert into player(name_player, surname, patronymic, id_command) values('??????', '????????', '', (SELECT id FROM command WHERE name_command = '?????????' and sex=false));
-- ??????? ????
insert into player(name_player, surname, patronymic, id_command) values('?????', '?????????', '????????', (SELECT id FROM command WHERE name_command = '????' and sex=false));
insert into player(name_player, surname, patronymic, id_command) values('???????', '??????', '', (SELECT id FROM command WHERE name_command = '????' and sex=false));
insert into player(name_player, surname, patronymic, id_command) values('?????????', '????????', '', (SELECT id FROM command WHERE name_command = '????' and sex=false));
insert into player(name_player, surname, patronymic, id_command) values('?????????', '????????', '', (SELECT id FROM command WHERE name_command = '????' and sex=false));
-- ??????? ????
insert into player(name_player, surname, patronymic, id_command) values('????', '', '', (SELECT id FROM command WHERE name_command = '????' and sex=false));
insert into player(name_player, surname, patronymic, id_command) values('??????', '???????', '', (SELECT id FROM command WHERE name_command = '????' and sex=false));
insert into player(name_player, surname, patronymic, id_command) values('?????', '???????', '', (SELECT id FROM command WHERE name_command = '????' and sex=false));
insert into player(name_player, surname, patronymic, id_command) values('????', '????????', '', (SELECT id FROM command WHERE name_command = '????' and sex=false));

-- ???????
insert into cup(name_cup, sponsor, start_cup, end_cup) values('????? ????', '????', CURDATE(), CURDATE());
insert into cup(name_cup, sponsor, start_cup, end_cup) values('????? ????', '????', CURDATE(), CURDATE());
insert into cup(name_cup, sponsor, start_cup, end_cup) values('????? ?????????', '??????????', CURDATE(), CURDATE());

-- ??????-???????
insert into cup_command(id_cup, id_command) VALUES('1', '1');
insert into cup_command(id_cup, id_command) VALUES('1', '2');

-- ????
insert into game(date_game, id_cup) VALUES (CURDATE(), 1,);

-- ????_???????
insert into game_command(id_game, id_command, set_1, set_2, set_3, set_4, set_5) VALUES (1, 1, 25, 25, 25, 0, 0);
insert into game_command(id_game, id_command, set_1, set_2, set_3, set_4, set_5) VALUES (1, 2, 23, 23, 23, 0, 0);

-- ?????
insert into stat(id_game, id_player, columnPointTot, columnPointWon, columnPointWonLose,
                 columnServeTot, columnServeError, columnServePoint,
                 columnReceptionTot, columnReceptionError, columnReceptionGood, columnReceptionNice,
                 columnAttackTot, columnAttackError, columnAttackBlock, columnAttackPoint,
                 columnBlockPoint, columnBlockError)
VALUES (1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
insert into stat(id_game, id_player, columnPointTot, columnPointWon, columnPointWonLose,
                 columnServeTot, columnServeError, columnServePoint,
                 columnReceptionTot, columnReceptionError, columnReceptionGood, columnReceptionNice,
                 columnAttackTot, columnAttackError, columnAttackBlock, columnAttackPoint,
                 columnBlockPoint, columnBlockError)
VALUES (1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
insert into stat(id_game, id_player, columnPointTot, columnPointWon, columnPointWonLose,
                 columnServeTot, columnServeError, columnServePoint,
                 columnReceptionTot, columnReceptionError, columnReceptionGood, columnReceptionNice,
                 columnAttackTot, columnAttackError, columnAttackBlock, columnAttackPoint,
                 columnBlockPoint, columnBlockError)
VALUES (1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
insert into stat(id_game, id_player, columnPointTot, columnPointWon, columnPointWonLose,
                 columnServeTot, columnServeError, columnServePoint,
                 columnReceptionTot, columnReceptionError, columnReceptionGood, columnReceptionNice,
                 columnAttackTot, columnAttackError, columnAttackBlock, columnAttackPoint,
                 columnBlockPoint, columnBlockError)
VALUES (1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
insert into stat(id_game, id_player, columnPointTot, columnPointWon, columnPointWonLose,
                 columnServeTot, columnServeError, columnServePoint,
                 columnReceptionTot, columnReceptionError, columnReceptionGood, columnReceptionNice,
                 columnAttackTot, columnAttackError, columnAttackBlock, columnAttackPoint,
                 columnBlockPoint, columnBlockError)
VALUES (1, 9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
insert into stat(id_game, id_player, columnPointTot, columnPointWon, columnPointWonLose,
                 columnServeTot, columnServeError, columnServePoint,
                 columnReceptionTot, columnReceptionError, columnReceptionGood, columnReceptionNice,
                 columnAttackTot, columnAttackError, columnAttackBlock, columnAttackPoint,
                 columnBlockPoint, columnBlockError)
VALUES (1, 10, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
insert into stat(id_game, id_player, columnPointTot, columnPointWon, columnPointWonLose,
                 columnServeTot, columnServeError, columnServePoint,
                 columnReceptionTot, columnReceptionError, columnReceptionGood, columnReceptionNice,
                 columnAttackTot, columnAttackError, columnAttackBlock, columnAttackPoint,
                 columnBlockPoint, columnBlockError)
VALUES (1, 11, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
insert into stat(id_game, id_player, columnPointTot, columnPointWon, columnPointWonLose,
                 columnServeTot, columnServeError, columnServePoint,
                 columnReceptionTot, columnReceptionError, columnReceptionGood, columnReceptionNice,
                 columnAttackTot, columnAttackError, columnAttackBlock, columnAttackPoint,
                 columnBlockPoint, columnBlockError)
VALUES (1, 12, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);

-- 6.??????????? ???????

-- ??????? ??? ??????? ???????
select *
from command where sex = true;
-- ??????? ??? ??????? ???????
select *
from command where sex = false;

-- ??????? ???? ??????
select *
from player where id_command in (select id from command where sex = true);
-- ??????? ???? ??????
select *
from player where id_command in (select id from command where sex = false);

-- ????? ?????????? ??????? ?? ??? ????
select sum(s.columnPointTot), sum(s.columnPointWon), sum(s.columnPointWonLose),
  sum(s.columnServeTot), sum(s.columnServeError), sum(s.columnServePoint),
  sum(s.columnReceptionTot), sum(s.columnReceptionError), sum(s.columnReceptionGood), sum(s.columnReceptionNice),
  sum(s.columnAttackTot), sum(s.columnServeError), sum(s.columnAttackBlock), sum(s.columnAttackPoint),
  sum(s.columnBlockPoint), sum(s.columnBlockError)
from stat s
where id_player in (
  select p.id as p_id from  player p
  where p.id_command = 1
);

-- ????? ?????????? ?????? ?? ??? ????
select sum(s.columnPointTot), sum(s.columnPointWon), sum(s.columnPointWonLose),
  sum(s.columnServeTot), sum(s.columnServeError), sum(s.columnServePoint),
  sum(s.columnReceptionTot), sum(s.columnReceptionError), sum(s.columnReceptionGood), sum(s.columnReceptionNice),
  sum(s.columnAttackTot), sum(s.columnServeError), sum(s.columnAttackBlock), sum(s.columnAttackPoint),
  sum(s.columnBlockPoint), sum(s.columnBlockError)
from stat s
where id_player = 1;


-- 7.??????? ?? ??????????, ????????

-- ?????????? ?????? ??????
-- ?????????? ???????, ?????, ???????? ? ??????
update player set name_player = '????????', surname = '??????', patronymic = '?????????', number = 8
where id = 1;
-- ??????? ? ?????? ???????
update player set id_command = '1'
where id = 1;
-- ???????? ?????? ? ??????
delete from player where id = 0;

-- ?????????? ?????? ???????
update command set name_command = '????', coach = '???????', sponsor = '????'
where id = 1;
-- ???????? ?????? ? ???????
delete from command where id = 0;

-- ?????????? ?????? ? ???????
-- ?????????? ????????, ????????, ???? ??????, ???? ?????????
update cup set name_cup = '????? ????', sponsor = '????', start_cup = curdate(), end_cup = curdate()
where id = 0;

-- ???????? ?????? ? ???????
delete from cup where id = 1;

-- ?????????? ?????? ? ????
update game set id_cup = 0, date_game = curdate()
where id = 0;

-- ???????? ?????? ? ????
delete from game where id = 0;

-- ??. ????????? ???????
select max(id) from command;

-- все игры, команды и счет
SELECT * from game g INNER JOIN game_command gc ON g.id = gc.id_game INNER JOIN command c ON c.id = gc.id_command;
SELECT g.id, g.date_game, c.id as id_command, c.name_command, c.sex, gc.set_1, gc.set_2,gc.set_3,gc.set_4, gc.set_5
from game g INNER JOIN game_command gc ON g.id = gc.id_game INNER JOIN command c ON c.id = gc.id_command
WHERE g.id = 1 and g.id_cup = 1;

