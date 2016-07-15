package com.ufgov.zc.server.zc.dao;

import java.util.List;

import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcQx;

public interface IZcQxDao {

  List getQxLst(ElementConditionDto elementConditionDto);

  ZcQx selectByPrimaryKey(String qxCode);

  List getQxBiLst(String qxCode);

  List getItemLst(String qxCode);

  void insert(ZcQx qx);
  
  List selectBiByQxCode(String qxCode);
  
  String getMaxVouId(String vouId) ;

  void insertBiLst(List biList);

  void insertItemLst(List itemList);

  void update(ZcQx qx);

  void deleteBiByQxCode(String qxCode);

  void deleteItemByQxCode(String qxCode);
  
  void delete(ZcQx qx);

}
