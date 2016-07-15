package com.ufgov.zc.server.zc.service.impl;

import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcBAgencyListAptd;
import com.ufgov.zc.common.zc.model.ZcBCatalogue;
import com.ufgov.zc.server.zc.dao.IZcCatalogueDao;
import com.ufgov.zc.server.zc.service.IZCatalogueService;
import java.sql.SQLException;
import java.util.List;

public class ZcCatalogueService implements IZCatalogueService {

  private IZcCatalogueDao zcCatalogueDao;

  public List getZcCatalogueList(ElementConditionDto dto) throws SQLException {
    List list = zcCatalogueDao.getZcCatalogueList(dto);
    return list;
  }

  public void doSave(ZcBCatalogue zcBCatalogue) throws SQLException {
    //    List aptList = zcBAgency.getZcBAgencAptdList();
    //    for (int i = 0; i < aptList.size(); i++) {
    //      ZcBAgencyListAptd aptd = (ZcBAgencyListAptd) aptList.get(i);
    //      aptd.setZcAgeyCode(zcBAgency.getZcAgeyCode());
    //      zcCatalogueDao.doDeleteAptd(aptd);
    //    }
    //    List list = zcCatalogueDao.getZcZcBAgencydList(zcBAgency);
    //    if (list.size() == 0) {
    //      zcCatalogueDao.doSave(zcBAgency);
    //    } else {
    //      zcCatalogueDao.update(zcBAgency);
    //    }
    //    for (int i = 0; i < aptList.size(); i++) {
    //      ZcBAgencyListAptd aptd = (ZcBAgencyListAptd) aptList.get(i);
    //      aptd.setZcSeriCode(String.valueOf(i + 1));
    //      aptd.setZcAgeyCode(zcBAgency.getZcAgeyCode());
    //      aptd.setZcDiyuDaima(zcBAgency.getZcDiyuDaima());
    //      zcCatalogueDao.doSaveAptd(aptd);
    //    }
    doDelete(zcBCatalogue);
    zcCatalogueDao.doSave(zcBCatalogue);
  }

  public void doDelete(ZcBCatalogue zcBCatalogue) throws SQLException {
    zcCatalogueDao.doDelete(zcBCatalogue);
  }

  public List getZcZcBAgencyAptdList(ElementConditionDto dto) throws SQLException {
    return zcCatalogueDao.getZcZcBAgencyAptdList(dto);
  }

  public List getZcBAgencyAptdAllList(ElementConditionDto dto) throws SQLException {
    return zcCatalogueDao.getZcBAgencyAptdAllList(dto);
  }

  //�������ʵȼ�
  public void doSaveAptd(ZcBAgencyListAptd aptd) throws SQLException {
    zcCatalogueDao.doDeleteApds(aptd);
    zcCatalogueDao.doSaveAptds(aptd);
  }

  public void doDeleteApds(ZcBAgencyListAptd aptd) throws SQLException {
    zcCatalogueDao.doDeleteApds(aptd);
  }

  public IZcCatalogueDao getZcCatalogueDao() {
    return zcCatalogueDao;
  }

  public void setZcCatalogueDao(IZcCatalogueDao zcCatalogueDao) {
    this.zcCatalogueDao = zcCatalogueDao;
  }

}
