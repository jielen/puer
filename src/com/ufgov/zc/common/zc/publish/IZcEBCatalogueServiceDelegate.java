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
import com.ufgov.zc.common.zc.model.ZcBAgencyListAptd;
import com.ufgov.zc.common.zc.model.ZcBCatalogue;

public interface IZcEBCatalogueServiceDelegate extends Publishable {

  //��ѯ���вɹ�Ŀ¼List�б�
  public List getZcCatalogueList(ElementConditionDto dto, RequestMeta meta) throws SQLException;

  //����
  public void doSave(ZcBCatalogue zcBCatalogue, RequestMeta meta) throws SQLException;

  //ɾ��
  public void doDelete(ZcBCatalogue zcBCatalogue, RequestMeta meta) throws SQLException;

  //��ѯ����������б�
  public List getZcZcBAgencyAptdList(ElementConditionDto dto, RequestMeta meta) throws SQLException;

  //��ѯȫ�������б?���ʵȼ�������
  public List getZcBAgencyAptdAllList(ElementConditionDto dto, RequestMeta meta) throws SQLException;

  //��ѯȫ�������б?���ʵȼ�������
  public void doSaveAptd(ZcBAgencyListAptd aptd, RequestMeta meta) throws SQLException;

  public void doDeleteApds(ZcBAgencyListAptd aptd, RequestMeta meta) throws SQLException;

}
