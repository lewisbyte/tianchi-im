/*
 Navicat Premium Data Transfer

 Source Server         : pgsql
 Source Server Type    : PostgreSQL
 Source Server Version : 130003
 Source Host           : localhost:5432
 Source Catalog        : postgres
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 130003
 File Encoding         : 65001

 Date: 17/07/2021 18:00:23
*/


-- ----------------------------
-- Sequence structure for t_message_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."t_message_id_seq";
CREATE SEQUENCE "public"."t_message_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER SEQUENCE "public"."t_message_id_seq" OWNER TO "postgres";

-- ----------------------------
-- Sequence structure for t_room_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."t_room_id_seq";
CREATE SEQUENCE "public"."t_room_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER SEQUENCE "public"."t_room_id_seq" OWNER TO "postgres";

-- ----------------------------
-- Sequence structure for t_user_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."t_user_id_seq";
CREATE SEQUENCE "public"."t_user_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER SEQUENCE "public"."t_user_id_seq" OWNER TO "postgres";

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_message";
CREATE TABLE "public"."t_message" (
  "id" int8 NOT NULL DEFAULT nextval('t_message_id_seq'::regclass),
  "text" varchar(200) COLLATE "pg_catalog"."default",
  "roomid" int8,
  "stamp" int8,
  "mid" int8
)
;
ALTER TABLE "public"."t_message" OWNER TO "postgres";

-- ----------------------------
-- Table structure for t_room
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_room";
CREATE TABLE "public"."t_room" (
  "id" int8 NOT NULL DEFAULT nextval('t_room_id_seq'::regclass),
  "name" varchar(200) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "public"."t_room" OWNER TO "postgres";

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_user";
CREATE TABLE "public"."t_user" (
  "id" int8 NOT NULL DEFAULT nextval('t_user_id_seq'::regclass),
  "username" varchar(200) COLLATE "pg_catalog"."default",
  "first_name" varchar(200) COLLATE "pg_catalog"."default",
  "last_name" varchar(200) COLLATE "pg_catalog"."default",
  "email" varchar(200) COLLATE "pg_catalog"."default",
  "password" varchar(200) COLLATE "pg_catalog"."default",
  "phone" varchar(200) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "public"."t_user" OWNER TO "postgres";

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."t_message_id_seq"
OWNED BY "public"."t_message"."id";
SELECT setval('"public"."t_message_id_seq"', 2, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."t_room_id_seq"
OWNED BY "public"."t_room"."id";
SELECT setval('"public"."t_room_id_seq"', 2, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."t_user_id_seq"
OWNED BY "public"."t_user"."id";
SELECT setval('"public"."t_user_id_seq"', 2, false);

-- ----------------------------
-- Primary Key structure for table t_message
-- ----------------------------
ALTER TABLE "public"."t_message" ADD CONSTRAINT "t_message_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_room
-- ----------------------------
ALTER TABLE "public"."t_room" ADD CONSTRAINT "t_room_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_user
-- ----------------------------
ALTER TABLE "public"."t_user" ADD CONSTRAINT "t_user_pkey" PRIMARY KEY ("id");