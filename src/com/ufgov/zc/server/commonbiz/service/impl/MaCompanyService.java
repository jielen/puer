package com.ufgov.zc.server.commonbiz.service.impl;

import java.util.List;

import com.ufgov.zc.common.commonbiz.model.MaCompany;
import com.ufgov.zc.common.commonbiz.model.MaCompanyExample;
import com.ufgov.zc.common.commonbiz.model.MaCompanyKey;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.server.commonbiz.dao.IMaCompanyDAO;
import com.ufgov.zc.server.commonbiz.service.IMaCompanyService;

public class MaCompanyService implements IMaCompanyService {
  private IMaCompanyDAO macompanyDao;

  public int countByExample(MaCompanyExample example, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return this.macompanyDao.countByExample(example);
  }

  public int deleteByExample(MaCompanyExample example, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return this.macompanyDao.deleteByExample(example);
  }

  public int deleteByPrimaryKey(MaCompanyKey key, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return this.macompanyDao.deleteByPrimaryKey(key);
  }

  public void insert(MaCompany record, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    this.macompanyDao.insert(record);
  }

  public void insertSelective(MaCompany record, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    this.macompanyDao.insertSelective(record);
  }

  public List selectByExample(MaCompanyExample example, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return this.macompanyDao.selectByExample(example);
  }

  public MaCompany selectByPrimaryKey(MaCompanyKey key, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return this.macompanyDao.selectByPrimaryKey(key);
  }

  public int updateByExampleSelective(MaCompany record, MaCompanyExample example, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return this.macompanyDao.updateByExampleSelective(record, example);
  }

  public int updateByExample(MaCompany record, MaCompanyExample example, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return this.macompanyDao.updateByExample(record, example);
  }

  public int updateByPrimaryKeySelective(MaCompany record, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return this.macompanyDao.updateByPrimaryKeySelective(record);
  }

  public int updateByPrimaryKey(MaCompany record, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return this.macompanyDao.updateByPrimaryKey(record);
  }

  public IMaCompanyDAO getMacompanyDao() {
    return macompanyDao;
  }

  public void setMacompanyDao(IMaCompanyDAO macompanyDao) {
    this.macompanyDao = macompanyDao;
  }

}
