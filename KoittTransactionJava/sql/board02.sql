CREATE TABLE BOARD02 (
	NO		INT		PRIMARY KEY,
	TITLE	VARCHAR(30),
	CONTENT	VARCHAR(255),
	WRITER	VARCHAR(30),
	REGDATE	DATE
);

SHOW TABLES;

INSERT INTO board02 (no, title, content, writer, regdate)
	VALUES (2, 'TITLE-2', 'CONTENT-2', 'WRITER-2', CURDATE());

# 데이터베이스에서 최근 글의 글번호를 가져오는 SQL문
# 만약 처음 10개를 가져오고 싶다. -> LIMIT 10 일케 적으면 댐
SELECT no FROM board02 	ORDER BY no DESC LIMIT 1;

SELECT * FROM board02;