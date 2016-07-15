package com.ufgov.zc.common.zc.publish;

import java.util.List;

import com.ufgov.zc.common.system.Publishable;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.zc.model.SupplierPriceDetail;
import com.ufgov.zc.common.zc.model.ZcEbSignup;
import com.ufgov.zc.common.zc.model.ZcEbSignupPackDetail;

public interface ISupplierPriceDelegate extends Publishable {

  public List selectPriceDetail(SupplierPriceDetail detail, RequestMeta meta);

  public void deletePriceDetial(SupplierPriceDetail detail, RequestMeta meta);

  public void insertPriceDetail(List details, RequestMeta meta);
  
  public void updateSingUpPackPriceDetail(ZcEbSignupPackDetail packDetail, RequestMeta meta);

  void updateSignUpPackPrice(ZcEbSignup signup, RequestMeta meta);
}
