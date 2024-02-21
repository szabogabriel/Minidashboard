CREATE TABLE master.dbo.configuration_entity (
	config_id bigint NOT NULL,
	conf_key varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	conf_value varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT PK__configur__4AD1BFF12F05A63A PRIMARY KEY (config_id)
);


CREATE TABLE master.dbo.configuration_entity_seq (
	next_val bigint NULL
);


CREATE TABLE master.dbo.data_entry_entity_seq (
	next_val bigint NULL
);

CREATE TABLE master.dbo.domain_entity (
	domain_id bigint NOT NULL,
	name varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT PK__domain_e__E72BC766B5EED05C PRIMARY KEY (domain_id)
);

CREATE TABLE master.dbo.domain_entity_seq (
	next_val bigint NULL
);

CREATE TABLE master.dbo.file_entity (
	file_id bigint NOT NULL,
	binary_content varbinary(MAX) NULL,
	create_timestamp bigint NULL,
	file_name varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	mime_type varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT PK__file_ent__07D884C653E6704D PRIMARY KEY (file_id)
);

CREATE TABLE master.dbo.file_entity_seq (
	next_val bigint NULL
);

CREATE TABLE master.dbo.data_entry_entity (
	data_entry_id bigint NOT NULL,
	category varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	create_timestamp bigint NULL,
	entry varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	last_modified bigint NULL,
	level0 varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	level1 varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	level2 varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	level3 varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	level4 varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	level5 varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	level6 varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	level7 varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	valid_until bigint NULL,
	version bigint NULL,
	domain_id bigint NULL,
	CONSTRAINT PK__data_ent__739DBD6C8702F003 PRIMARY KEY (data_entry_id),
	CONSTRAINT FKpxhr7qsw9k9dc8wx7avqlmils FOREIGN KEY (domain_id) REFERENCES master.dbo.domain_entity(domain_id)
);