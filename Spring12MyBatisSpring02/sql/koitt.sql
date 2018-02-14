CREATE TABLE owner (
	owner_name VARCHAR(30) PRIMARY KEY
);

CREATE TABLE pet (
	pet_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	pet_name VARCHAR(30),
	owner_name VARCHAR(30),
	pet_type VARCHAR(30),
	birth_date DATE,
	FOREIGN KEY (owner_name) REFERENCES owner(owner_name)
);

INSERT INTO owner VALUES('LEE');
INSERT INTO owner VALUES('KANG');
INSERT INTO owner VALUES('BAEK');
INSERT INTO owner VALUES('PARK');
INSERT INTO owner VALUES('KIM');

INSERT INTO pet (pet_name, owner_name, pet_type, birth_date) 
	VALUES ('PONG-EE', 'LEE', '먼치킨', STR_TO_DATE('2009-01-01', '%Y-%m-%d'));

INSERT INTO pet (pet_name, owner_name, pet_type, birth_date) 
	VALUES ('NAVI', 'BAEK', '노르웨이숲', STR_TO_DATE('2010-01-01', '%Y-%m-%d'));

INSERT INTO pet (pet_name, owner_name, pet_type, birth_date) 
	VALUES ('JANG-GOON', 'PARK', '메인쿤', STR_TO_DATE('2015-01-01', '%Y-%m-%d'));
	
INSERT INTO pet (pet_name, owner_name, pet_type, birth_date) 
	VALUES ('KAY', 'KANG', '페르시안 친칠라', STR_TO_DATE('2017-01-01', '%Y-%m-%d'));
	
INSERT INTO pet (pet_name, owner_name, pet_type, birth_date) 
	VALUES ('ANNA', 'KIM', '코리안숏헤어', STR_TO_DATE('2010-01-01', '%Y-%m-%d'));
	
INSERT INTO pet (pet_name, owner_name, pet_type, birth_date) 
	VALUES ('JAY', 'KANG', '노르웨이 숲', STR_TO_DATE('2017-01-01', '%Y-%m-%d'));

	
SELECT * FROM owner;
SELECT * FROM pet;