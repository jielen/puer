package com.ufgov.zc.server.zc.service;

import java.util.List;

import com.ufgov.zc.common.zc.model.SupplierPriceDetail;
import com.ufgov.zc.common.zc.model.ZcEbSignupPackDetail;

public interface ISupplierPriceService {
  
  public List selectPriceDetail(SupplierPriceDetail detail);
  
  public void deletePriceDetial(SupplierPriceDetail detail);
  
  public void insertPriceDetail(List details);
  
  public void updateSingUpPackPriceDetail(ZcEbSignupPackDetail packDetail);
  

}
