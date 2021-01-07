CREATE TABLE guestbook (no number,
                        name VARCHAR2(80) NOT NULL,
                        password VARCHAR2(20),
                        content VARCHAR2(2000),
                        reg_date date,
                        PRIMARY KEY (no));

DELETE FROM guestbook;

DROP SEQUENCE seq_no;

CREATE SEQUENCE seq_no INCREMENT by 1 START WITH 1;

SELECT  no as "식별번호",
        name as "이름",
        password as "비밀번호",
        content as "본문",
        TO_CHAR(reg_date, 'yyyy-mm-dd hh:mi:ss') as "date"
FROM    guestbook
ORDER BY no asc;

INSERT INTO guestbook VALUES(seq_no.nextval, '이정재', 'password', 'content' , sysdate);

commit;

rollback;