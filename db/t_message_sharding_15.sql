

-- ----------------------------
-- Table structure for t_message_15
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_message_15";
CREATE TABLE "public"."t_message_15" (
  "id" int8 NOT NULL DEFAULT nextval('t_message_15_id_seq'::regclass),
  "text" varchar(200) COLLATE "pg_catalog"."default",
  "roomid" int8,
  "stamp" int8,
  "mid" varchar(200) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "public"."t_message_15" OWNER TO "postgres";


-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."t_message_15_id_seq"
OWNED BY "public"."t_message_15"."id";
SELECT setval('"public"."t_message_15_id_seq"', 13, true);


-- ----------------------------
-- Indexes structure for table t_message_15
-- ----------------------------
CREATE INDEX "t_message_15_roomid" ON "public"."t_message_15" USING btree (
  "roomid" "pg_catalog"."int8_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table t_message_15
-- ----------------------------
ALTER TABLE "public"."t_message_15" ADD CONSTRAINT "t_message_15_pkey" PRIMARY KEY ("id");
