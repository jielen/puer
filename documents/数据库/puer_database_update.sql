
----------------20170119--------------------
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

----------------��֪ʲôʱ��------------
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
 
 --2
 
----------------20170119--------------------
--1

UPDATE AS_NO_RULE R SET  NO_PREFIX='��˾��ȷ��['   where COMPO_ID = 'SF_ENTRUST' ;

--2

insert into as_func (FUNC_ID, FUNC_DESC, ORD_INDEX, IS_GRANT_TO_ALL, IS_LIST, SHORTCUT_KEY, IS_CTRL, IS_SHIFT, IS_ALT)
values ('fsubcopy', '����', 765, null, 'Y', null, null, null, null);

--3

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('fsubcopy', 'C', '����');

--4

insert into AS_NO_RULE (COMPO_ID, RULE_CODE, RULE_NAME, NO_PREFIX, NO_INDEX_LEN, IS_CONT, NUM_TOOL_ID, NO_FIELD, IS_INCL_ATOZ, IS_FILL_ZERO, NO_AFTFIX, TRANS_DATE)
values ('SF_JD_REPORT_000', 'SF_JD_REPORT_CODE_GEN_000', '���������Ź���000', '�չ�˾��[', 10, 'Y', 'SF_JD_REPORT_CODE_GEN_ID_000', 'REPORT_CODE', null, 'N', '��', to_date('19-01-2017 12:30:25', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_NUM_TOOL (NUM_TOOL_ID, NUM_TOOL_NAME, IS_CONT, NUM_TOOL_DESC)
values ('SF_JD_REPORT_CODE_GEN_ID_000', '������������', 'Y', '������������');

insert into AS_NO_RULE_SEG (COMPO_ID, RULE_CODE, ORD_INDEX, SEG_FIELD, SEG_SV, SEG_CONST, SEG_LEN, SEG_FILL_POSI, SEG_FILL, SEG_DELI, DATE_FMT, IS_BEFORE_NO)
values ('SF_JD_REPORT_000', 'SF_JD_REPORT_CODE_GEN_000', 1, 'ND', null, null, 4, null, null, ']', null, 'Y');

--5
DELETE FROM zc_role_search_condition WHERE CONDITION_ID='SfEntrust_Tab';

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_WTF', 'SfEntrust_Tab', 'all');

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_WTF', 'SfEntrust_Tab', 'complete');

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_WTF', 'SfEntrust_Tab', 'doing');

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_WTF', 'SfEntrust_Tab', 'wtfTodo');

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_WTF', 'SfEntrust_Tab', 'unaccept');

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_JD_PERSON', 'SfEntrust_Tab', 'todo');

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_JD_PERSON', 'SfEntrust_Tab', 'doing');

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_JD_PERSON', 'SfEntrust_Tab', 'other');

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_JD_PERSON', 'SfEntrust_Tab', 'complete');

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_JD_PERSON', 'SfEntrust_Tab', 'docNoSend');

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_JD_PERSON', 'SfEntrust_Tab', 'docSended');

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_JD_PERSON', 'SfEntrust_Tab', 'all');

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_JDJG', 'SfEntrust_Tab', 'todo');

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_JDJG', 'SfEntrust_Tab', 'doing');

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_JDJG', 'SfEntrust_Tab', 'other');

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_JDJG', 'SfEntrust_Tab', 'complete');

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_JDJG', 'SfEntrust_Tab', 'docNoSend');

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_JDJG', 'SfEntrust_Tab', 'docSended');

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_JDJG', 'SfEntrust_Tab', 'all');

--6
delete FROM ZC_SEARCH_CONDITION S where s.compo_id='SF_ENTRUST';

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_search', 'ND', '���', 2, 'SF_ENTRUST', null, '˾������ί����������', 'condition', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'todo', '������', 10, 'SF_ENTRUST', null, '˾������ί��ҳǩ', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'wtfTodo', '������', 11, 'SF_ENTRUST', null, '˾������ί��ҳǩ', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'untread', '���˻�', 12, 'SF_ENTRUST', null, '˾������ί��ҳǩ', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'doing', '������', 15, 'SF_ENTRUST', null, '˾������ί��ҳǩ', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'other', '����', 22, 'SF_ENTRUST', null, '˾������ί��ҳǩ', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'complete', '�������', 20, 'SF_ENTRUST', null, '˾������ί��ҳǩ', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'pause', '������ͣ', 25, 'SF_ENTRUST', null, '˾������ί��ҳǩ', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'stop', '������ֹ', 30, 'SF_ENTRUST', null, '˾������ί��ҳǩ', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'expire', '����', 35, 'SF_ENTRUST', null, '˾������ί��ҳǩ', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'unaccept', '��������', 40, 'SF_ENTRUST', null, '˾������ί��ҳǩ', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'docNoSend', '��������', 50, 'SF_ENTRUST', null, '˾������ί��ҳǩ', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'docSended', '���������', 55, 'SF_ENTRUST', null, '˾������ί��ҳǩ', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'all', '�鿴ȫ��', 65, 'SF_ENTRUST', null, '˾������ί��ҳǩ', 'tab', '301');

----------------------20161128------------------
--1
-- Add/modify columns 
alter table SF_MATERIALS add tq_info clob;
-- Add comments to the columns 
comment on column SF_MATERIALS.tq_info
  is '�����ȡλ��˵��';

--2
-- Add/modify columns 
alter table SF_MATERIALS modify name VARCHAR2(200);

--3

update as_lang_trans l set l.res_na='��Ƭ������ļ�' where l.res_id like 'SF_MATERIALS_ATTACH_FILE';

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_MATERIALS_TQ_INFO', 'C', '�����ȡλ��˵��');

--4

--------------------20161127--------------------------
--1
create or replace view v_sf_sj_out as
select o.in_id,o.co_code,sum(o.amount) as amount from sf_sj_out o group by o.in_id,o.co_code;

--2

   update as_lang_trans l set l.res_na='ʹ������' where l.res_id='SF_SJ_OUT_AMOUNT';


--4
-- Create sequence 
create sequence SEQ_SF_SJ_GROUP_ID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
cache 2;

--5

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_GROUP', 'CO_CODE', 0, '��������', '��������', 'VARCHAR2', 30, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_GROUP', 'GROUP_ID', 0, '���ID', '���ID', 'NUMBER', 22, null, null, null, 'Y', null, null, 'Y', null, null, 'N', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_GROUP', 'GROUP_NAME', 0, '�������', '�������', 'VARCHAR2', 30, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

--6

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_GROUP_CO_CODE', 'C', '��������');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_GROUP_GROUP_ID', 'C', '���ID');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_GROUP_GROUP_NAME', 'C', '�������');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_GROUP', 'C', '�Լ����');

--7

insert into as_table (TAB_ID, MASTER_TAB_ID, TAB_NA, TAB_DESC, DB_VER_NO, TRAD_VER_NO, LSTDATE, PRE_VALSET, IS_TABLE)
values ('SF_SJ_GROUP', null, '�Լ����', '�Լ����', null, null, null, null, 'Y');

--8

insert into as_compo (COMPO_ID, COMPO_NAME, MASTER_TAB_ID, TRAD_ID, COMPO_TYPE, ICON_NAME, CONDITION, NO_FIELD, TYPE_FIELD, TYPE_TABLE, PARENT_COMPO, PAGE_FIELD, DATE_FIELD, VALSET_FIELD, WF_FLOW_TYPE, WF_LIST_TYPE, DEFAULT_WF_TEMPLATE, TEMPLATE_IS_USED, IS_AUTO_LIST, ORDER_BY, IS_GRANT_TO_ALL, PRINT_TYPE, TITLE_FIELD, TITLE_DATE, WF_BRIEF_FIELDS, IS_FUNC_CONTROL)
values ('SF_SJ_GROUP', '�Լ����', 'SF_SJ_GROUP', null, 'M', null, null, null, null, null, null, null, null, null, null, null, null, 'N', 'Y', null, null, '0', null, null, null, 'Y ');

--9

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_GROUP', 'fdelete', 'Y', null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_GROUP', 'fedit', 'Y', null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_GROUP', 'fexit', null, null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_GROUP', 'fnew', null, null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_GROUP', 'fprint', 'Y', null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_GROUP', 'fprint_preview', null, null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_GROUP', 'fprn_tpl_set', null, null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_GROUP', 'fsave', 'Y', null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_GROUP', 'fwatch', null, null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

--10

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfSjGroup_Tab', 'all', '�鿴ȫ��', 7, 'SF_SJ_GROUP', null, '�Լ����ҳǩ', 'tab', '301');

--11
 
