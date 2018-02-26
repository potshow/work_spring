
# no : 회원번호
# email : 이메일 (아이디용도)
# password : 비밀번호
# name : 이름

DROP TABLE users;

CREATE TABLE users (
	no			INT				NOT NULL	PRIMARY KEY		AUTO_INCREMENT,
	email		VARCHAR(255)	NOT NULL,
	password	VARCHAR(255)	NOT NULL,
	name		VARCHAR(255)	NOT NULL,
	UNIQUE (email)
);
# UNIQUE 중복된 값을 허용 안하는 키워드

INSERT INTO users (email, password, name)
	VALUES	('jihyo_hello@naver.com', '1111', '강예은');
	
INSERT INTO users (email, password, name)
	VALUES	('TEST1@naver.com', '1111', '주지훈');
	
INSERT INTO users (email, password, name)
	VALUES	('TEST2@naver.com', '1111', '원빈');

SHOW TABLES;

SHOW DATABASES;

USE koitt;
	
USE users;