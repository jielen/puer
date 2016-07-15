package com.ufgov.zc.server.commonbiz.dao;

import java.util.List;

import com.ufgov.zc.common.commonbiz.model.MaCompany;
import com.ufgov.zc.common.commonbiz.model.MaCompanyExample;
import com.ufgov.zc.common.commonbiz.model.MaCompanyKey;

public interface IMaCompanyDAO {

  int countByExample(MaCompanyExample example);

  int deleteByExample(MaCompanyExample example);

  int deleteByPrimaryKey(MaCompanyKey key);

  void insert(MaCompany record);

  void insertSelective(MaCompany record);

  List selectByExample(MaCompanyExample example);

  MaCompany selectByPrimaryKey(MaCompanyKey key);

  int updateByExampleSelective(MaCompany record, MaCompanyExample example);

  int updateByExample(MaCompany record, MaCompanyExample example);

  int updateByPrimaryKeySelective(MaCompany record);

  int updateByPrimaryKey(MaCompany record);
}