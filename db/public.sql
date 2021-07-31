create table if not exists t_message
(
	id bigserial not null
		constraint t_message_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message owner to postgres;

create index if not exists t_message_roomid
	on t_message (roomid);

create table if not exists t_room
(
	id bigserial not null
		constraint t_room_pkey
			primary key,
	name varchar(200)
);

alter table t_room owner to postgres;

create table if not exists t_user
(
	id bigserial not null
		constraint t_user_pkey
			primary key,
	username varchar(200)
		constraint t_user_uk1
			unique,
	first_name varchar(200),
	last_name varchar(200),
	email varchar(200),
	password varchar(200),
	phone varchar(200)
);

alter table t_user owner to postgres;

create unique index if not exists t_user_idx1
	on t_user (username, password);

create unique index if not exists t_user_idx2
	on t_user (username, password);

create table if not exists t_message_0
(
	id bigserial not null
		constraint t_message_0_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_0 owner to postgres;

create index if not exists t_message_0_roomid
	on t_message_0 (roomid);

create table if not exists t_message_1
(
	id bigserial not null
		constraint t_message_1_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_1 owner to postgres;

create index if not exists t_message_1_roomid
	on t_message_1 (roomid);

create table if not exists t_message_2
(
	id bigserial not null
		constraint t_message_2_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_2 owner to postgres;

create index if not exists t_message_2_roomid
	on t_message_2 (roomid);

create table if not exists t_message_3
(
	id bigserial not null
		constraint t_message_3_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_3 owner to postgres;

create index if not exists t_message_3_roomid
	on t_message_3 (roomid);

create table if not exists t_message_4
(
	id bigserial not null
		constraint t_message_4_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_4 owner to postgres;

create index if not exists t_message_4_roomid
	on t_message_4 (roomid);

create table if not exists t_message_5
(
	id bigserial not null
		constraint t_message_5_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_5 owner to postgres;

create index if not exists t_message_5_roomid
	on t_message_5 (roomid);

create table if not exists t_message_6
(
	id bigserial not null
		constraint t_message_6_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_6 owner to postgres;

create index if not exists t_message_6_roomid
	on t_message_6 (roomid);

create table if not exists t_message_7
(
	id bigserial not null
		constraint t_message_7_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_7 owner to postgres;

create index if not exists t_message_7_roomid
	on t_message_7 (roomid);

create table if not exists t_message_8
(
	id bigserial not null
		constraint t_message_8_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_8 owner to postgres;

create index if not exists t_message_8_roomid
	on t_message_8 (roomid);

create table if not exists t_message_9
(
	id bigserial not null
		constraint t_message_9_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_9 owner to postgres;

create index if not exists t_message_9_roomid
	on t_message_9 (roomid);

create table if not exists t_message_10
(
	id bigserial not null
		constraint t_message_10_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_10 owner to postgres;

create index if not exists t_message_10_roomid
	on t_message_10 (roomid);

create table if not exists t_message_11
(
	id bigserial not null
		constraint t_message_11_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_11 owner to postgres;

create index if not exists t_message_11_roomid
	on t_message_11 (roomid);

create table if not exists t_message_12
(
	id bigserial not null
		constraint t_message_12_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_12 owner to postgres;

create index if not exists t_message_12_roomid
	on t_message_12 (roomid);

create table if not exists t_message_13
(
	id bigserial not null
		constraint t_message_13_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_13 owner to postgres;

create index if not exists t_message_13_roomid
	on t_message_13 (roomid);

create table if not exists t_message_14
(
	id bigserial not null
		constraint t_message_14_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_14 owner to postgres;

create index if not exists t_message_14_roomid
	on t_message_14 (roomid);

create table if not exists t_message_15
(
	id bigserial not null
		constraint t_message_15_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_15 owner to postgres;

create index if not exists t_message_15_roomid
	on t_message_15 (roomid);

create table if not exists t_message_16
(
	id bigserial not null
		constraint t_message_16_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_16 owner to postgres;

create index if not exists t_message_16_roomid
	on t_message_16 (roomid);

