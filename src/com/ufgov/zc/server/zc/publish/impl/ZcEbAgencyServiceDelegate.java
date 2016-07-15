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
import com.ufgov.zc.common.zc.model.ZcBAgency;
import com.ufgov.zc.common.zc.model.ZcBAgencyListAptd;
import com.ufgov.zc.common.zc.publish.IZcEBAgencyServiceDelegate;
import com.ufgov.zc.server.zc.service.IZcAgencyService;

public class ZcEbAgencyServiceDelegate implements IZcEBAgencyServiceDelegate {

  private IZcAgencyService zcAgencyService;

  public List getZcAgencyList(ElementConditionDto dto, RequestMeta meta) throws SQLException {
    List list = zcAgencyService.getZcAgencyList(dto, meta);
    return list;
  }

  public void doSave(ZcBAgency zcBAgency, RequestMeta meta) throws SQLException {
    zcAgencyService.doSave(zcBAgency);
  }

  public List getIsExists(ZcBAgency zcBAgency, RequestMeta meta) throws SQLException {
    return zcAgencyService.getIsExists(zcBAgency);
  }

  public List getPriKey(ZcBAgency zcBAgency, RequestMeta meta) throws SQLException {
    return zcAgencyService.getPriKey(zcBAgency);
  }

  public void doDelete(ZcBAgency zcBAgency, RequestMeta meta) throws SQLException {
    zcAgencyService.doDelete(zcBAgency);
  }

  public List getZcZcBAgencyAptdList(ElementConditionDto dto, RequestMeta meta) throws SQLException {
    List list = zcAgencyService.getZcZcBAgencyAptdList(dto);
    return list;
  }

  public List getZcBAgencyAptdAllList(ElementConditionDto dto, RequestMeta meta) throws SQLException {
    List list = zcAgencyService.getZcBAgencyAptdAllList(dto);
    return list;
  }

  public void doSaveAptd(ZcBAgencyListAptd aptd, RequestMeta meta) throws SQLException {
    zcAgencyService.doSaveAptd(aptd);
  }

  public void doDeleteApds(ZcBAgencyListAptd aptd, RequestMeta meta) throws SQLException {
    zcAgencyService.doDeleteApds(aptd);
  }

  public IZcAgencyService getZcAgencyService() {
    return zcAgencyService;
  }

  public void setZcAgencyService(IZcAgencyService zcAgencyService) {
    this.zcAgencyService = zcAgencyService;
  }

}
