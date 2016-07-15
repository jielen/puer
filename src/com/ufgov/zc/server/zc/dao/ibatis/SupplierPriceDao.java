package com.ufgov.zc.server.zc.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.zc.model.SupplierPriceDetail;
import com.ufgov.zc.common.zc.model.ZcEbSignupPackDetail;
import com.ufgov.zc.server.zc.dao.ISupplierPriceDao;

public class SupplierPriceDao extends SqlMapClientDaoSupport implements ISupplierPriceDao {
  private String namespace = "ZC_EB_SUP_PRICE.";

  
  public List selectPriceDetail(SupplierPriceDetail detail) {
    return this.getSqlMapClientTemplate().queryForList(namespace + "selectPriceDetail", detail);
  }

  
  public void insertPriceDetail(SupplierPriceDetail detail) {
    this.getSqlMapClientTemplate().insert(namespace + "insertPriceDetail", detail);
  }

  
  public void deletePriceDetail(SupplierPriceDetail detail) {
    this.getSqlMapClientTemplate().insert(namespace + "deletePriceDetail", detail);
  }

  
  public void updateSignPackMoney(ZcEbSignupPackDetail packDetail) {
    this.getSqlMapClientTemplate().update(namespace + "updateSignPackMoney", packDetail);
  }

}
