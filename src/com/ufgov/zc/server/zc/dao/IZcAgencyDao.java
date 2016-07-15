package com.ufgov.zc.server.zc.dao;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcBAgency;
import com.ufgov.zc.common.zc.model.ZcBAgencyListAptd;

public interface IZcAgencyDao {

  List selectAgency(ElementConditionDto dto, RequestMeta meta);

  void doSave(ZcBAgency zcBAgency);

  List getIsExists(ZcBAgency zcBAgency);

  List getPriKey(ZcBAgency zcBAgency);

  void update(ZcBAgency zcBAgency);

  void doDelete(ZcBAgency zcBAgency);

  void doDeleteAptd(ZcBAgencyListAptd zcBAgency);

  void doSaveAptd(ZcBAgencyListAptd zcBAgency);

  List getZcZcBAgencyAptdList(ElementConditionDto dto);

  //��������ѯ
  List getZcZcBAgencydList(ZcBAgency zcBAgency);

  public List getZcBAgencyAptdAllList(ElementConditionDto dto);

  public void doSaveAptds(ZcBAgencyListAptd aptd);

  public void doDeleteApds(ZcBAgencyListAptd aptd);

}