insert into ZC_SYS_BILL_ELEMENT (ELEMENT_ID, ND, BILL_TYPE_CODE, ELEMENT_CODE, ELEMENT_NAME, IS_INCLUDE, IS_INHERIT, INHERIT_ELEMENT_CODE, INHERIT_ELEMENT_NAME, IS_EDIT, IS_RANDOM_EDIT, DEC_FLAG, LEVEL_CTRL, IS_EDIT_PUTIN, IS_EDIT_AUDIT, IS_CONFIG, DEFAULT_VAL_CODE, DEFAULT_VAL_NAME, IS_NULL, PARENT_LEVEL_CTRL, EXTEND_ELEMENT_TYPE, LEVEL_STR, DISPLAY_ORDER_INDEX, GENBALANCE_LEVEL_CTRL)
values ('SF_SJ_GROUP_GROUP_NAME', 9999, 'SF_SJ_GROUP', 'GROUP_NAME', '����', '1', '0', null, null, '1', '1', '0', 0, '0', '0', '1', null, null, '0', 0, '1', '1', 1, 0);

--
-- Create sequence 
create sequence SEQ_SF_SJ_UNIT_ID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
cache 2;

--5

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_UNIT', 'CO_CODE', 0, '��������', '��������', 'VARCHAR2', 30, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_UNIT', 'UNIT_ID', 0, '��λID', '��λID', 'NUMBER', 22, null, null, null, 'Y', null, null, 'Y', null, null, 'N', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_UNIT', 'UNIT_NAME', 0, '����', '����', 'VARCHAR2', 30, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

--6

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_UNIT_CO_CODE', 'C', '��������');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_UNIT_UNIT_ID', 'C', '��λID');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_UNIT_UNIT_NAME', 'C', '����');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_UNIT', 'C', '�Լ���λ');

--7

insert into as_table (TAB_ID, MASTER_TAB_ID, TAB_NA, TAB_DESC, DB_VER_NO, TRAD_VER_NO, LSTDATE, PRE_VALSET, IS_TABLE)
values ('SF_SJ_UNIT', null, '�Լ���λ', '�Լ���λ', null, null, null, null, 'Y');

--8

insert into as_compo (COMPO_ID, COMPO_NAME, MASTER_TAB_ID, TRAD_ID, COMPO_TYPE, ICON_NAME, CONDITION, NO_FIELD, TYPE_FIELD, TYPE_TABLE, PARENT_COMPO, PAGE_FIELD, DATE_FIELD, VALSET_FIELD, WF_FLOW_TYPE, WF_LIST_TYPE, DEFAULT_WF_TEMPLATE, TEMPLATE_IS_USED, IS_AUTO_LIST, ORDER_BY, IS_GRANT_TO_ALL, PRINT_TYPE, TITLE_FIELD, TITLE_DATE, WF_BRIEF_FIELDS, IS_FUNC_CONTROL)
values ('SF_SJ_UNIT', '�Լ���λ', 'SF_SJ_UNIT', null, 'M', null, null, null, null, null, null, null, null, null, null, null, null, 'N', 'Y', null, null, '0', null, null, null, 'Y ');

--9

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_UNIT', 'fdelete', 'Y', null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_UNIT', 'fedit', 'Y', null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_UNIT', 'fexit', null, null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_UNIT', 'fnew', null, null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_UNIT', 'fprint', 'Y', null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_UNIT', 'fprint_preview', null, null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_UNIT', 'fprn_tpl_set', null, null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_UNIT', 'fsave', 'Y', null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_UNIT', 'fwatch', null, null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

--10

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfSjUnit_Tab', 'all', '�鿴ȫ��', 7, 'SF_SJ_UNIT', null, '�Լ���λҳǩ', 'tab', '301');

--11
 
insert into ZC_SYS_BILL_ELEMENT (ELEMENT_ID, ND, BILL_TYPE_CODE, ELEMENT_CODE, ELEMENT_NAME, IS_INCLUDE, IS_INHERIT, INHERIT_ELEMENT_CODE, INHERIT_ELEMENT_NAME, IS_EDIT, IS_RANDOM_EDIT, DEC_FLAG, LEVEL_CTRL, IS_EDIT_PUTIN, IS_EDIT_AUDIT, IS_CONFIG, DEFAULT_VAL_CODE, DEFAULT_VAL_NAME, IS_NULL, PARENT_LEVEL_CTRL, EXTEND_ELEMENT_TYPE, LEVEL_STR, DISPLAY_ORDER_INDEX, GENBALANCE_LEVEL_CTRL)
values ('SF_SJ_UNIT_UNIT_NAME', 9999, 'SF_SJ_UNIT', 'UNIT_NAME', '����', '1', '0', null, null, '1', '1', '0', 0, '0', '0', '1', null, null, '0', 0, '1', '1', 1, 0);

--12

insert into AS_MENU_COMPO (MENU_ID, COMPO_ID, ORD_INDEX, IS_GOTO_EDIT, IS_IN_MENU, IS_ALWAYS_NEW, URL)
values ('SF', 'SF_SJ_OUT', 15, 'N', 'Y', 'N', '/GB/jsp/SF/CommonPage.jsp?className=com.ufgov.zc.client.sf.sjout.SfSjOutListPanel');

insert into AS_MENU_COMPO (MENU_ID, COMPO_ID, ORD_INDEX, IS_GOTO_EDIT, IS_IN_MENU, IS_ALWAYS_NEW, URL)
values ('SF', 'SF_SJ_GROUP', 35, 'N', 'Y', 'N', '/GB/jsp/SF/CommonPage.jsp?className=com.ufgov.zc.client.sf.sjgroup.SfSjGroupListPanel');

insert into AS_MENU_COMPO (MENU_ID, COMPO_ID, ORD_INDEX, IS_GOTO_EDIT, IS_IN_MENU, IS_ALWAYS_NEW, URL)
values ('SF', 'SF_SJ_UNIT', 35, 'N', 'Y', 'N', '/GB/jsp/SF/CommonPage.jsp?className=com.ufgov.zc.client.sf.sjunit.SfSjUnitListPanel');

--13

DELETE from as_role_func r where r.compo_id like 'SF_SJ%';

--14

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_IN', 'fdelete', to_date('20-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_IN', 'fedit', to_date('21-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_IN', 'fexit', to_date('22-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_IN', 'fnew', to_date('23-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_IN', 'fprint', to_date('24-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_IN', 'fprint_preview', to_date('25-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_IN', 'fprn_tpl_set', to_date('26-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_IN', 'fsave', to_date('27-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_IN', 'fwatch', to_date('28-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_IN', 'fdelete', to_date('20-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_IN', 'fedit', to_date('21-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_IN', 'fexit', to_date('22-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_IN', 'fnew', to_date('23-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_IN', 'fprint', to_date('24-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_IN', 'fprint_preview', to_date('25-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_IN', 'fprn_tpl_set', to_date('26-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_IN', 'fsave', to_date('27-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_IN', 'fwatch', to_date('28-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ', 'fdelete', to_date('20-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ', 'fedit', to_date('21-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ', 'fexit', to_date('22-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ', 'fnew', to_date('23-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ', 'fprint', to_date('24-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ', 'fprint_preview', to_date('25-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ', 'fprn_tpl_set', to_date('26-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ', 'fsave', to_date('27-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ', 'fwatch', to_date('28-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ', 'fdelete', to_date('20-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ', 'fedit', to_date('21-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ', 'fexit', to_date('22-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ', 'fnew', to_date('23-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ', 'fprint', to_date('24-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ', 'fprint_preview', to_date('25-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ', 'fprn_tpl_set', to_date('26-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ', 'fsave', to_date('27-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ', 'fwatch', to_date('28-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_OUT', 'fdelete', to_date('20-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_OUT', 'fedit', to_date('21-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_OUT', 'fexit', to_date('22-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_OUT', 'fnew', to_date('23-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_OUT', 'fprint', to_date('24-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_OUT', 'fprint_preview', to_date('25-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_OUT', 'fprn_tpl_set', to_date('26-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_OUT', 'fsave', to_date('27-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_OUT', 'fwatch', to_date('28-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_OUT', 'fdelete', to_date('20-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_OUT', 'fedit', to_date('21-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_OUT', 'fexit', to_date('22-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_OUT', 'fnew', to_date('23-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_OUT', 'fprint', to_date('24-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_OUT', 'fprint_preview', to_date('25-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_OUT', 'fprn_tpl_set', to_date('26-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_OUT', 'fsave', to_date('27-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_OUT', 'fwatch', to_date('28-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_UNIT', 'fdelete', to_date('20-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_UNIT', 'fedit', to_date('21-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_UNIT', 'fexit', to_date('22-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_UNIT', 'fnew', to_date('23-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_UNIT', 'fprint', to_date('24-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_UNIT', 'fprint_preview', to_date('25-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_UNIT', 'fprn_tpl_set', to_date('26-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_UNIT', 'fsave', to_date('27-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_UNIT', 'fwatch', to_date('28-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_UNIT', 'fdelete', to_date('20-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_UNIT', 'fedit', to_date('21-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_UNIT', 'fexit', to_date('22-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_UNIT', 'fnew', to_date('23-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_UNIT', 'fprint', to_date('24-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_UNIT', 'fprint_preview', to_date('25-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_UNIT', 'fprn_tpl_set', to_date('26-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_UNIT', 'fsave', to_date('27-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_UNIT', 'fwatch', to_date('28-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_GROUP', 'fdelete', to_date('20-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_GROUP', 'fedit', to_date('21-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_GROUP', 'fexit', to_date('22-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_GROUP', 'fnew', to_date('23-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_GROUP', 'fprint', to_date('24-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_GROUP', 'fprint_preview', to_date('25-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_GROUP', 'fprn_tpl_set', to_date('26-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_GROUP', 'fsave', to_date('27-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_GROUP', 'fwatch', to_date('28-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_GROUP', 'fdelete', to_date('20-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_GROUP', 'fedit', to_date('21-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_GROUP', 'fexit', to_date('22-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_GROUP', 'fnew', to_date('23-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_GROUP', 'fprint', to_date('24-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_GROUP', 'fprint_preview', to_date('25-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_GROUP', 'fprn_tpl_set', to_date('26-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_GROUP', 'fsave', to_date('27-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_GROUP', 'fwatch', to_date('28-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_SUPPLIER', 'fdelete', to_date('20-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_SUPPLIER', 'fedit', to_date('21-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_SUPPLIER', 'fexit', to_date('22-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_SUPPLIER', 'fnew', to_date('23-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_SUPPLIER', 'fprint', to_date('24-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_SUPPLIER', 'fprint_preview', to_date('25-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_SUPPLIER', 'fprn_tpl_set', to_date('26-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_SUPPLIER', 'fsave', to_date('27-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_SUPPLIER', 'fwatch', to_date('28-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_SUPPLIER', 'fdelete', to_date('20-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_SUPPLIER', 'fedit', to_date('21-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_SUPPLIER', 'fexit', to_date('22-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_SUPPLIER', 'fnew', to_date('23-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_SUPPLIER', 'fprint', to_date('24-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_SUPPLIER', 'fprint_preview', to_date('25-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_SUPPLIER', 'fprn_tpl_set', to_date('26-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_SUPPLIER', 'fsave', to_date('27-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_SUPPLIER', 'fwatch', to_date('28-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_PRODUCTOR', 'fdelete', to_date('20-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_PRODUCTOR', 'fedit', to_date('21-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_PRODUCTOR', 'fexit', to_date('22-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_PRODUCTOR', 'fnew', to_date('23-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_PRODUCTOR', 'fprint', to_date('24-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_PRODUCTOR', 'fprint_preview', to_date('25-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_PRODUCTOR', 'fprn_tpl_set', to_date('26-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_PRODUCTOR', 'fsave', to_date('27-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_PRODUCTOR', 'fwatch', to_date('28-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_PRODUCTOR', 'fdelete', to_date('20-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_PRODUCTOR', 'fedit', to_date('21-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_PRODUCTOR', 'fexit', to_date('22-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_PRODUCTOR', 'fnew', to_date('23-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_PRODUCTOR', 'fprint', to_date('24-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_PRODUCTOR', 'fprint_preview', to_date('25-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_PRODUCTOR', 'fprn_tpl_set', to_date('26-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_PRODUCTOR', 'fsave', to_date('27-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_PRODUCTOR', 'fwatch', to_date('28-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));



