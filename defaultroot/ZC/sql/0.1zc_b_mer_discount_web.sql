-- Create table
create table ZC_B_MER_DISCOUNT_WEB
(
  ZC_MER_CODE             VARCHAR2(30) not null,
  ZC_SU_CODE              VARCHAR2(30) not null,
  ZC_TREATY_LOWER_LIMIT   NUMBER not null,
  ZC_TREATY_UPPER_LIMIT   NUMBER not null,
  ZC_TREATY_DISCOUNT_RATE NUMBER(4,2),
  ZC_TREATY_PRICE         NUMBER(16,2),
  ZC_TREATY_MEMO          VARCHAR2(255)
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
-- Create/Recreate primary, unique and foreign key constraints 
alter table ZC_B_MER_DISCOUNT_WEB
  add constraint PK primary key (ZC_MER_CODE, ZC_SU_CODE, ZC_TREATY_LOWER_LIMIT, ZC_TREATY_UPPER_LIMIT)
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
