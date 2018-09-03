CREATE TABLE USER (
id bigint(20) AUTO_INCREMENT,
created_at datetime,
enabled bit(1),
mail_address varchar(255),
mail_address_verified bit(1),
password varchar(255),
username varchar(255),
PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE user_authorities(id bigint(20),authorities varchar(255),PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=UTF8;
