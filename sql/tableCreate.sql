DROP SEQUENCE gamer_seq;

DROP TABLE gamer;

--Játékos tábla
--azonosító - gamer_id -kulcs
--név - name
--megnyert játszmák száma - win_games
--vesztett játszmák száma - lost_games
--döntetlen Játszmák száma - draw_games

CREATE SEQUENCE gamer_seq;

CREATE TABLE gamer (
    gamer_id        NUMBER(10) NOT NULL,
    name            VARCHAR(20) NOT NULL,
    pic_file_name   VARCHAR(100),
    win_games       NUMBER(10) DEFAULT 0 NOT NULL,
    lost_games      NUMBER(10) DEFAULT 0 NOT NULL,
    draw_games      NUMBER(10) DEFAULT 0 NOT NULL,
    CONSTRAINT gamer_pk PRIMARY KEY ( gamer_id ),
    CONSTRAINT gamer_ck1 CHECK ( win_games >= 0 ),
    CONSTRAINT gamer_ck2 CHECK ( lost_games >= 0 ),
    CONSTRAINT gamer_ck3 CHECK ( draw_games >= 0 )
);

COMMIT;