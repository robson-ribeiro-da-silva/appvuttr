CREATE TABLE tool (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  description varchar(255) NOT NULL,
  link varchar(50) NOT NULL,
  listtags tinyblob,
  title varchar(50) NOT NULL,
  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  email varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (email)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE role (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_role (
  user_id bigint(20) NOT NULL,
  role_id bigint(20) NOT NULL,
  KEY (role_id),
  KEY (user_id),
  CONSTRAINT FOREIGN KEY (user_id) REFERENCES user (id),
  CONSTRAINT FOREIGN KEY (role_id) REFERENCES role (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;