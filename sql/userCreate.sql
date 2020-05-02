--create user
ALTER SESSION SET container=xepdb1;
SELECT sys_context('USERENV','CON_NAME') FROM dual;

CREATE USER blackjack IDENTIFIED BY admin;
GRANT ALL PRIVILEGES TO blackjack;
