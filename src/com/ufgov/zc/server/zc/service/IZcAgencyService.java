package com.ufgov.zc.server.zc.service;

import java.sql.SQLException;
import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcBAgency;
import com.ufgov.zc.common.zc.model.ZcBAgencyListAptd;

public interface IZcAgencyService {

  List getZcAgencyList(ElementConditionDto dto, RequestMeta meta) throws SQLException;

  public void doSave(ZcBAgency zcBAgency) throws SQLException;

  public List getIsExists(ZcBAgency zcBAgency) throws SQLException;

  public List getPriKey(ZcBAgency zcBAgency) throws SQLException;

  public void doDelete(ZcBAgency zcBAgency) throws SQLException;

  public List getZcZcBAgencyAptdList(ElementConditionDto dto) throws SQLException;

  public List getZcBAgencyAptdAllList(ElementConditionDto dto) throws SQLException;

  public void doSaveAptd(ZcBAgencyListAptd aptd) throws SQLException;

  public void doDeleteApds(ZcBAgencyListAptd aptd) throws SQLException;
}
