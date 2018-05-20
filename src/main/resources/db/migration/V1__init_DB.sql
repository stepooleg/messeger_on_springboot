CREATE TABLE hibernate_sequence (next_val bigint) engine=MyISAM;
INSERT INTO hibernate_sequence VALUES ( 1 );
INSERT INTO hibernate_sequence VALUES ( 1 );
CREATE TABLE msg_model (ID bigint NOT NULL, filename varchar(255), tag varchar(255), text varchar(255), user_id bigint, PRIMARY KEY (ID)) engine=MyISAM;
CREATE TABLE user_role (user_id bigint NOT NULL, roles varchar(255)) engine=MyISAM;
CREATE TABLE usr (ID bigint NOT NULL, activation_code varchar(255), ACTIVE bit, email varchar(255), password varchar(255), username varchar(255), PRIMARY KEY (ID)) engine=MyISAM;
ALTER TABLE msg_model ADD CONSTRAINT FK34v90eews8u0phcmbnc953x3n FOREIGN KEY (user_id) REFERENCES usr (ID);
ALTER TABLE user_role ADD CONSTRAINT FKfpm8swft53ulq2hl11yplpr5 FOREIGN KEY (user_id) REFERENCES usr (ID);