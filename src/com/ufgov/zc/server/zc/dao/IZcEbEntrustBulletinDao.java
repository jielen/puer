package com.ufgov.zc.server.zc.dao;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcEbProjPrintPermit;

public interface IZcEbEntrustBulletinDao {

  public List getZcEbEntrustBull(ElementConditionDto dto, RequestMeta meta);

  public List getZcEbEntrustBullin(ElementConditionDto dto, RequestMeta meta);

  public List getZcEbEntrustReport(ElementConditionDto dto, RequestMeta meta);

  public List getZcEbProjectSupport(ElementConditionDto dto, RequestMeta meta);

  public void deletePrintPermit(ZcEbProjPrintPermit proj);

  public void insertPrintPermit(ZcEbProjPrintPermit proj);

  public void updatePrintPermit(ZcEbProjPrintPermit proj);

  public List getZcEbProjPrintPermit(ZcEbProjPrintPermit proj);

  public void updateSupplierIsSite(List zcEbProjSupportList);
  
  public List frozenZcBSupplier(List zcEbProjSupportList);

  public List getZcEbPackHistory(ElementConditionDto dto, RequestMeta meta);

}
