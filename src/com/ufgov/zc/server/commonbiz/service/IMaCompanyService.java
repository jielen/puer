package com.ufgov.zc.server.commonbiz.service;

import java.util.List;

import com.ufgov.zc.common.commonbiz.model.MaCompany;
import com.ufgov.zc.common.commonbiz.model.MaCompanyExample;
import com.ufgov.zc.common.commonbiz.model.MaCompanyKey;
import com.ufgov.zc.common.system.RequestMeta;

public interface IMaCompanyService {
  int countByExample(MaCompanyExample example, RequestMeta requestMeta);

  int deleteByExample(MaCompanyExample example, RequestMeta requestMeta);

  int deleteByPrimaryKey(MaCompanyKey key, RequestMeta requestMeta);

  void insert(MaCompany record, RequestMeta requestMeta);

  void insertSelective(MaCompany record, RequestMeta requestMeta);

  List selectByExample(MaCompanyExample example, RequestMeta requestMeta);

  MaCompany selectByPrimaryKey(MaCompanyKey key, RequestMeta requestMeta);

  int updateByExampleSelective(MaCompany record, MaCompanyExample example, RequestMeta requestMeta);

  int updateByExample(MaCompany record, MaCompanyExample example, RequestMeta requestMeta);

  int updateByPrimaryKeySelective(MaCompany record, RequestMeta requestMeta);

  int updateByPrimaryKey(MaCompany record, RequestMeta requestMeta);
}
