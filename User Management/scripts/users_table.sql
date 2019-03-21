--------------------------------------------------------
--  File created - Wednesday-March-20-2019   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table USER
--------------------------------------------------------

  CREATE TABLE "TRAININGTRACKING"."USER" 
   (	"ID" NUMBER(10,0), 
	"FIRSTNAME" VARCHAR2(50 BYTE), 
	"LASTNAME" VARCHAR2(50 BYTE), 
	"EMAIL" VARCHAR2(50 BYTE), 
	"TYPE" NUMBER(10,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index USER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "TRAININGTRACKING"."USER_PK" ON "TRAININGTRACKING"."USER" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table USER
--------------------------------------------------------

  ALTER TABLE "TRAININGTRACKING"."USER" ADD CONSTRAINT "USER_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "TRAININGTRACKING"."USER" MODIFY ("TYPE" NOT NULL ENABLE);
  ALTER TABLE "TRAININGTRACKING"."USER" MODIFY ("EMAIL" NOT NULL ENABLE);
  ALTER TABLE "TRAININGTRACKING"."USER" MODIFY ("LASTNAME" NOT NULL ENABLE);
  ALTER TABLE "TRAININGTRACKING"."USER" MODIFY ("FIRSTNAME" NOT NULL ENABLE);
  ALTER TABLE "TRAININGTRACKING"."USER" MODIFY ("ID" NOT NULL ENABLE);
