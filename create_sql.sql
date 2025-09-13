CREATE TABLE member(
                       id INT(11) NOT NULL AUTO_INCREMENT,
                       user_id VARCHAR(200) NOT NULL,
                       password VARCHAR(200) NULL,
                       email VARCHAR(200) NULL,
                       registered_at  DATETIME,
                       fail_cnt SMALLINT,
                       CONSTRAINT member_PK PRIMARY KEY(id)
);

CREATE TABLE book(
                     id INT(11) NOT NULL AUTO_INCREMENT,
                     title VARCHAR(200) NOT NULL,
                     writer VARCHAR(200) NULL,
                     publisher VARCHAR(200) NULL,
                     published_at  DATETIME,
                     CONSTRAINT book_PK PRIMARY KEY(id)
);