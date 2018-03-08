drop table user_table;

create table user_table(
	id varchar(128) not null primary key,
	username varchar(32) not null,
	password varchar(32) not null
);