

-- ----------------------------
-- Table structure for t_message_0
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_message_0";
CREATE TABLE "public"."t_message_0" (
  "id" int8 NOT NULL DEFAULT nextval('t_message_0_id_seq'::regclass),
  "text" varchar(200) COLLATE "pg_catalog"."default",
  "roomid" int8,
  "stamp" int8,
  "mid" varchar(200) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "public"."t_message_0" OWNER TO "postgres";


-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."t_message_0_id_seq"
OWNED BY "public"."t_message_0"."id";
SELECT setval('"public"."t_message_0_id_seq"', 13, true);


-- ----------------------------
-- Indexes structure for table t_message_0
-- ----------------------------
CREATE INDEX "t_message_0_roomid" ON "public"."t_message_0" USING btree (
  "roomid" "pg_catalog"."int8_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table t_message_0
-- ----------------------------
ALTER TABLE "public"."t_message_0" ADD CONSTRAINT "t_message_0_pkey" PRIMARY KEY ("id");
