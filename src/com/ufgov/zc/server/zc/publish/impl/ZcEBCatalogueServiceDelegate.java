/**   
* @(#) project: zc_xa
* @(#) file: ZcPProMakeServiceDelegate.java
* 
* Copyright 2010 UFGOV, Inc. All rights reserved.
* UFGOV PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
* 
*/
package com.ufgov.zc.server.zc.publish.impl;

import java.sql.SQLException;
import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcBAgencyListAptd;
import com.ufgov.zc.common.zc.model.ZcBCatalogue;
import com.ufgov.zc.common.zc.publish.IZcEBCatalogueServiceDelegate;
import com.ufgov.zc.server.zc.service.IZCatalogueService;

/**
* @ClassName: ZcEBCatalogueServiceDelegate
* @Description: TCJLODO(������һ�仰��������������)
* @date: 2010-4-21 ����05:00:25
* @version: V1.0 
* @since: 1.0
* @author: 
* @modify: 
*/
public class ZcEBCatalogueServiceDelegate implements IZcEBCatalogueServiceDelegate {

  private IZCatalogueService zcCatalogueService;

  public List getZcCatalogueList(ElementConditionDto dto, RequestMeta meta) throws SQLException {
    List list = zcCatalogueService.getZcCatalogueList(dto);
    return list;
  }

  public void doSave(ZcBCatalogue zcBCatalogue, RequestMeta meta) throws SQLException {
    zcCatalogueService.doSave(zcBCatalogue);
  }

  //ɾ��
  public void doDelete(ZcBCatalogue zcBCatalogue, RequestMeta meta) throws SQLException {
    zcCatalogueService.doDelete(zcBCatalogue);
  }

  public List getZcZcBAgencyAptdList(ElementConditionDto dto, RequestMeta meta) throws SQLException {
    List list = zcCatalogueService.getZcZcBAgencyAptdList(dto);
    return list;
  }

  //��ѯȫ�������б?���ʵȼ�������
  public List getZcBAgencyAptdAllList(ElementConditionDto dto, RequestMeta meta) throws SQLException {
    List list = zcCatalogueService.getZcBAgencyAptdAllList(dto);
    return list;
  }

  public void doSaveAptd(ZcBAgencyListAptd aptd, RequestMeta meta) throws SQLException {
    zcCatalogueService.doSaveAptd(aptd);
  }

  public void doDeleteApds(ZcBAgencyListAptd aptd, RequestMeta meta) throws SQLException {
    zcCatalogueService.doDeleteApds(aptd);
  }

  public IZCatalogueService getZcCatalogueService() {
    return zcCatalogueService;
  }

  public void setZcCatalogueService(IZCatalogueService zcCatalogueService) {
    this.zcCatalogueService = zcCatalogueService;
  }

}
