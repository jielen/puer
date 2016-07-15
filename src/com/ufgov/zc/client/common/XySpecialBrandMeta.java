package com.ufgov.zc.client.common;

import java.util.ArrayList;
import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class XySpecialBrandMeta {

  private static List<String> specialBrand = new ArrayList<String>();
  private RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  private static IZcEbBaseServiceDelegate zcBaseDataServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory

  .create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");
  
  public static List<String> getXySpecialBrand(RequestMeta meta){
    if(specialBrand.size() ==0){
      specialBrand = zcBaseDataServiceDelegate.queryDataForList("ZC_P_PRO_MAKE.getSpecialBrands", null, meta);
    }
    if(specialBrand == null){
      specialBrand = new ArrayList<String>();
    }
    return specialBrand;
  }
}
