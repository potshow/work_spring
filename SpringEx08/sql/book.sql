SHOW TABLES;
DROP TABLE board;

#DDL (Data Definition Language)
DROP TABLE book;
DROP TABLE users_authority;
DROP TABLE authority;
DROP TABLE users;

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

# 사용자 권한 정의한 테이블
CREATE TABLE authority (
	id		INT			NOT NULL	PRIMARY KEY,
	name 	VARCHAR(30) NOT NULL
);

# 사용자 번호와 사용자 권한 아이디 값을 연결
CREATE TABLE users_authority (
	users_no		INT		NOT NULL,
	authority_id	INT		NOT NULL,
	FOREIGN KEY (users_no)	REFERENCES users(no),
	FOREIGN KEY (authority_id)	REFERENCES authority(id)
);

#DQL(Data Query Language)
SHOW TABLES;
SELECT * FROM book;
SELECT * FROM users;
SELECT * FROM authority;
SELECT * FROM users_authority;

#DML(Data Manipulation Language)

# 권한 입력
INSERT INTO authority (id, name) 
	VALUES (10, 'ADMIN');
	
INSERT INTO authority (id, name) 
	VALUES (20, 'USER');

# 책 예시 입력
INSERT INTO book (user_no, title, author, publisher, price, description, attachment)
	VALUES (2, '나의 살던 고향은', '꽃', '피는', 30000, '산골', NULL);

# 회원 예시 입력
INSERT INTO users (email, password, name, attachment)
	VALUES	('admin@naver.com', '$2a$10$MzzmhmOpVRb1VgME/ei37.iXjvxFr8FtzFVlZkIiyndqbtyykQfWy', '도서 관리자(K)', NULL);
	
INSERT INTO users (email, password, name, attachment)
	VALUES	('test1@naver.com', '$2a$10$MzzmhmOpVRb1VgME/ei37.iXjvxFr8FtzFVlZkIiyndqbtyykQfWy', '유저 고양이 JK씨', NULL);
	
INSERT INTO users (email, password, name, attachment)
	VALUES	('test2@naver.com', '$2a$10$MzzmhmOpVRb1VgME/ei37.iXjvxFr8FtzFVlZkIiyndqbtyykQfWy', '유저겸 관리자 백씨', NULL);
	
# 사용자에게 권한 부여
INSERT INTO users_authority VALUES (1, 10); 
INSERT INTO users_authority VALUES (2, 20);
INSERT INTO users_authority VALUES (4, 10); 
INSERT INTO users_authority VALUES (3, 20);
	
# 1. users_authority 테이블과 authority 테이블을 EQUI JOIN 하는 SQL문
SELECT users_authority.users_no, authority.id, authority.name 
FROM users_authority, authority 
WHERE users_authority.authority_id = authority.id;

# 2. 1번에서 조인한 결과 테이블과 users 테이블을 EQUI JOIN 하는 SQL문
SELECT u.no, u.email, u.password, u.name, u.attachment, ua.id, ua.name as "aname" 
FROM users u,
	(SELECT users_authority.users_no, authority.id, authority.name
	FROM users_authority, authority
	WHERE users_authority.authority_id = authority.id) ua
WHERE u.no = ua.users_no;

# 3. 2번 결과에서 한 사용자에 대한 정보만 가져오는 SQL문 (2번 + AND u.no = #{no})
SELECT u.no, u.email, u.password, u.name, u.attachment, ua.id, ua.name as "aname" 
FROM users u,
	(SELECT users_authority.users_no, authority.id, authority.name
	FROM users_authority, authority
	WHERE users_authority.authority_id = authority.id) ua
WHERE u.no = ua.users_no AND u.no = #{no};

# 4. Book 테이블과 users 테이블 EQUI JOIN
SELECT b.isbn, b.title, b.author, b.user_no, b.publisher, b.price, b.description, b.attachment, 
	u.email, u.name, u.attachment as "uattachment" 
FROM book b, users u 
WHERE b.user_no = u.no ORDER BY b.isbn DESC;

# 5. 4번 SQL문에서 하나의 게시물을 선택하기 위한 SQL문
SELECT b.isbn, b.title, b.author, b.user_no, b.publisher, b.price, b.description, b.attachment, 
	u.email, u.name, u.attachment as "uattachment" 
FROM book b, users u 
WHERE b.user_no = u.no AND b.isbn = #{isbn} ORDER BY b.isbn DESC;

# 6. 사용자 번호 3번 유저와 같이 관리자 권한과 일반사용자 권한을 함께 입력할 경우
INSERT INTO users_authority(users_no, authority_id) VALUES 
(8, 10),
(8, 20);

