prompt PL/SQL Developer import file
prompt Created on 2011Äê6ÔÂ8ÈÕ by john
set feedback off
set define off
prompt Disabling triggers for ZC_B_MER_PRICE_WEB...
alter table ZC_B_MER_PRICE_WEB disable all triggers;
prompt Deleting ZC_B_MER_PRICE_WEB...
delete from ZC_B_MER_PRICE_WEB;
commit;
prompt Loading ZC_B_MER_PRICE_WEB...
prompt Table is empty
prompt Enabling triggers for ZC_B_MER_PRICE_WEB...
alter table ZC_B_MER_PRICE_WEB enable all triggers;
set feedback on
set define on
prompt Done.
