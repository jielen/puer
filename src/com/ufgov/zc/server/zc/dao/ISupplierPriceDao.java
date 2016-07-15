package com.ufgov.zc.server.zc.dao;

import java.util.List;

import com.ufgov.zc.common.zc.model.SupplierPriceDetail;
import com.ufgov.zc.common.zc.model.ZcEbSignupPackDetail;

public interface ISupplierPriceDao {

  public List selectPriceDetail(SupplierPriceDetail detail);

  public void insertPriceDetail(SupplierPriceDetail detail);

  public void deletePriceDetail(SupplierPriceDetail detail);

  public void updateSignPackMoney(ZcEbSignupPackDetail packDetail);
}
