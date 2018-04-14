
----------------20180410--------------------
--1

insert into as_option (OPT_ID, CO_CODE, COMPO_ID, TRANS_TYPE, OPT_VAL, IS_SYST_OPT)
values ('OPT_SF_FTP_HOST', '*', '*', '*', '10.169.180.148', 'Y');

insert into as_option (OPT_ID, CO_CODE, COMPO_ID, TRANS_TYPE, OPT_VAL, IS_SYST_OPT)
values ('OPT_SF_FTP_PORT', '*', '*', '*', '21', 'Y');

insert into as_option (OPT_ID, CO_CODE, COMPO_ID, TRANS_TYPE, OPT_VAL, IS_SYST_OPT)
values ('OPT_SF_FTP_USER', '*', '*', '*', 'puersf', 'Y');

insert into as_option (OPT_ID, CO_CODE, COMPO_ID, TRANS_TYPE, OPT_VAL, IS_SYST_OPT)
values ('OPT_SF_FTP_PASSWORD', '*', '*', '*', 'puersfftp', 'Y');

insert into as_option (OPT_ID, CO_CODE, COMPO_ID, TRANS_TYPE, OPT_VAL, IS_SYST_OPT)
values ('OPT_SF_FTP_MAX_COLLECTION', '*', '*', '*', '3', 'Y');

insert into as_option (OPT_ID, CO_CODE, COMPO_ID, TRANS_TYPE, OPT_VAL, IS_SYST_OPT)
values ('OPT_SF_FTP_DIR_RECORD', '*', '*', '*', 'records', 'Y');

insert into as_option (OPT_ID, CO_CODE, COMPO_ID, TRANS_TYPE, OPT_VAL, IS_SYST_OPT)
values ('OPT_SF_FTP_USED', '*', '*', '*', 'Y', 'Y');

--2
create sequence SEQ_SF_FTP_RECORD
minvalue 1
maxvalue 999999999999999
start with 1
increment by 2
cache 20;

create sequence SEQ_SF_JD_RESULT_FILE_STORE_ID
minvalue 1
maxvalue 999999999999999
start with 1
increment by 2
cache 20;

--3

CREATE TABLE SF_JD_RESULT_FILE_STORE
(
	SF_JD_RESULT_FILE_STORE_ID INTEGER NOT NULL ,
	JD_RESULT_ID         INTEGER   ,
	ENTRUST_ID         INTEGER   ,
	STORE_TYPE           VARCHAR2(30)  ,
	PATH                 VARCHAR2(30)  ,
 PRIMARY KEY (SF_JD_RESULT_FILE_STORE_ID)
);



COMMENT ON TABLE SF_JD_RESULT_FILE_STORE IS '鉴定过程文件存储';




COMMENT ON COLUMN SF_JD_RESULT_FILE_STORE.SF_JD_RESULT_FILE_STORE_ID IS '鉴定过程文件存储ID';




COMMENT ON COLUMN SF_JD_RESULT_FILE_STORE.STORE_TYPE IS '存储类型';




COMMENT ON COLUMN SF_JD_RESULT_FILE_STORE.PATH IS '存储目录';




COMMENT ON COLUMN SF_JD_RESULT_FILE_STORE.JD_RESULT_ID IS '鉴定结果ID';

COMMENT ON COLUMN SF_JD_RESULT_FILE_STORE.ENTRUST_ID IS '委托ID';

--4

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_JD_RESULT_FILE_STORE', 'JD_RESULT_ID', 0, '鉴定结果ID', '鉴定结果ID', 'NUMBER', 22, null, null, null, 'Y', null, null, 'Y', null, null, 'N', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_JD_RESULT_FILE_STORE', 'PATH', 0, '存储目录', '存储目录', 'VARCHAR2', 30, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_JD_RESULT_FILE_STORE', 'SF_JD_RESULT_FILE_STORE_ID', 0, '鉴定过程文件存储ID', '鉴定过程文件存储ID', 'NUMBER', 22, null, null, null, 'Y', null, null, 'Y', null, null, 'N', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_JD_RESULT_FILE_STORE', 'STORE_TYPE', 0, '存储类型', '存储类型', 'VARCHAR2', 30, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

--5

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_JD_RESULT_FILE_STORE_JD_RESULT_ID', 'C', '鉴定结果ID');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_JD_RESULT_FILE_STORE_PATH', 'C', '存储目录');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_JD_RESULT_FILE_STORE_SF_JD_RESULT_FILE_STORE_ID', 'C', '鉴定过程文件存储ID');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_JD_RESULT_FILE_STORE_STORE_TYPE', 'C', '存储类型');


--6

insert into as_table (TAB_ID, MASTER_TAB_ID, TAB_NA, TAB_DESC, DB_VER_NO, TRAD_VER_NO, LSTDATE, PRE_VALSET, IS_TABLE)
values ('SF_JD_RESULT_FILE_STORE', 'SF_JD_RESULT', '鉴定过程文件存储', '鉴定过程文件存储', null, null, null, null, 'Y');

--7

----------------不知什么时候------------
--1
CREATE OR REPLACE VIEW V_SF_DOC_SEND AS 
SELECT D.ENTRUST_ID,
       MAX(D.RECIEVOR) AS RECIEVOR,
       MAX(D.RECIEVOR_TEL) AS RECIEVOR_TEL,
       MAX(D.SEND_DATE) AS SEND_DATE
  FROM SF_DOC_SEND D
 GROUP BY D.ENTRUST_ID;
 
 CREATE OR REPLACE VIEW V_SF_JD_REPORT AS 
 SELECT R.ENTRUST_ID,MAX(R.REPORT_CODE) AS REPORT_CODE FROM SF_JD_REPORT R GROUP BY R.ENTRUST_ID;
 