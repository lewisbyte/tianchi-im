create table t_message
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

create index t_message_roomid
	on t_message (roomid);

create table t_room
(
	id bigserial not null
		constraint t_room_pkey
			primary key,
	name varchar(200)
);

alter table t_room owner to postgres;

create table t_user
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

create unique index t_user_idx1
	on t_user (username, password);

create unique index t_user_idx2
	on t_user (username, password);

create table t_message_0
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

create index t_message_0_roomid
	on t_message_0 (roomid);

create table t_message_1
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

create index t_message_1_roomid
	on t_message_1 (roomid);

create table t_message_2
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

create index t_message_2_roomid
	on t_message_2 (roomid);

create table t_message_3
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

create index t_message_3_roomid
	on t_message_3 (roomid);

create table t_message_4
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

create index t_message_4_roomid
	on t_message_4 (roomid);

create table t_message_5
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

create index t_message_5_roomid
	on t_message_5 (roomid);

create table t_message_6
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

create index t_message_6_roomid
	on t_message_6 (roomid);

create table t_message_7
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

create index t_message_7_roomid
	on t_message_7 (roomid);

create table t_message_8
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

create index t_message_8_roomid
	on t_message_8 (roomid);

create table t_message_9
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

create index t_message_9_roomid
	on t_message_9 (roomid);

create table t_message_10
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

create index t_message_10_roomid
	on t_message_10 (roomid);

create table t_message_11
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

create index t_message_11_roomid
	on t_message_11 (roomid);

create table t_message_12
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

create index t_message_12_roomid
	on t_message_12 (roomid);

create table t_message_13
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

create index t_message_13_roomid
	on t_message_13 (roomid);

create table t_message_14
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

create index t_message_14_roomid
	on t_message_14 (roomid);

create table t_message_15
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

create index t_message_15_roomid
	on t_message_15 (roomid);

create table t_message_16
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

create index t_message_16_roomid
	on t_message_16 (roomid);

create table t_message_17
(
	id bigserial not null
		constraint t_message_17_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_17 owner to postgres;

create index t_message_17_roomid
	on t_message_17 (roomid);

create table t_message_18
(
	id bigserial not null
		constraint t_message_18_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_18 owner to postgres;

create index t_message_18_roomid
	on t_message_18 (roomid);

create table t_message_19
(
	id bigserial not null
		constraint t_message_19_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_19 owner to postgres;

create index t_message_19_roomid
	on t_message_19 (roomid);

create table t_message_20
(
	id bigserial not null
		constraint t_message_20_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_20 owner to postgres;

create index t_message_20_roomid
	on t_message_20 (roomid);

create table t_message_21
(
	id bigserial not null
		constraint t_message_21_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_21 owner to postgres;

create index t_message_21_roomid
	on t_message_21 (roomid);

create table t_message_22
(
	id bigserial not null
		constraint t_message_22_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_22 owner to postgres;

create index t_message_22_roomid
	on t_message_22 (roomid);

create table t_message_23
(
	id bigserial not null
		constraint t_message_23_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_23 owner to postgres;

create index t_message_23_roomid
	on t_message_23 (roomid);

create table t_message_24
(
	id bigserial not null
		constraint t_message_24_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_24 owner to postgres;

create index t_message_24_roomid
	on t_message_24 (roomid);

create table t_message_25
(
	id bigserial not null
		constraint t_message_25_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_25 owner to postgres;

create index t_message_25_roomid
	on t_message_25 (roomid);

create table t_message_26
(
	id bigserial not null
		constraint t_message_26_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_26 owner to postgres;

create index t_message_26_roomid
	on t_message_26 (roomid);

create table t_message_27
(
	id bigserial not null
		constraint t_message_27_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_27 owner to postgres;

create index t_message_27_roomid
	on t_message_27 (roomid);

create table t_message_28
(
	id bigserial not null
		constraint t_message_28_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_28 owner to postgres;

create index t_message_28_roomid
	on t_message_28 (roomid);

create table t_message_29
(
	id bigserial not null
		constraint t_message_29_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_29 owner to postgres;

create index t_message_29_roomid
	on t_message_29 (roomid);

create table t_message_30
(
	id bigserial not null
		constraint t_message_30_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_30 owner to postgres;

create index t_message_30_roomid
	on t_message_30 (roomid);

create table t_message_31
(
	id bigserial not null
		constraint t_message_31_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_31 owner to postgres;

create index t_message_31_roomid
	on t_message_31 (roomid);

create table t_message_32
(
	id bigserial not null
		constraint t_message_32_pkey
			primary key,
	text varchar(200),
	roomid bigint,
	stamp bigint,
	mid varchar(200)
);

alter table t_message_32 owner to postgres;

create index t_message_32_roomid
	on t_message_32 (roomid);

