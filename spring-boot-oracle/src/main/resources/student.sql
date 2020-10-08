/*
Navicat Oracle Data Transfer
Oracle Client Version : 10.2.0.5.0

Source Server         : 192.168.147.132_1521_helowin
Source Server Version : 110200
Source Host           : 192.168.147.132:1521
Source Schema         : SYSTEM

Target Server Type    : ORACLE
Target Server Version : 110200
File Encoding         : 65001

Date: 2020-10-08 18:49:15
*/


-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE "SYSTEM"."student";
CREATE TABLE "SYSTEM"."student" (
"id" NUMBER NOT NULL ,
"name" VARCHAR2(111 BYTE) NULL ,
"sex" VARCHAR2(111 BYTE) NULL ,
"age" NUMBER(10) NULL ,
"phone" VARCHAR2(11 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO "SYSTEM"."student" VALUES ('1', 'sam', '男', null, null);
INSERT INTO "SYSTEM"."student" VALUES ('3', 'skdin', '男', '12', '12345678910');
INSERT INTO "SYSTEM"."student" VALUES ('5', 'skdin', '男', '12', '12345678910');
INSERT INTO "SYSTEM"."student" VALUES ('4', 'skdin', '男', '12', '12345678910');

-- ----------------------------
-- Indexes structure for table student
-- ----------------------------

-- ----------------------------
-- Checks structure for table student
-- ----------------------------
ALTER TABLE "SYSTEM"."student" ADD CHECK ("id" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table student
-- ----------------------------
ALTER TABLE "SYSTEM"."student" ADD PRIMARY KEY ("id");
