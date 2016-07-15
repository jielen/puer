package com.ufgov.zc.server.zc.dao;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcBdSppc;

public interface IZcBdSppcDao {

  void insertZcBdSppc(ZcBdSppc record);

  List getZcBdSppcList(ElementConditionDto dto, RequestMeta meta);

  ZcBdSppc selectByPrimaryKey(String primKey);

  int deleteByPrimaryKey(String zcPrimKey);

  int updateZcBdSppcByPrimaryKey(ZcBdSppc zcBdSppc);

}