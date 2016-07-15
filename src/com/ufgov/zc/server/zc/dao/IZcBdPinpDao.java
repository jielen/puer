package com.ufgov.zc.server.zc.dao;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcBBrand;

public interface IZcBdPinpDao {

  void insert(ZcBBrand record);

  List getPinpList(ElementConditionDto dto, RequestMeta meta);

  ZcBBrand selectByPrimaryKey(String primKey);

  int deleteByPrimaryKey(String zcPrimKey);

  int updateByPrimaryKey(ZcBBrand zcBdPinp);

}