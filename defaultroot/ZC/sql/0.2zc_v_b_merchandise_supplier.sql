create or replace view zc_v_b_merchandise_supplier as
select price.zc_su_code,
             mer.ZC_MER_CODE,mer.ZC_BRA_CODE,mer.ZC_PROJ_ID,mer.ZC_YEAR,mer.ZC_CATALOGUE_CODE,mer.
             ZC_SUP_MER_CODE,mer.ZC_MER_NAME,mer.ZC_CG_LEIXING,
             mer.ZC_MER_M_PRICE,mer.ZC_MER_TAX,mer.ZC_MER_SPEC,
             mer.ZC_MER_IS_ATTACH,mer.ZC_MER_COLLOCATE,mer.ZC_MER_UNIT,
             mer.ZC_MER_PIC,mer.ZC_MER_PIC_BLOBID,mer.ZC_IS_JNJS,mer.ZC_MER_STATUS,
             mer.ZC_REMARK,mer.ZC_BRA_NAME,mer.ZC_SUP_BRA_CODE,mer.ZC_SUP_PROJ_ID,
             mer.ZC_DIYU_DAIMA,mer.ZC_MD_TYPE,mer.ZC_IS_SHARED,mer.ZC_MER_IS_ZZCX,
             mer.ZC_MER_IS_LSHB,mer.ZC_CATALOGUE_YEAR,
                (select distinct ZC_CATALOGUE_NAME from ZC_B_CATALOGUE where ZC_B_CATALOGUE.ZC_CATALOGUE_CODE = mer.ZC_CATALOGUE_CODE and ZC_B_CATALOGUE.ZC_YEAR = mer.ZC_CATALOGUE_YEAR ) as ZC_CATALOGUE_NAME,
                case when nvl(ZC_MER_TAX,0) > 0 then round(((ZC_MER_TAX-ZC_MER_M_PRICE)/ZC_MER_TAX)*100,2) else 0 end as ZC_YHL,
                null as ZC_CJSL ,
             case
             when (price.ZC_MER_CG_PRICE_MIN is null or
                  price.ZC_MER_CG_PRICE_MAX is null) then
              '0.00'
             when (price.ZC_MER_CG_PRICE_MIN = price.ZC_MER_CG_PRICE_MAX) then
              to_char(price.ZC_MER_CG_PRICE_MIN,9999999990.99)
             else
              to_char(price.ZC_MER_CG_PRICE_MIN,9999999990.99) || ' бл' || to_char(price.ZC_MER_CG_PRICE_MAX,9999999990.99)
             end as ZC_MER_CG_PRICE
         from ZC_B_MERCHANDISE mer,
              (select zc_su_code,ZC_MER_CODE,
                    min(ZC_MER_CG_PRICE) as ZC_MER_CG_PRICE_MIN,
                    max(ZC_MER_CG_PRICE) as ZC_MER_CG_PRICE_MAX
               from ZC_B_MER_PRICE
              where ZC_MER_CG_PRICE != 0.00
              group by ZC_MER_CODE ,zc_su_code)   price,
               ZC_B_SUPPLIER    sup
         where mer.zc_mer_code = price.zc_mer_code
           and sup.zc_su_code = price.zc_su_code;
