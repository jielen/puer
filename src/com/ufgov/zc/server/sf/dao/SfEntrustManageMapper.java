package com.ufgov.zc.server.sf.dao;

import com.ufgov.zc.common.sf.model.SfEntrustManage;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

import java.math.BigDecimal;
import java.util.List;

public interface SfEntrustManageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SF_ENTRUST_MANAGE
     *
     * @mbggenerated Mon Aug 15 09:54:19 CST 2016
     */
    int deleteByPrimaryKey(BigDecimal manageId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SF_ENTRUST_MANAGE
     *
     * @mbggenerated Mon Aug 15 09:54:19 CST 2016
     */
    int insert(SfEntrustManage record); 
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SF_ENTRUST_MANAGE
     *
     * @mbggenerated Mon Aug 15 09:54:19 CST 2016
     */
    SfEntrustManage selectByPrimaryKey(BigDecimal manageId);

    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SF_ENTRUST_MANAGE
     *
     * @mbggenerated Mon Aug 15 09:54:19 CST 2016
     */
    int updateByPrimaryKey(SfEntrustManage record);
    
    List getMainDataLst(ElementConditionDto elementConditionDto);
}