CREATE TABLE categories (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  uuid UUID NOT NULL,
  lang_code varchar(10) NOT NULL,
  name varchar(250) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_uuid (uuid),
  UNIQUE KEY UK_category (lang_code, name)
);
