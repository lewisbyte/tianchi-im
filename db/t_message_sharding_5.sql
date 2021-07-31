

-- ----------------------------
-- Table structure for t_message_5
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_message_5";
CREATE TABLE "public"."t_message_5" (
  "id" int8 NOT NULL DEFAULT nextval('t_message_5_id_seq'::regclass),
  "text" varchar(200) COLLATE "pg_catalog"."default",
  "roomid" int8,
  "stamp" int8,
  "mid" varchar(200) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "public"."t_message_5" OWNER TO "postgres";


-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."t_message_5_id_seq"
OWNED BY "public"."t_message_5"."id";
SELECT setval('"public"."t_message_5_id_seq"', 13, true);


-- ----------------------------
-- Indexes structure for table t_message_5
-- ----------------------------
CREATE INDEX "t_message_5_roomid" ON "public"."t_message_5" USING btree (
  "roomid" "pg_catalog"."int8_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table t_message_5
-- ----------------------------
ALTER TABLE "public"."t_message_5" ADD CONSTRAINT "t_message_5_pkey" PRIMARY KEY ("id");
