package com.ufgov.zc.server.zc.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcBAgency;
import com.ufgov.zc.common.zc.model.ZcBAgencyListAptd;
import com.ufgov.zc.server.zc.dao.IZcAgencyDao;
import com.ufgov.zc.server.zc.service.IZcAgencyService;

public class ZcAgencyService implements IZcAgencyService {

  private IZcAgencyDao zcAgencyDao;

  public List getZcAgencyList(ElementConditionDto dto, RequestMeta meta) throws SQLException {
    List list = zcAgencyDao.selectAgency(dto, meta);
    return list;
  }

  public void doSave(ZcBAgency zcBAgency) throws SQLException {
    List aptList = zcBAgency.getZcBAgencAptdList();
    if (aptList != null) {
      for (int i = 0; i < aptList.size(); i++) {
        ZcBAgencyListAptd aptd = (ZcBAgencyListAptd) aptList.get(i);
        aptd.setZcAgeyCode(zcBAgency.getZcAgeyCode());
        zcAgencyDao.doDeleteAptd(aptd);
      }
    }
    List list = zcAgencyDao.getZcZcBAgencydList(zcBAgency);
    if (list.size() == 0) {
      zcAgencyDao.doSave(zcBAgency);
    } else {
      zcAgencyDao.update(zcBAgency);
    }
    if (aptList != null) {
      for (int i = 0; i < aptList.size(); i++) {
        ZcBAgencyListAptd aptd = (ZcBAgencyListAptd) aptList.get(i);
        aptd.setZcSeriCode(String.valueOf(i + 1));
        aptd.setZcAgeyCode(zcBAgency.getZcAgeyCode());
        aptd.setZcDiyuDaima(zcBAgency.getZcDiyuDaima());
        zcAgencyDao.doSaveAptd(aptd);
      }
    }
  }

  public List getIsExists(ZcBAgency zcBAgency) throws SQLException {
    return zcAgencyDao.getIsExists(zcBAgency);
  }

  public List getPriKey(ZcBAgency zcBAgency) throws SQLException {
    return zcAgencyDao.getPriKey(zcBAgency);
  }

  public void doDelete(ZcBAgency zcBAgency) throws SQLException {
    zcAgencyDao.doDelete(zcBAgency);
  }

  public List getZcZcBAgencyAptdList(ElementConditionDto dto) throws SQLException {
    return zcAgencyDao.getZcZcBAgencyAptdList(dto);
  }

  public List getZcBAgencyAptdAllList(ElementConditionDto dto) throws SQLException {
    return zcAgencyDao.getZcBAgencyAptdAllList(dto);
  }

  //�������ʵȼ�
  public void doSaveAptd(ZcBAgencyListAptd aptd) throws SQLException {
    zcAgencyDao.doDeleteApds(aptd);
    zcAgencyDao.doSaveAptds(aptd);
  }

  public void doDeleteApds(ZcBAgencyListAptd aptd) throws SQLException {
    zcAgencyDao.doDeleteApds(aptd);
  }

  public IZcAgencyDao getZcAgencyDao() {
    return zcAgencyDao;
  }

  public void setZcAgencyDao(IZcAgencyDao zcAgencyDao) {
    this.zcAgencyDao = zcAgencyDao;
  }

}
