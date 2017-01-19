----------------20170119--------------------
--1

UPDATE AS_NO_RULE R SET  NO_PREFIX='普司鉴确认['   where COMPO_ID = 'SF_ENTRUST' ;

--2

insert into as_func (FUNC_ID, FUNC_DESC, ORD_INDEX, IS_GRANT_TO_ALL, IS_LIST, SHORTCUT_KEY, IS_CTRL, IS_SHIFT, IS_ALT)
values ('fsubcopy', '复制', 765, null, 'Y', null, null, null, null);

--3

insert into as_lang_trans (RES_ID, LANG_ID, RES_NA)
values ('fsubcopy', 'C', '复制');

--4

insert into AS_NO_RULE (COMPO_ID, RULE_CODE, RULE_NAME, NO_PREFIX, NO_INDEX_LEN, IS_CONT, NUM_TOOL_ID, NO_FIELD, IS_INCL_ATOZ, IS_FILL_ZERO, NO_AFTFIX, TRANS_DATE)
values ('SF_JD_REPORT_000', 'SF_JD_REPORT_CODE_GEN_000', '鉴定文书编号规则000', '普公司鉴[', 10, 'Y', 'SF_JD_REPORT_CODE_GEN_ID_000', 'REPORT_CODE', null, 'N', '号', to_date('19-01-2017 12:30:25', 'dd-mm-yyyy hh24:mi:ss'));

insert into AS_NUM_TOOL (NUM_TOOL_ID, NUM_TOOL_NAME, IS_CONT, NUM_TOOL_DESC)
values ('SF_JD_REPORT_CODE_GEN_ID_000', '鉴定文书编号器', 'Y', '鉴定文书编号器');

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
values ('SfEntrust_search', 'ND', '年度', 2, 'SF_ENTRUST', null, '司法鉴定委托搜索条件', 'condition', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'todo', '待处理', 10, 'SF_ENTRUST', null, '司法鉴定委托页签', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'wtfTodo', '待处理', 11, 'SF_ENTRUST', null, '司法鉴定委托页签', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'untread', '被退回', 12, 'SF_ENTRUST', null, '司法鉴定委托页签', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'doing', '鉴定中', 15, 'SF_ENTRUST', null, '司法鉴定委托页签', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'other', '其他', 22, 'SF_ENTRUST', null, '司法鉴定委托页签', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'complete', '鉴定完成', 20, 'SF_ENTRUST', null, '司法鉴定委托页签', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'pause', '鉴定暂停', 25, 'SF_ENTRUST', null, '司法鉴定委托页签', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'stop', '鉴定终止', 30, 'SF_ENTRUST', null, '司法鉴定委托页签', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'expire', '逾期', 35, 'SF_ENTRUST', null, '司法鉴定委托页签', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'unaccept', '不被受理', 40, 'SF_ENTRUST', null, '司法鉴定委托页签', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'docNoSend', '意见书待领', 50, 'SF_ENTRUST', null, '司法鉴定委托页签', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'docSended', '意见书已领', 55, 'SF_ENTRUST', null, '司法鉴定委托页签', 'tab', '301');

insert into ZC_SEARCH_CONDITION (CONDITION_ID, CONDITION_FIELD_CODE, CONDITION_FIELD_NAME, CONDITION_FIELD_ORDER, COMPO_ID, COMPO_NAME, CONDITION_NAME, CONDITION_TYPE, CONDITION_NAME_ORDER)
values ('SfEntrust_Tab', 'all', '查看全部', 65, 'SF_ENTRUST', null, '司法鉴定委托页签', 'tab', '301');
