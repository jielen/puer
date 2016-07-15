package com.ufgov.zc.server.zc.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.zc.model.EmExpertAbility;
import com.ufgov.zc.common.zc.model.EmExpertAbilityHistory;
import com.ufgov.zc.common.zc.model.EmExpertEvaluation;
import com.ufgov.zc.server.zc.dao.ibatis.BaseDao;
import com.ufgov.zc.server.zc.service.IZcEmExpertAbilityService;

public class ZcEmExpertAbilityService implements IZcEmExpertAbilityService {

  private BaseDao baseDao;
   
  public BaseDao getBaseDao() {
    return baseDao;
  }

  public void setBaseDao(BaseDao baseDao) {
    this.baseDao = baseDao;
  }

 
  public EmExpertAbility selectByPrimaryKey(String primKey, RequestMeta requestMeta) throws SQLException {
    
    EmExpertAbility bill = (EmExpertAbility) baseDao.read("EmExpertAbilityHistory.selectByPrimaryKey", primKey);
    
    List abilityList = baseDao.query("EmExpertAbilityHistory.getExpertAbilityHistoryByPrimKey", primKey);

    List itemList = baseDao.query("EmExpertEvaluation.listByEmExpertCode", primKey);
    
    //根据抽取单号获取项目名称和分包  
      List packNameLst=baseDao.query("EmExpertSelectionBill.getPakcNamesByExpertCodes", primKey); 
     if(packNameLst!=null && packNameLst.size()>0){
       HashMap s1=new HashMap(),s2=new HashMap();
       for(int i=0;i<packNameLst.size();i++){
         HashMap row=(HashMap) packNameLst.get(i);
         String proj_name=(String) row.get("EM_MAKE_NAME");
         String proj_code=(String) row.get("PROJ_CODE");
         String pack_name=(String) row.get("PACK_NAME");
         String pack_code=(String) row.get("PACK_CODE");
         String pack_desc=(String) row.get("PUR_CONTENT");
         String emBillCode=(String) row.get("EM_BILL_CODE");
         if(s2.containsKey(emBillCode)){
           StringBuffer b=(StringBuffer) s2.get("PACK_CODE");
           b.append(",").append(pack_code);
           StringBuffer b2=(StringBuffer) s2.get("PACK_NAME");
           b2.append(",").append(pack_name);
           StringBuffer b3=(StringBuffer) s2.get("PACK_DESC");
           b3.append(",").append(pack_desc);
           s2.put("PACK_CODE", b);
           s2.put("PACK_NAME", b2);
           s2.put("PUR_CONTENT", b3);
         }else{
           StringBuffer b=new StringBuffer();
           b.append(pack_code);
           StringBuffer b2=new StringBuffer();
           b2.append(pack_name);
           StringBuffer b3=new StringBuffer();
           b3.append(pack_desc);
           s2.put("PACK_CODE", b);
           s2.put("PACK_NAME", b2); 
           s2.put("PACK_DESC", b3);   
           s2.put("PROJ_CODE", proj_code); 
           s2.put("EM_MAKE_NAME", proj_name);
           s2.put("EM_BILL_CODE", emBillCode);
         }
         s1.put(emBillCode, s2);
       }
       for(int i=0;i<itemList.size();i++){
         EmExpertEvaluation item=(EmExpertEvaluation) itemList.get(i);
         if(s1.containsKey(item.getEmBillCode())){
           HashMap mp=(HashMap) s1.get(item.getEmBillCode());
           if(mp!=null){
             String packName=mp.get("PACK_NAME")==null?"":mp.get("PACK_NAME").toString();
             String packCode=mp.get("PACK_CODE")==null?"":mp.get("PACK_CODE").toString();
             String packDesc=mp.get("PACK_DESC")==null?"":mp.get("PACK_DESC").toString();
             String projCode=mp.get("PROJ_CODE")==null?"":mp.get("PROJ_CODE").toString();
             String projName=mp.get("EM_MAKE_NAME")==null?"":mp.get("EM_MAKE_NAME").toString();
             item.setProjName(projName);
             item.setPackCode(packCode);
             item.setPackName(packName);
             item.setPackDesc(packDesc);
           }
         }
       }
     }
  
    bill.setItemList(itemList);
    
    bill.setAbilityList(abilityList);
    
    bill.setDbDigest(bill.digest());

    return bill;
  }

 
  public EmExpertAbility updateByPrimaryKey(EmExpertAbility bean, RequestMeta meta) throws SQLException {
    
    List diseList = bean.getAbilityList();

    if (diseList == null || diseList.size() == 0)

       throw new RuntimeException("专家能力评审不能为空");

    String code = bean.getEmExpertCode();
      
    baseDao.delete("EmExpertAbilityHistory.deleteEmExpertAbilityHistory", code);
    
    for (int i = 0; i < diseList.size(); i++) {

      EmExpertAbilityHistory bi = (EmExpertAbilityHistory) diseList.get(i);

      bi.setEmExpertCode(bean.getEmExpertCode());

      baseDao.insert("EmExpertAbilityHistory.addExpertAbilityHistory", bi);

    }

    return this.selectByPrimaryKey(code,meta);
  }

}
