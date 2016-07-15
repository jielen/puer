package com.ufgov.zc.server.zc.service;

import java.sql.SQLException;
import java.util.List;

import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcBAgencyListAptd;
import com.ufgov.zc.common.zc.model.ZcBCatalogue;

public interface IZCatalogueService {

  List getZcCatalogueList(ElementConditionDto dto) throws SQLException;

  public void doSave(ZcBCatalogue zcBCatalogue) throws SQLException;

  public void doDelete(ZcBCatalogue zcBCatalogue) throws SQLException;

  public List getZcZcBAgencyAptdList(ElementConditionDto dto) throws SQLException;

  public List getZcBAgencyAptdAllList(ElementConditionDto dto) throws SQLException;

  public void doSaveAptd(ZcBAgencyListAptd aptd) throws SQLException;

  public void doDeleteApds(ZcBAgencyListAptd aptd) throws SQLException;
}
