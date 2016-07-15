/**   
* @(#) project: zc_xa
* @(#) file: IZcPProMakeServiceDelegate.java
* 
* Copyright 2010 UFGOV, Inc. All rights reserved.
* UFGOV PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
* 
*/
package com.ufgov.zc.common.zc.publish;

import java.sql.SQLException;
import java.util.List;

import com.ufgov.zc.common.system.Publishable;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcBAgency;
import com.ufgov.zc.common.zc.model.ZcBAgencyListAptd;

public interface IZcEBAgencyServiceDelegate extends Publishable {

  public List getZcAgencyList(ElementConditionDto dto, RequestMeta meta) throws SQLException;

  //保存
  public void doSave(ZcBAgency zcBAgency, RequestMeta meta) throws SQLException;

  //校验代理机构是否存在
  public List getIsExists(ZcBAgency zcBAgency, RequestMeta meta) throws SQLException;

  public List getPriKey(ZcBAgency zcBAgency, RequestMeta meta) throws SQLException;

  //删除
  public void doDelete(ZcBAgency zcBAgency, RequestMeta meta) throws SQLException;

  public List getZcZcBAgencyAptdList(ElementConditionDto dto, RequestMeta meta) throws SQLException;

  public List getZcBAgencyAptdAllList(ElementConditionDto dto, RequestMeta meta) throws SQLException;

  public void doSaveAptd(ZcBAgencyListAptd aptd, RequestMeta meta) throws SQLException;

  public void doDeleteApds(ZcBAgencyListAptd aptd, RequestMeta meta) throws SQLException;

}
