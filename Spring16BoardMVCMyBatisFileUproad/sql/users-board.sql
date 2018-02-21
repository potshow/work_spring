INSERT INTO users (email, password, name)
	VALUES ('apple@koitt.com', '1111', '김사과');
	
SELECT * FROM users ORDER BY no DESC;

INSERT INTO board(title, content, user_no, regdate) 
	VALUES ('사과-1', '사과내용-1', 7, CURDATE());

	SELECT * FROM board;
	
	SELECT b.no, b.title, b.content, u.email, b.regdate
	FROM board b, users u
	WHERE b.user_no = u.no;
	
	# b테이블의 user_no 와 u테이블에 no가 같을 경우에 select를 실행한다.