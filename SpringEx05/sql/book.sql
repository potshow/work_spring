DROP DATABASE bookmgr;

CREATE DATABASE bookmgr;

SHOW DATABASES;

DROP TABLE book;

CREATE TABLE users (
	no			INT				NOT NULL	PRIMARY KEY		AUTO_INCREMENT,
	email		VARCHAR(255)	NOT NULL,
	password	VARCHAR(255)	NOT NULL,
	name		VARCHAR(255)	NOT NULL,
	attachment	VARCHAR(255),
	UNIQUE (email)
);

CREATE TABLE book(
	isbn			INT				NOT NULL 		PRIMARY KEY AUTO_INCREMENT,
	user_no			INT				NOT NULL,
	title			VARCHAR(20)		NOT NULL,
	author			VARCHAR(20)		NOT	NULL,
	publisher		VARCHAR(20)		NOT NULL,
	price			INT				NOT NULL,
	description		VARCHAR(255)		NULL,
	attachment		VARCHAR(255),
	FOREIGN KEY (user_no) REFERENCES users(no)
);

SHOW TABLES;

SELECT * FROM book;

INSERT INTO book (user_no, title, author, publisher, price, description, attachment)
	VALUES (2, '나의 살던 고향은', '꽃', '피는', 30000, '산골', NULL);

SELECT * FROM users;

INSERT INTO users (email, password, name, attachment)
	VALUES	('jihyo_hello@naver.com', '1111', '강예은', NULL);
	
INSERT INTO users (email, password, name, attachment)
	VALUES	('TEST1@naver.com', '1111', '주지훈', NULL);
	
INSERT INTO users (email, password, name, attachment)
	VALUES	('TEST2@naver.com', '1111', '원빈', NULL);