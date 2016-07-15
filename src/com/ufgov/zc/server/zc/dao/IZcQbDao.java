package com.ufgov.zc.server.zc.dao;

import java.util.List;

import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcQb;

public interface IZcQbDao {

  List getQbLst(ElementConditionDto elementConditionDto);

  ZcQb selectByPrimaryKey(String qbCode);

  List getQbBiLst(String qbCode);

  List getItemLst(String qbCode);

  void insert(ZcQb qb);
  
  List selectBiByQbCode(String qbCode);
  
  String getMaxVouId(String vouId) ;

  void insertBiLst(List biList);

  void insertItemLst(List itemList);

  void update(ZcQb qb);

  void deleteBiByQbCode(String qbCode);

  void deleteItemByQbCode(String qbCode);
  
  void delete(ZcQb qb);

}