--18

insert into as_val (VALSET_ID, VAL_ID, VAL, ORD_INDEX, LSTDATE, IS_SYSTEM, TRANS_DATE)
values ('SF_VS_ENTRUSTOR_DISTRICT', 'puer', '�ն���', 1, null, 'Y', to_date('26-11-2016 16:33:08', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_val (VALSET_ID, VAL_ID, VAL, ORD_INDEX, LSTDATE, IS_SYSTEM, TRANS_DATE)
values ('SF_VS_ENTRUSTOR_DISTRICT', 'simao', '˼é', 2, null, 'Y', to_date('26-11-2016 16:33:08', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_val (VALSET_ID, VAL_ID, VAL, ORD_INDEX, LSTDATE, IS_SYSTEM, TRANS_DATE)
values ('SF_VS_ENTRUSTOR_DISTRICT', 'mojiang', 'ī��', 3, null, 'Y', to_date('26-11-2016 16:33:08', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_val (VALSET_ID, VAL_ID, VAL, ORD_INDEX, LSTDATE, IS_SYSTEM, TRANS_DATE)
values ('SF_VS_ENTRUSTOR_DISTRICT', 'jingdong', '����', 4, null, 'Y', to_date('26-11-2016 16:33:08', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_val (VALSET_ID, VAL_ID, VAL, ORD_INDEX, LSTDATE, IS_SYSTEM, TRANS_DATE)
values ('SF_VS_ENTRUSTOR_DISTRICT', 'jinggu', '����', 5, null, 'Y', to_date('26-11-2016 16:33:08', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_val (VALSET_ID, VAL_ID, VAL, ORD_INDEX, LSTDATE, IS_SYSTEM, TRANS_DATE)
values ('SF_VS_ENTRUSTOR_DISTRICT', 'zhenyuan', '����', 6, null, 'Y', to_date('26-11-2016 16:33:08', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_val (VALSET_ID, VAL_ID, VAL, ORD_INDEX, LSTDATE, IS_SYSTEM, TRANS_DATE)
values ('SF_VS_ENTRUSTOR_DISTRICT', 'jiangcheng', '����', 7, null, 'Y', to_date('26-11-2016 16:33:08', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_val (VALSET_ID, VAL_ID, VAL, ORD_INDEX, LSTDATE, IS_SYSTEM, TRANS_DATE)
values ('SF_VS_ENTRUSTOR_DISTRICT', 'menglian', '����', 8, null, 'Y', to_date('26-11-2016 16:33:08', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_val (VALSET_ID, VAL_ID, VAL, ORD_INDEX, LSTDATE, IS_SYSTEM, TRANS_DATE)
values ('SF_VS_ENTRUSTOR_DISTRICT', 'lancang', '����', 9, null, 'Y', to_date('26-11-2016 16:33:08', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_val (VALSET_ID, VAL_ID, VAL, ORD_INDEX, LSTDATE, IS_SYSTEM, TRANS_DATE)
values ('SF_VS_ENTRUSTOR_DISTRICT', 'ximeng', '����', 10, null, 'Y', to_date('26-11-2016 16:33:08', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_val (VALSET_ID, VAL_ID, VAL, ORD_INDEX, LSTDATE, IS_SYSTEM, TRANS_DATE)
values ('SF_VS_ENTRUSTOR_DISTRICT', 'qita', '����', 99, null, 'Y', to_date('26-11-2016 16:33:08', 'dd-mm-yyyy hh24:mi:ss'));

--19

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_ENTRUSTOR_DISTRICT_CODE', 'C', '����');

--20
delete from ZC_SYS_BILL_ELEMENT e where e.bill_type_code like 'SF_ENTRUSTOR%';


insert into ZC_SYS_BILL_ELEMENT (ELEMENT_ID, ND, BILL_TYPE_CODE, ELEMENT_CODE, ELEMENT_NAME, IS_INCLUDE, IS_INHERIT, INHERIT_ELEMENT_CODE, INHERIT_ELEMENT_NAME, IS_EDIT, IS_RANDOM_EDIT, DEC_FLAG, LEVEL_CTRL, IS_EDIT_PUTIN, IS_EDIT_AUDIT, IS_CONFIG, DEFAULT_VAL_CODE, DEFAULT_VAL_NAME, IS_NULL, PARENT_LEVEL_CTRL, EXTEND_ELEMENT_TYPE, LEVEL_STR, DISPLAY_ORDER_INDEX, GENBALANCE_LEVEL_CTRL)
values ('SF_ENTRUSTOR_NAME', 9999, 'SF_ENTRUSTOR', 'NAME', 'ί�з�����', '1', '0', null, null, '1', '1', '0', 0, '0', '0', '1', null, null, '0', 0, '1', '1', 1, 0);

insert into ZC_SYS_BILL_ELEMENT (ELEMENT_ID, ND, BILL_TYPE_CODE, ELEMENT_CODE, ELEMENT_NAME, IS_INCLUDE, IS_INHERIT, INHERIT_ELEMENT_CODE, INHERIT_ELEMENT_NAME, IS_EDIT, IS_RANDOM_EDIT, DEC_FLAG, LEVEL_CTRL, IS_EDIT_PUTIN, IS_EDIT_AUDIT, IS_CONFIG, DEFAULT_VAL_CODE, DEFAULT_VAL_NAME, IS_NULL, PARENT_LEVEL_CTRL, EXTEND_ELEMENT_TYPE, LEVEL_STR, DISPLAY_ORDER_INDEX, GENBALANCE_LEVEL_CTRL)
values ('SF_ENTRUSTOR_TYPE', 9999, 'SF_ENTRUSTOR', 'ENTRUSTOR_TYPE', 'ί�з����', '1', '0', null, null, '1', '1', '0', 0, '0', '0', '1', null, null, '0', 0, '1', '1', 1, 0);

insert into ZC_SYS_BILL_ELEMENT (ELEMENT_ID, ND, BILL_TYPE_CODE, ELEMENT_CODE, ELEMENT_NAME, IS_INCLUDE, IS_INHERIT, INHERIT_ELEMENT_CODE, INHERIT_ELEMENT_NAME, IS_EDIT, IS_RANDOM_EDIT, DEC_FLAG, LEVEL_CTRL, IS_EDIT_PUTIN, IS_EDIT_AUDIT, IS_CONFIG, DEFAULT_VAL_CODE, DEFAULT_VAL_NAME, IS_NULL, PARENT_LEVEL_CTRL, EXTEND_ELEMENT_TYPE, LEVEL_STR, DISPLAY_ORDER_INDEX, GENBALANCE_LEVEL_CTRL)
values ('SF_ENTRUSTOR_DISTRICT_CODE', 9999, 'SF_ENTRUSTOR', 'DISTRICT_CODE', '����', '1', '0', null, null, '1', '1', '0', 0, '0', '0', '1', null, null, '0', 0, '1', '1', 1, 0);


--22
delete FROM ZC_SEARCH_CONDITION S where s.compo_id='SF_ENTRUSTOR' ;


insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SF_ENTRUSTOR_TAB_ID', 'all', 'ȫ��', 99, 'SF_ENTRUSTOR', null, 'ί�з�ҳǩ', 'tab', '0');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SF_ENTRUSTOR_TAB_ID', 'puer', '�ն�', 1, 'SF_ENTRUSTOR', null, 'ί�з�ҳǩ', 'tab', '0');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SF_ENTRUSTOR_TAB_ID', 'simao', '˼é', 2, 'SF_ENTRUSTOR', null, 'ί�з�ҳǩ', 'tab', '0');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SF_ENTRUSTOR_TAB_ID', 'mojiang', 'ī��', 3, 'SF_ENTRUSTOR', null, 'ί�з�ҳǩ', 'tab', '0');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SF_ENTRUSTOR_TAB_ID', 'jingdong', '����', 4, 'SF_ENTRUSTOR', null, 'ί�з�ҳǩ', 'tab', '0');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SF_ENTRUSTOR_TAB_ID', 'jinggu', '����', 5, 'SF_ENTRUSTOR', null, 'ί�з�ҳǩ', 'tab', '0');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SF_ENTRUSTOR_TAB_ID', 'zhenyuan', '����', 6, 'SF_ENTRUSTOR', null, 'ί�з�ҳǩ', 'tab', '0');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SF_ENTRUSTOR_TAB_ID', 'jiangcheng', '����', 7, 'SF_ENTRUSTOR', null, 'ί�з�ҳǩ', 'tab', '0');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SF_ENTRUSTOR_TAB_ID', 'menglian', '����', 8, 'SF_ENTRUSTOR', null, 'ί�з�ҳǩ', 'tab', '0');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SF_ENTRUSTOR_TAB_ID', 'lancang', '����', 9, 'SF_ENTRUSTOR', null, 'ί�з�ҳǩ', 'tab', '0');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SF_ENTRUSTOR_TAB_ID', 'ximeng', '����', 10, 'SF_ENTRUSTOR', null, 'ί�з�ҳǩ', 'tab', '0');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SF_ENTRUSTOR_TAB_ID', 'qita', '����', 11, 'SF_ENTRUSTOR', null, 'ί�з�ҳǩ', 'tab', '0');

--

INSERT INTO 	
		  (user_id,user_name,passwd)
		VALUES
		  (#userId:VARCHAR#,#userName:VARCHAR#,#password:VARCHAR#)
		  
		  
		  INSERT INTO AS_EMP
		  (EMP_CODE,EMP_NAME,USER_ID,IS_LOGIN,CA_SERIAL)
		VALUES
		  (#empCode:VARCHAR#,#empName:VARCHAR#,#userId:VARCHAR#,#isLogin#,#caSerial:VARCHAR#)
		  
		  
		  INSERT INTO AS_EMP_POSITION
		  (EMP_CODE,POSI_CODE,ORG_CODE,CO_CODE,EMP_POSI_ID,ND)
		VALUES
		  (#empCode:VARCHAR#,#posiCode:VARCHAR#,#orgCode:VARCHAR#,#coCode:VARCHAR#,#empPosiId:VARCHAR#,#nd:VARCHAR#)
		  
		  
		  
		  INSERT INTO AS_USER_GROUP
		  (GROUP_ID,USER_ID)
		VALUES
		  (#groupId:VARCHAR#,#userId:VARCHAR#)
	
		  
--------------20161121-------------
--1

insert into ZC_SYS_BILL_ELEMENT (ELEMENT_ID, ND, BILL_TYPE_CODE, ELEMENT_CODE, ELEMENT_NAME, IS_INCLUDE, IS_INHERIT, INHERIT_ELEMENT_CODE, INHERIT_ELEMENT_NAME, IS_EDIT, IS_RANDOM_EDIT, DEC_FLAG, LEVEL_CTRL, IS_EDIT_PUTIN, IS_EDIT_AUDIT, IS_CONFIG, DEFAULT_VAL_CODE, DEFAULT_VAL_NAME, IS_NULL, PARENT_LEVEL_CTRL, EXTEND_ELEMENT_TYPE, LEVEL_STR, DISPLAY_ORDER_INDEX, GENBALANCE_LEVEL_CTRL)
values ('SF_ENTRUST_ENTRUSTOR_NAME', 9999, 'SF_ENTRUST', 'ENTRUSTOR_NAME', 'ί�м�����λ', '1', '0', null, null, '1', '1', '0', 0, '0', '0', '1', null, null, '0', 0, '1', '1', 1, 0);

--2

select * from as_file f where f.file_id='sf_entrust_template' for update;
--����ί����-����2 - �﷨.xml
select * from as_file f where f.file_id='sf_entrust_confirm_template' for update;
--����������ȷ���顷-���� - �﷨.xml
select * from as_file f where f.file_id='sf_entrust_confirm_xy_template' for update;
--����������ȷ����-Э�顷-���� - �﷨.xml

--3
-- Add/modify columns 
alter table SF_SJ add sj_group varchar2(10);
-- Add comments to the columns 
comment on column SF_SJ.sj_group
  is '�Լ����';
--4

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ', 'SJ_GROUP', 0, '�Լ����', '�Լ����', 'VARCHAR2', 30, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', 'N', 'Y', 'Y', null, null, null, 'N', null, null, null, null, null, null, null, 'N', 'N', null, null);

--5

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_SJ_GROUP', 'C', '�Լ����');

--6

insert into as_val (VALSET_ID, VAL_ID, VAL, ORD_INDEX, LSTDATE, IS_SYSTEM, TRANS_DATE)
values ('SF_VS_SJ_GROUP', '1', '��', 1, null, 'Y', to_date('16-11-2016 15:10:14', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_val (VALSET_ID, VAL_ID, VAL, ORD_INDEX, LSTDATE, IS_SYSTEM, TRANS_DATE)
values ('SF_VS_SJ_GROUP', '2', '�ۼ�', 2, null, 'Y', to_date('16-11-2016 15:10:14', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_val (VALSET_ID, VAL_ID, VAL, ORD_INDEX, LSTDATE, IS_SYSTEM, TRANS_DATE)
values ('SF_VS_SJ_GROUP', '3', '��ҽ', 3, null, 'Y', to_date('16-11-2016 15:10:14', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_val (VALSET_ID, VAL_ID, VAL, ORD_INDEX, LSTDATE, IS_SYSTEM, TRANS_DATE)
values ('SF_VS_SJ_GROUP', '4', 'DNA', 4, null, 'Y', to_date('16-11-2016 15:10:14', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_val (VALSET_ID, VAL_ID, VAL, ORD_INDEX, LSTDATE, IS_SYSTEM, TRANS_DATE)
values ('SF_VS_SJ_GROUP', '99', '����', 99, null, 'Y', to_date('16-11-2016 15:10:14', 'dd-mm-yyyy hh24:mi:ss'));

--7
-- Add/modify columns 
alter table SF_SJ_PRODUCTOR add co_code VARCHAR2(20);
-- Add comments to the columns 
comment on column SF_SJ_PRODUCTOR.co_code
  is '��������';
 
 --8
 -- Add/modify columns 
alter table SF_SJ_SUPPLIER add co_code VARCHAR2(20);
-- Add comments to the columns 
comment on column SF_SJ_SUPPLIER.co_code
  is '��������';

--9

insert into as_role_num_lim (ROLE_ID, FUNC_ID, COMPO_ID, CTRL_FIELD, GRAN_RANGE, REVO_RANGE, IS_GRAN, IS_RELATION)
values ('R_SF_JYJD', 'fwatch', 'SF_SJ_SUPPLIER', 'CO_CODE', '= ''@@svCoCode''', null, '0', 'N');

insert into as_role_num_lim (ROLE_ID, FUNC_ID, COMPO_ID, CTRL_FIELD, GRAN_RANGE, REVO_RANGE, IS_GRAN, IS_RELATION)
values ('R_SF_JDJG', 'fwatch', 'SF_SJ_SUPPLIER', 'CO_CODE', '= ''@@svCoCode''', null, '0', 'N');

--10

update as_lang_trans l set l.res_na='������' where l.res_id='SF_SJ_PRODUCTOR_ID' ;

--11

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_IN', 'C', '�Լ����');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_IN_AMOUNT', 'C', '����');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_IN_BUY_CODE', 'C', '�ɹ�����');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_IN_CO_CODE', 'C', '��������');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_IN_EXPIRY_DATE', 'C', 'ʧЧ����');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_IN_INPUTOR', 'C', '�����');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_IN_INVOICE_CODE', 'C', '��Ʊ��');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_IN_IN_BILL_CODE', 'C', '��ⵥ��');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_IN_IN_DATE', 'C', '���ʱ��');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_IN_IN_ID', 'C', '��ⵥID');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_IN_ND', 'C', '���');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_IN_PRICE', 'C', '����');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_IN_SHIJI_PIHAO', 'C', '�Լ�����');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_IN_SJ_ID', 'C', '�Լ�ID');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_IN_STORE_CODE', 'C', '�洢λ��ID');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_IN_SUPPLIER_ID', 'C', '������ID');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_IN_TOTAL_NUM', 'C', '�ܼ�');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_IN_UNIT_CODE', 'C', '��λ');

--12

insert into as_table (TAB_ID, MASTER_TAB_ID, TAB_NA, TAB_DESC, DB_VER_NO, TRAD_VER_NO, LSTDATE, PRE_VALSET, IS_TABLE)
values ('SF_SJ_IN', null, '�Լ����', '�Լ����', null, null, null, null, 'Y');

--13

insert into as_compo (COMPO_ID, COMPO_NAME, MASTER_TAB_ID, TRAD_ID, COMPO_TYPE, ICON_NAME, CONDITION, NO_FIELD, TYPE_FIELD, TYPE_TABLE, PARENT_COMPO, PAGE_FIELD, DATE_FIELD, VALSET_FIELD, WF_FLOW_TYPE, WF_LIST_TYPE, DEFAULT_WF_TEMPLATE, TEMPLATE_IS_USED, IS_AUTO_LIST, ORDER_BY, IS_GRANT_TO_ALL, PRINT_TYPE, TITLE_FIELD, TITLE_DATE, WF_BRIEF_FIELDS, IS_FUNC_CONTROL)
values ('SF_SJ_IN', '�Լ����', 'SF_SJ_IN', null, 'M', null, null, null, null, null, null, null, null, null, null, null, null, 'N', 'Y', 'IN_ID', null, '0', null, null, null, 'Y ');

--14

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_IN', 'fdelete', 'Y', null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_IN', 'fedit', 'Y', null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_IN', 'fexit', null, null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_IN', 'fnew', null, null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_IN', 'fprint', 'Y', null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_IN', 'fprint_preview', null, null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_IN', 'fprn_tpl_set', null, null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_IN', 'fsave', 'Y', null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_IN', 'fwatch', null, null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

--15

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfSjIn_Tab', 'all', '�鿴ȫ��', 7, 'SF_SJ_IN', null, '�Լ����ҳǩ', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfSjIn_search', 'ND', '���', 2, 'SF_SJ_IN', null, '�Լ������������', 'condition', '1');

--16

insert into ZC_SYS_BILL_ELEMENT (ELEMENT_ID, ND, BILL_TYPE_CODE, ELEMENT_CODE, ELEMENT_NAME, IS_INCLUDE, IS_INHERIT, INHERIT_ELEMENT_CODE, INHERIT_ELEMENT_NAME, IS_EDIT, IS_RANDOM_EDIT, DEC_FLAG, LEVEL_CTRL, IS_EDIT_PUTIN, IS_EDIT_AUDIT, IS_CONFIG, DEFAULT_VAL_CODE, DEFAULT_VAL_NAME, IS_NULL, PARENT_LEVEL_CTRL, EXTEND_ELEMENT_TYPE, LEVEL_STR, DISPLAY_ORDER_INDEX, GENBALANCE_LEVEL_CTRL)
values ('SF_SJ_IN_AMOUNT', 9999, 'SF_SJ_IN', 'AMOUNT', '����', '1', '0', null, null, '1', '1', '0', 0, '0', '0', '1', null, null, '0', 0, '1', '1', 1, 0);

insert into ZC_SYS_BILL_ELEMENT (ELEMENT_ID, ND, BILL_TYPE_CODE, ELEMENT_CODE, ELEMENT_NAME, IS_INCLUDE, IS_INHERIT, INHERIT_ELEMENT_CODE, INHERIT_ELEMENT_NAME, IS_EDIT, IS_RANDOM_EDIT, DEC_FLAG, LEVEL_CTRL, IS_EDIT_PUTIN, IS_EDIT_AUDIT, IS_CONFIG, DEFAULT_VAL_CODE, DEFAULT_VAL_NAME, IS_NULL, PARENT_LEVEL_CTRL, EXTEND_ELEMENT_TYPE, LEVEL_STR, DISPLAY_ORDER_INDEX, GENBALANCE_LEVEL_CTRL)
values ('SF_SJ_IN_SJ_ID', 9999, 'SF_SJ_IN', 'SJ_ID', '�Լ�ID', '1', '0', null, null, '1', '1', '0', 0, '0', '0', '1', null, null, '0', 0, '1', '1', 1, 0);

--17
--����seq SEQ_SF_SF_SJ_IN_ID

--18

   update as_lang_trans l set l.res_na='������' where l.res_id='SF_SJ_IN_SUPPLIER_ID';
   
--19
-- Add/modify columns 
alter table SF_SJ_IN add remark VARCHAR2(200);
-- Add comments to the columns 
comment on column SF_SJ_IN.remark
  is '��ע';
  
--20

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_IN', 'REMARK', 0, '��ע', '��ע', 'VARCHAR2', 100, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', 'N', 'Y', 'Y', null, null, null, 'N', null, null, null, null, null, null, null, 'N', 'N', null, null);

--21

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_IN_REMARK', 'C', '��ע');

--22

insert into ZC_SYS_BILL_ELEMENT (ELEMENT_ID, ND, BILL_TYPE_CODE, ELEMENT_CODE, ELEMENT_NAME, IS_INCLUDE, IS_INHERIT, INHERIT_ELEMENT_CODE, INHERIT_ELEMENT_NAME, IS_EDIT, IS_RANDOM_EDIT, DEC_FLAG, LEVEL_CTRL, IS_EDIT_PUTIN, IS_EDIT_AUDIT, IS_CONFIG, DEFAULT_VAL_CODE, DEFAULT_VAL_NAME, IS_NULL, PARENT_LEVEL_CTRL, EXTEND_ELEMENT_TYPE, LEVEL_STR, DISPLAY_ORDER_INDEX, GENBALANCE_LEVEL_CTRL)
values ('SF_SJ_IN_AMOUNT', 9999, 'SF_SJ_IN', 'AMOUNT', '����', '1', '0', null, null, '1', '1', '0', 0, '0', '0', '1', null, null, '0', 0, '1', '1', 1, 0);

insert into ZC_SYS_BILL_ELEMENT (ELEMENT_ID, ND, BILL_TYPE_CODE, ELEMENT_CODE, ELEMENT_NAME, IS_INCLUDE, IS_INHERIT, INHERIT_ELEMENT_CODE, INHERIT_ELEMENT_NAME, IS_EDIT, IS_RANDOM_EDIT, DEC_FLAG, LEVEL_CTRL, IS_EDIT_PUTIN, IS_EDIT_AUDIT, IS_CONFIG, DEFAULT_VAL_CODE, DEFAULT_VAL_NAME, IS_NULL, PARENT_LEVEL_CTRL, EXTEND_ELEMENT_TYPE, LEVEL_STR, DISPLAY_ORDER_INDEX, GENBALANCE_LEVEL_CTRL)
values ('SF_SJ_IN_SJ_NAME', 9999, 'SF_SJ_IN', 'SJ_NAME', '�Լ�����', '1', '0', null, null, '1', '1', '0', 0, '0', '0', '1', null, null, '0', 0, '1', '1', 1, 0);

--23

insert into AS_MENU_COMPO (MENU_ID, COMPO_ID, ORD_INDEX, IS_GOTO_EDIT, IS_IN_MENU, IS_ALWAYS_NEW, URL)
values ('SF', 'SF_SJ_IN', 15, 'N', 'Y', 'N', '/GB/jsp/SF/CommonPage.jsp?className=com.ufgov.zc.client.sf.sjin.SfSjInListPanel');

--24

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_IN', 'fdelete', to_date('20-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_IN', 'fedit', to_date('21-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_IN', 'fexit', to_date('22-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_IN', 'fnew', to_date('23-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_IN', 'fprint', to_date('24-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_IN', 'fprint_preview', to_date('25-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_IN', 'fprn_tpl_set', to_date('26-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_IN', 'fsave', to_date('27-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_ZR', 'SF_SJ_IN', 'fwatch', to_date('28-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_IN', 'fdelete', to_date('20-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_IN', 'fedit', to_date('21-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_IN', 'fexit', to_date('22-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_IN', 'fnew', to_date('23-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_IN', 'fprint', to_date('24-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_IN', 'fprint_preview', to_date('25-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_IN', 'fprn_tpl_set', to_date('26-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_IN', 'fsave', to_date('27-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_role_func (ROLE_ID, COMPO_ID, FUNC_ID, TRANS_DATE)
values ('R_SF_JYJD', 'SF_SJ_IN', 'fwatch', to_date('28-08-2010 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));

--25

insert into as_role_num_lim (ROLE_ID, FUNC_ID, COMPO_ID, CTRL_FIELD, GRAN_RANGE, REVO_RANGE, IS_GRAN, IS_RELATION)
values ('R_SF_JYJD', 'fwatch', 'SF_SJ_IN', 'SF_SJ_IN.CO_CODE', '= ''@@svCoCode''', null, '0', 'N');

insert into as_role_num_lim (ROLE_ID, FUNC_ID, COMPO_ID, CTRL_FIELD, GRAN_RANGE, REVO_RANGE, IS_GRAN, IS_RELATION)
values ('R_SF_ZR', 'fwatch', 'SF_SJ_IN', 'SF_SJ_IN.CO_CODE', '= ''@@svCoCode''', null, '0', 'N');

--26

insert into as_role_num_lim (ROLE_ID, FUNC_ID, COMPO_ID, CTRL_FIELD, GRAN_RANGE, REVO_RANGE, IS_GRAN, IS_RELATION)
values ('R_SF_JYJD', 'fwatch', 'SF_SJ', 'SF_SJ.CO_CODE', '= ''@@svCoCode''', null, '0', 'N');

insert into as_role_num_lim (ROLE_ID, FUNC_ID, COMPO_ID, CTRL_FIELD, GRAN_RANGE, REVO_RANGE, IS_GRAN, IS_RELATION)
values ('R_SF_ZR', 'fwatch', 'SF_SJ', 'SF_SJ.CO_CODE', '= ''@@svCoCode''', null, '0', 'N');

--27

drop table sf_sj_in;

-- Create table

create table SF_SJ_IN
(
  in_id        INTEGER not null,
  sj_id        INTEGER,
  shiji_pihao  VARCHAR2(100),
  amount       INTEGER,
  price        NUMBER,
  expiry_date  DATE,
  store_code   VARCHAR2(100),
  in_bill_code VARCHAR2(100),
  in_date      DATE,
  inputor      VARCHAR2(30),
  invoice_code VARCHAR2(100),
  buy_code     VARCHAR2(30),
  nd           INTEGER,
  co_code      VARCHAR2(100),
  supplier_id  INTEGER,
  unit_code    VARCHAR2(20),
  total_num    NUMBER,
  remark       VARCHAR2(200)
)
tablespace UFGOV
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SF_SJ_IN
  is '���';
-- Add comments to the columns 
comment on column SF_SJ_IN.in_id
  is '��ⵥID';
comment on column SF_SJ_IN.sj_id
  is '�Լ�ID';
comment on column SF_SJ_IN.shiji_pihao
  is '�Լ�����';
comment on column SF_SJ_IN.amount
  is '����';
comment on column SF_SJ_IN.price
  is '����';
comment on column SF_SJ_IN.expiry_date
  is 'ʧЧ����';
comment on column SF_SJ_IN.store_code
  is '�洢λ��ID';
comment on column SF_SJ_IN.in_bill_code
  is '��ⵥ��';
comment on column SF_SJ_IN.in_date
  is '���ʱ��';
comment on column SF_SJ_IN.inputor
  is '�����';
comment on column SF_SJ_IN.invoice_code
  is '��Ʊ��';
comment on column SF_SJ_IN.buy_code
  is '�ɹ�����';
comment on column SF_SJ_IN.nd
  is '���';
comment on column SF_SJ_IN.co_code
  is '��������';
comment on column SF_SJ_IN.supplier_id
  is '������ID';
comment on column SF_SJ_IN.unit_code
  is '��λ';
comment on column SF_SJ_IN.total_num
  is '�ܼ�';
comment on column SF_SJ_IN.remark
  is '��ע';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SF_SJ_IN
  add primary key (IN_ID)
  using index 
  tablespace UFGOV
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

--28

drop table SF_SJ_OUT;
DROP TABLE SF_SJ_OUT_DETAIL;

DROP TABLE SF_SJ_LOSS;
DROP TABLE SF_SJ_LOSS_DETAIL;



CREATE TABLE SF_SJ_OUT
(
	OUT_ID               INTEGER NOT NULL ,
	OUT_BILL_CODE        VARCHAR2(100) NULL ,
	CO_CODE              VARCHAR2(30) NULL ,
	ND                   INTEGER NULL ,
	PROPOSER             VARCHAR2(60) NULL ,
	PROPOSER_DEPT        VARCHAR2(30) NULL ,
	PROCESS_INST_ID      INTEGER NULL ,
	INPUTOR              VARCHAR2(60) NULL ,
	INPUT_DATE           DATE NULL ,
	OUT_DATE             DATE NULL ,
	REMARK               VARCHAR2(1000) NULL ,
	STATUS               VARCHAR2(30) NULL ,
	AMOUNT               INTEGER NULL ,
	IN_ID                INTEGER NULL ,
	OUT_TYPE             VARCHAR2(30) NULL ,
	LOSS_REASON          VARCHAR2(1000) NULL ,
	LOSS_TIME            DATE NULL ,
 PRIMARY KEY (OUT_ID)
);



COMMENT ON TABLE SF_SJ_OUT IS '����';




COMMENT ON COLUMN SF_SJ_OUT.OUT_ID IS '����ID';




COMMENT ON COLUMN SF_SJ_OUT.OUT_BILL_CODE IS '���ⵥ��';




COMMENT ON COLUMN SF_SJ_OUT.CO_CODE IS '��������';




COMMENT ON COLUMN SF_SJ_OUT.ND IS '���';




COMMENT ON COLUMN SF_SJ_OUT.PROPOSER IS '������';




COMMENT ON COLUMN SF_SJ_OUT.PROPOSER_DEPT IS '���ò���';




COMMENT ON COLUMN SF_SJ_OUT.PROCESS_INST_ID IS '������ʵ����';




COMMENT ON COLUMN SF_SJ_OUT.INPUTOR IS '¼����';




COMMENT ON COLUMN SF_SJ_OUT.INPUT_DATE IS '¼��ʱ��';




COMMENT ON COLUMN SF_SJ_OUT.OUT_DATE IS '����ʱ��';




COMMENT ON COLUMN SF_SJ_OUT.REMARK IS '��ע';




COMMENT ON COLUMN SF_SJ_OUT.STATUS IS '״̬';




COMMENT ON COLUMN SF_SJ_OUT.AMOUNT IS '����';




COMMENT ON COLUMN SF_SJ_OUT.IN_ID IS '��ⵥID';




COMMENT ON COLUMN SF_SJ_OUT.OUT_TYPE IS '����';




COMMENT ON COLUMN SF_SJ_OUT.LOSS_REASON IS '��ʧԭ��';




COMMENT ON COLUMN SF_SJ_OUT.LOSS_TIME IS '���ʱ��';


--29

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_OUT', 'AMOUNT', 0, '����', '����', 'NUMBER', 22, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_OUT', 'CO_CODE', 0, '��������', '��������', 'VARCHAR2', 30, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_OUT', 'INPUTOR', 0, '¼����', '¼����', 'VARCHAR2', 60, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_OUT', 'INPUT_DATE', 0, '¼��ʱ��', '¼��ʱ��', 'DATE', 7, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_OUT', 'IN_ID', 0, '��ⵥID', '��ⵥID', 'NUMBER', 22, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_OUT', 'LOSS_REASON', 0, '��ʧԭ��', '��ʧԭ��', 'VARCHAR2', 1000, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_OUT', 'LOSS_TIME', 0, '���ʱ��', '���ʱ��', 'DATE', 7, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_OUT', 'ND', 0, '���', '���', 'NUMBER', 22, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_OUT', 'OUT_BILL_CODE', 0, '���ⵥ��', '���ⵥ��', 'VARCHAR2', 100, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_OUT', 'OUT_DATE', 0, '����ʱ��', '����ʱ��', 'DATE', 7, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_OUT', 'OUT_ID', 0, '����ID', '����ID', 'NUMBER', 22, null, null, null, 'Y', null, null, 'Y', null, null, 'N', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_OUT', 'OUT_TYPE', 0, '����', '����', 'VARCHAR2', 30, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_OUT', 'PROCESS_INST_ID', 0, '������ʵ����', '������ʵ����', 'NUMBER', 22, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_OUT', 'PROPOSER', 0, '������', '������', 'VARCHAR2', 60, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_OUT', 'PROPOSER_DEPT', 0, '���ò���', '���ò���', 'VARCHAR2', 30, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_OUT', 'REMARK', 0, '��ע', '��ע', 'VARCHAR2', 1000, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

insert into as_tab_col (TAB_ID, DATA_ITEM, ORD_INDEX, DATA_ITEM_DESC, DATA_ITEM_NA, DATA_TYPE, DATA_LEN, DEC_LEN, F_REF_NAME, F_FIELD, IS_SAVE, VAL_SET_ID, IS_FPK, IS_USED, IS_PRE, IS_PK, IS_NULL, IS_NUM, IS_LIST, IS_SELE, DFLT_VAL, ADD_DATE, DB_VER_NO, IS_PAGE_FIELD, MIN_VALUE, MAX_VALUE, MIN_LENGTH, IS_EFFECT, VS_EFFECT_TABLE, URL, IS_KILO_STYLE, IS_TREEVIEW, IS_ONLYLEAF, EDIT_BOX_TYPE, IS_ORDER)
values ('SF_SJ_OUT', 'STATUS', 0, '״̬', '״̬', 'VARCHAR2', 30, null, null, null, 'Y', null, null, 'Y', null, null, 'Y', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

--30

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_OUT_AMOUNT', 'C', '����');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_OUT_CO_CODE', 'C', '��������');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_OUT_INPUTOR', 'C', '¼����');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_OUT_INPUT_DATE', 'C', '¼��ʱ��');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_OUT_IN_ID', 'C', '��ⵥID');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_OUT_LOSS_REASON', 'C', '��ʧԭ��');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_OUT_LOSS_TIME', 'C', '���ʱ��');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_OUT_ND', 'C', '���');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_OUT_OUT_BILL_CODE', 'C', '���ⵥ��');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_OUT_OUT_DATE', 'C', '����ʱ��');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_OUT_OUT_ID', 'C', '����ID');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_OUT_OUT_TYPE', 'C', '����');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_OUT_PROCESS_INST_ID', 'C', '������ʵ����');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_OUT_PROPOSER', 'C', '������');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_OUT_PROPOSER_DEPT', 'C', '���ò���');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_OUT_REMARK', 'C', '��ע');

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_OUT_STATUS', 'C', '״̬');

--31

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_SJ_OUT', 'C', '�Լ�ʹ�õǼ�');

--32

insert into as_table (TAB_ID, MASTER_TAB_ID, TAB_NA, TAB_DESC, DB_VER_NO, TRAD_VER_NO, LSTDATE, PRE_VALSET, IS_TABLE)
values ('SF_SJ_OUT', null, '�Լ�ʹ�õǼ�', '�Լ�ʹ�õǼ�', null, null, null, null, 'Y');

--33

insert into as_compo (COMPO_ID, COMPO_NAME, MASTER_TAB_ID, TRAD_ID, COMPO_TYPE, ICON_NAME, CONDITION, NO_FIELD, TYPE_FIELD, TYPE_TABLE, PARENT_COMPO, PAGE_FIELD, DATE_FIELD, VALSET_FIELD, WF_FLOW_TYPE, WF_LIST_TYPE, DEFAULT_WF_TEMPLATE, TEMPLATE_IS_USED, IS_AUTO_LIST, ORDER_BY, IS_GRANT_TO_ALL, PRINT_TYPE, TITLE_FIELD, TITLE_DATE, WF_BRIEF_FIELDS, IS_FUNC_CONTROL)
values ('SF_SJ_OUT', '�Լ�ʹ�õǼ�', 'SF_SJ_OUT', null, 'M', null, null, null, null, null, null, null, null, null, 'workflow', null, '3100888', 'Y', 'Y', 'OUT_ID', null, '0', 'CODE', null, null, 'Y ');

--34
����seq SEQ_SF_SF_SJ_OUT_ID

--35

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_OUT', 'fcallback', 'Y', null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_OUT', 'fdelete', 'Y', null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_OUT', 'fedit', 'Y', null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_OUT', 'fexit', null, null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_OUT', 'fmanualcommit', 'Y', null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_OUT', 'fnew', null, null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_OUT', 'fnewcommit', 'Y', null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_OUT', 'fprint', 'Y', null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_OUT', 'fprint_preview', null, null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_OUT', 'fprn_tpl_set', null, null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_OUT', 'fsave', 'Y', null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_OUT', 'fshowinstancetrace', null, null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_OUT', 'funaudit', 'Y', null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_OUT', 'funtread', 'Y', null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_COMPO_FUNC (COMPO_ID, FUNC_ID, IS_WR_LOG, IS_NEVER_USE, TRANS_DATE)
values ('SF_SJ_OUT', 'fwatch', null, null, to_date('06-01-2015 10:56:15', 'dd-mm-yyyy hh24:mi:ss'));

--34

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfSjOut_search', 'ND', '���', 1, 'SF_SJ_OUT', null, '�Լ�ʹ�õǼ���������', 'condition', '1');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfSjOut_Tab', 'draft', '�ݸ�', 0, 'SF_SJ_OUT', null, '�Լ�ʹ�õǼ�ҳǩ', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfSjOut_Tab', 'todo', '����', 1, 'SF_SJ_OUT', null, '�Լ�ʹ�õǼ�ҳǩ', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfSjOut_Tab', 'untread', '�˻�', 2, 'SF_SJ_OUT', null, '�Լ�ʹ�õǼ�ҳǩ', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfSjOut_Tab', 'done', '�Ѱ�', 3, 'SF_SJ_OUT', null, '�Լ�ʹ�õǼ�ҳǩ', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfSjOut_Tab', 'exec', '����', 4, 'SF_SJ_OUT', null, '�Լ�ʹ�õǼ�ҳǩ', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfSjOut_Tab', 'all', '�鿴ȫ��', 7, 'SF_SJ_OUT', null, '�Լ�ʹ�õǼ�ҳǩ', 'tab', '301');

--35

insert into ZC_SYS_BILL_ELEMENT (ELEMENT_ID, ND, BILL_TYPE_CODE, ELEMENT_CODE, ELEMENT_NAME, IS_INCLUDE, IS_INHERIT, INHERIT_ELEMENT_CODE, INHERIT_ELEMENT_NAME, IS_EDIT, IS_RANDOM_EDIT, DEC_FLAG, LEVEL_CTRL, IS_EDIT_PUTIN, IS_EDIT_AUDIT, IS_CONFIG, DEFAULT_VAL_CODE, DEFAULT_VAL_NAME, IS_NULL, PARENT_LEVEL_CTRL, EXTEND_ELEMENT_TYPE, LEVEL_STR, DISPLAY_ORDER_INDEX, GENBALANCE_LEVEL_CTRL)
values ('SF_SJ_OUT_AMOUNT', 9999, 'SF_SJ_OUT', 'AMOUNT', '����', '1', '0', null, null, '1', '1', '0', 0, '0', '0', '1', null, null, '0', 0, '1', '1', 1, 0);

insert into ZC_SYS_BILL_ELEMENT (ELEMENT_ID, ND, BILL_TYPE_CODE, ELEMENT_CODE, ELEMENT_NAME, IS_INCLUDE, IS_INHERIT, INHERIT_ELEMENT_CODE, INHERIT_ELEMENT_NAME, IS_EDIT, IS_RANDOM_EDIT, DEC_FLAG, LEVEL_CTRL, IS_EDIT_PUTIN, IS_EDIT_AUDIT, IS_CONFIG, DEFAULT_VAL_CODE, DEFAULT_VAL_NAME, IS_NULL, PARENT_LEVEL_CTRL, EXTEND_ELEMENT_TYPE, LEVEL_STR, DISPLAY_ORDER_INDEX, GENBALANCE_LEVEL_CTRL)
values ('SF_SJ_OUT_PROPOSER', 9999, 'SF_SJ_OUT', 'PROPOSER', '������', '1', '0', null, null, '1', '1', '0', 0, '0', '0', '1', null, null, '0', 0, '1', '1', 1, 0);

--36

insert into as_val (VALSET_ID, VAL_ID, VAL, ORD_INDEX, LSTDATE, IS_SYSTEM, TRANS_DATE)
values ('SF_VS_SJ_OUT_TYPE', 'used', '����', 1, null, 'Y', to_date('18-11-2016 09:48:09', 'dd-mm-yyyy hh24:mi:ss'));

insert into as_val (VALSET_ID, VAL_ID, VAL, ORD_INDEX, LSTDATE, IS_SYSTEM, TRANS_DATE)
values ('SF_VS_SJ_OUT_TYPE', 'lossed', '���', 2, null, 'Y', to_date('18-11-2016 09:48:09', 'dd-mm-yyyy hh24:mi:ss'));

--37


CREATE TABLE SF_REPORT_CODE
(
	ENTRUST_ID           INTEGER NULL ,
	REPORT_CODE          VARCHAR2(30) NULL 
);



COMMENT ON TABLE SF_REPORT_CODE IS '���������ĺ�';




COMMENT ON COLUMN SF_REPORT_CODE.ENTRUST_ID IS 'ί��ID';




COMMENT ON COLUMN SF_REPORT_CODE.REPORT_CODE IS '������';


--38
���� ��ҽѧ�������˼����¼.doc �� �ļ�ģ����

----------------20161116---------------------------
--1
 -- Add/modify columns 
alter table SF_ENTRUSTOR add parent_id INTEGER;
-- Add comments to the columns 
comment on column SF_ENTRUSTOR.parent_id
  is '�ϼ�';
--2
-- Add/modify columns 
alter table SF_ENTRUSTOR add inputor varchar2(20);
alter table SF_ENTRUSTOR add input_date date;
-- Add comments to the columns 
comment on column SF_ENTRUSTOR.inputor
  is '¼����';
comment on column SF_ENTRUSTOR.input_date
  is '¼��ʱ��';

--2
������ְλ����R_SF_JDJG�Ľ�ɫ������ʶ������������


--3
delete from sf_xysx_type where XYSX_TYPE_ID='8';
insert into sf_xysx_type (XYSX_TYPE_ID, PARENT_ID, NAME, DEFAULT_VAL, LIST_INDEX, VAL_TYPE, IS_LEAF, IS_ENABLE)
values (8, 1, '�������Ҫ�����𻵼�ģ��������ݿ�����ʧ���޷���ԭ��', null, 2, '1', 'Y', 'Y');
 
--4
--��ͬ��������ί��������,�������ں�������˼������ĵĵ�λ��� ��SF_ENTRUST001

 
insert into AS_NO_RULE (COMPO_ID, RULE_CODE, RULE_NAME, NO_PREFIX, NO_INDEX_LEN, IS_CONT, NUM_TOOL_ID, NO_FIELD, IS_INCL_ATOZ, IS_FILL_ZERO, NO_AFTFIX, TRANS_DATE)
values ('SF_ENTRUST001', 'SF_ENTRUST_CODE_GEN001', '����ί�б�Ź���', '˼˾��ί��[', 10, 'Y', 'SF_ENTRUST_CODE_GEN_ID001', 'CODE', null, 'N', '��', to_date('15-11-2016 08:59:27', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_NUM_TOOL (NUM_TOOL_ID, NUM_TOOL_NAME, IS_CONT, NUM_TOOL_DESC)
values ('SF_ENTRUST_CODE_GEN_ID001', '˾��ί�б����', 'Y', '˾��ί�б����');

insert into AS_NO_RULE_SEG (COMPO_ID, RULE_CODE, ORD_INDEX, SEG_FIELD, SEG_SV, SEG_CONST, SEG_LEN, SEG_FILL_POSI, SEG_FILL, SEG_DELI, DATE_FMT, IS_BEFORE_NO)
values ('SF_ENTRUST001', 'SF_ENTRUST_CODE_GEN001', 1, 'ND', null, null, 4, null, null, ']��', null, 'Y');


insert into AS_NO_RULE (COMPO_ID, RULE_CODE, RULE_NAME, NO_PREFIX, NO_INDEX_LEN, IS_CONT, NUM_TOOL_ID, NO_FIELD, IS_INCL_ATOZ, IS_FILL_ZERO, NO_AFTFIX, TRANS_DATE)
values ('SF_ENTRUST002', 'SF_ENTRUST_CODE_GEN002', '����ί�б�Ź���', 'Ţ˾��ί��[', 10, 'Y', 'SF_ENTRUST_CODE_GEN_ID002', 'CODE', null, 'N', '��', to_date('15-11-2016 08:59:27', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_NUM_TOOL (NUM_TOOL_ID, NUM_TOOL_NAME, IS_CONT, NUM_TOOL_DESC)
values ('SF_ENTRUST_CODE_GEN_ID002', '˾��ί�б����', 'Y', '˾��ί�б����');

insert into AS_NO_RULE_SEG (COMPO_ID, RULE_CODE, ORD_INDEX, SEG_FIELD, SEG_SV, SEG_CONST, SEG_LEN, SEG_FILL_POSI, SEG_FILL, SEG_DELI, DATE_FMT, IS_BEFORE_NO)
values ('SF_ENTRUST002', 'SF_ENTRUST_CODE_GEN002', 1, 'ND', null, null, 4, null, null, ']��', null, 'Y');

--5

insert into AS_VALSET (VALSET_ID, VALSET_NAME, VAL_SQL, LSTDATE, IS_SYSTEM)
values ('SF_VS_JDJG', '��������', 'select m.Co_Code as val_id, m.Name as val,''SF_VS_JDJG'' as VALSET_ID from SF_JDJG m where M.CO_CODE IS NOT NULL', null, 'N');

--6

insert into ZC_SYS_BILL_ELEMENT (ELEMENT_ID, ND, BILL_TYPE_CODE, ELEMENT_CODE, ELEMENT_NAME, IS_INCLUDE, IS_INHERIT, INHERIT_ELEMENT_CODE, INHERIT_ELEMENT_NAME, IS_EDIT, IS_RANDOM_EDIT, DEC_FLAG, LEVEL_CTRL, IS_EDIT_PUTIN, IS_EDIT_AUDIT, IS_CONFIG, DEFAULT_VAL_CODE, DEFAULT_VAL_NAME, IS_NULL, PARENT_LEVEL_CTRL, EXTEND_ELEMENT_TYPE, LEVEL_STR, DISPLAY_ORDER_INDEX, GENBALANCE_LEVEL_CTRL)
values ('SF_ENTRUST_CO_CODE', 9999, 'SF_ENTRUST', 'CO_CODE', '��������', '1', '0', null, null, '1', '1', '0', 0, '0', '0', '1', null, null, '0', 0, '1', '1', 1, 0);

--7

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('SF_ENTRUST_CO_CODE', 'C', '��������');

--8
-- Add/modify columns 
alter table SF_ENTRUSTOR add short_name varchar2(60);
-- Add comments to the columns 
comment on column SF_ENTRUSTOR.short_name
  is '���';

--9
create or replace view v_sf_wf_current_task_todo as
--��������KESHI_SHOULI��ת��Ϊ���Ҷ�Ӧרҵ���˵Ĵ���
select t.instance_id, tt.ACCOUNT AS executor, tt.MAJOR_CODE
  from V_WF_CURRENT_TASK_ZC_TODO t,
       V_SF_KESHI_SHOULI_PERSON_LINK tt
 where t.executor = 'KESHI_SHOULI'
   and t.executor = tt.KESHI_SHOULI;

create or replace view v_sf_wf_current_task_todo2 as
--�ۺ�ֵ����(ZONGHE_SHOULI��ת��Ϊ��ǰֵ���˵Ĵ���
select t.instance_id, tt.USER_ID AS executor,tt.ND,tt.CO_CODE
  from V_WF_CURRENT_TASK_ZC_TODO t,
       V_SF_ZONGHE_ZHIBAN_PERSON_LINK tt
 where t.executor = 'ZONGHE_SHOULI'
   and t.executor =tt.ZONGHE_ZHIBAN;

create or replace view v_sf_wf_current_task_untread as
--����������(KESHI_SHOULI)ת��Ϊ���Ҷ�Ӧרҵ���˵��˻�
select t.instance_id, tt.ACCOUNT AS executor, tt.MAJOR_CODE
  from V_WF_CURRENT_TASK_ZC_UNTREAD T,
  V_SF_KESHI_SHOULI_PERSON_LINK TT
  where t.executor = 'KESHI_SHOULI'
   and t.executor = tt.KESHI_SHOULI;

create or replace view v_sf_wf_current_task_untread2 as
--�ۺ�ֵ����(ZONGHE_SHOULI��ת��Ϊ��ǰֵ���˵��˻�
select t.instance_id, tt.USER_ID AS executor, TT.ND,TT.CO_CODE
  from V_WF_CURRENT_TASK_ZC_UNTREAD T,
  V_SF_ZONGHE_ZHIBAN_PERSON_LINK TT
  where t.executor = 'ZONGHE_SHOULI'
   and t.executor = TT.ZONGHE_ZHIBAN;

         
CREATE OR REPLACE VIEW V_SF_KESHI_SHOULI_PERSON_LINK AS
--����������(KESHI_SHOULI)�뵱ǰֵ���˵Ĺ���
SELECT 'KESHI_SHOULI' AS KESHI_SHOULI,
               P.ACCOUNT,
               SUBSTR(PM.MAJOR_CODE, 0, 3) AS MAJOR_CODE
          FROM SF_JD_PERSON_MAJOR PM, SF_JD_PERSON P
         WHERE PM.JD_PERSON_ID = P.JD_PERSON_ID;

CREATE OR REPLACE VIEW V_SF_ZONGHE_ZHIBAN_PERSON_LINK AS
--�ۺ�ֵ����(ZONGHE_SHOULI)�뵱ǰֵ���˵Ĺ���
SELECT 'ZONGHE_SHOULI' AS ZONGHE_ZHIBAN,
               ZB.USER_ID,
               ZB.ND,
               ZB.CO_CODE
          FROM SF_ZONGHE_ZHIBAN ZB
         WHERE ZB.END_TIME IS NULL;

--10

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_WTF', 'SfEntrust_Tab', 'all');

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_WTF', 'SfEntrust_Tab', 'untread');

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_WTF', 'SfEntrust_Tab', 'todo');

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_WTF', 'SfEntrust_Tab', 'doing');

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_WTF', 'SfEntrust_Tab', 'unaccept');

insert into zc_role_search_condition (ROLE_ID, CONDITION_ID, CONDITION_FIELD_CODE)
values ('R_SF_WTF', 'SfEntrust_Tab', 'complete');






