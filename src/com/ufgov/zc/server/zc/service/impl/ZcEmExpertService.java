package com.ufgov.zc.server.zc.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.EmExpert;
import com.ufgov.zc.common.zc.model.EmExpertTypeJoin;
import com.ufgov.zc.server.system.util.NumUtil;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.dao.ibatis.BaseDao;
import com.ufgov.zc.server.zc.service.IZcEmExpertService;

public class ZcEmExpertService implements IZcEmExpertService {

  private BaseDao baseDao;
   
  public BaseDao getBaseDao() {
    return baseDao;
  }

  public void setBaseDao(BaseDao baseDao) {
    this.baseDao = baseDao;
  }

  public List getEmExpertList(ElementConditionDto dto, RequestMeta meta) throws SQLException {
  
    List list = baseDao.query("EmExpert.getEmExpertInfoList",dto);

    for (int i = 0; i < list.size(); i++) {

      EmExpert bill = (EmExpert) list.get(i);

      List itemList = baseDao.query("EmExpertTypeJoin.getEmExpertTypeJoinListByPrimKey", bill.getEmExpertCode());

      bill.setItemList(itemList);

    }

    ZcSUtil.setBillDBDigest(list);

    return list;
 
  }
 
  public EmExpert selectByPrimaryKey(String primKey, RequestMeta requestMeta) throws SQLException {
    
    EmExpert bill = (EmExpert) baseDao.read("EmExpert.selectByPrimaryKey", primKey);
    
    List itemList = baseDao.query("EmExpertTypeJoin.getEmExpertTypeJoinListByPrimKey", primKey);

    bill.setItemList(itemList);
    
    bill.setDbDigest(bill.digest());

    return bill;
  }

  public void deleteByPrimaryKey(String zcPrimKey, RequestMeta requestMeta) throws SQLException {

    baseDao.delete("EmExpert.deleteByPrimaryKey", zcPrimKey);

    baseDao.delete("EmExpertTypeJoin.deleteEmExpertTypeJoin",zcPrimKey);
  }
 
  public EmExpert updateByPrimaryKey(EmExpert bean, RequestMeta meta) throws SQLException {
    
    List diseList = bean.getItemList();

    if (diseList == null || diseList.size() == 0)

       throw new RuntimeException("专家类别不能为空");
    
    String code = "";
    
    if ("".equals(ZcSUtil.safeString(bean.getEmExpertCode())) || bean.getEmExpertCode().equals("自动编号")) {

      code = NumUtil.getInstance().getNo("ZC_EM_B_EXPERT", "EM_EXPERT_CODE", bean);

      bean.setEmExpertCode(code);
      
      baseDao.insert("EmExpert.insertExpertInfo", bean);

    } else {

      code = bean.getEmExpertCode();
      
      EmExpert originalBean = this.selectByPrimaryKey(code,meta);
      
      ZcSUtil.checkDigest(bean, originalBean, "emExpertCode");//一致性校验
      
      baseDao.update("EmExpert.updateByPrimeryKey", bean);

      baseDao.delete("EmExpertTypeJoin.deleteEmExpertTypeJoin", code);
    }
    
    for (int i = 0; i < diseList.size(); i++) {

      EmExpertTypeJoin bi = (EmExpertTypeJoin) diseList.get(i);

      bi.setEmExpertCode(bean.getEmExpertCode());

      baseDao.insert("EmExpertTypeJoin.addEmExpertTypeJoin", bi);

    }

    return this.selectByPrimaryKey(code,meta);
  }

}
