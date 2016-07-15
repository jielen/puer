package com.ufgov.zc.server.zc.dao;

import java.util.List;

import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcBAgency;
import com.ufgov.zc.common.zc.model.ZcBAgencyListAptd;
import com.ufgov.zc.common.zc.model.ZcBCatalogue;

public interface IZcCatalogueDao {

  //��ѯ������б�
  List getZcCatalogueList(ElementConditionDto dto);

  void doSave(ZcBCatalogue zcBCatalogue);

  void update(ZcBAgency zcBAgency);

  void doDelete(ZcBCatalogue zcBCatalogue);

  void doDeleteAptd(ZcBAgencyListAptd zcBAgency);

  void doSaveAptd(ZcBAgencyListAptd zcBAgency);

  List getZcZcBAgencyAptdList(ElementConditionDto dto);

  //��������ѯ
  List getZcZcBAgencydList(ZcBAgency zcBAgency);

  public List getZcBAgencyAptdAllList(ElementConditionDto dto);

  public void doSaveAptds(ZcBAgencyListAptd aptd);

  public void doDeleteApds(ZcBAgencyListAptd aptd);

}