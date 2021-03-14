CREATE TABLE category (
  category_id bigint(20) NOT NULL AUTO_INCREMENT,
  uuid UUID NOT NULL,
  lang_code varchar(10) NOT NULL,
  name varchar(255) NOT NULL,
  PRIMARY KEY (category_id),
  UNIQUE KEY UK_category_uuid (uuid),
  UNIQUE KEY UK_category (lang_code, name)
);

CREATE TABLE text (
  text_id bigint(20) NOT NULL AUTO_INCREMENT,
  category_id bigint(20) NOT NULL,
  uuid UUID NOT NULL,
  original varchar(255) NOT NULL,
  normalized varchar(255) NOT NULL,
  PRIMARY KEY (text_id),
  UNIQUE KEY UK_word_uuid (uuid),
  UNIQUE KEY UK_word (category_id, original),
  foreign key (category_id) references category(category_id)
);
