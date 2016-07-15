package com.ufgov.zc.server.zc.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcBBrand;
import com.ufgov.zc.server.system.util.NumUtil;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.dao.IZcBdPinpDao;
import com.ufgov.zc.server.zc.service.IZcBdPinpService;

public class ZcBdPinpService implements IZcBdPinpService {

  private IZcBdPinpDao zcBdPinpDao;

  public IZcBdPinpDao getZcBdPinpDao() {
    return zcBdPinpDao;
  }

  public void setZcBdPinpDao(IZcBdPinpDao zcBdPinpDao) {
    this.zcBdPinpDao = zcBdPinpDao;
  }

  public List getPinpList(ElementConditionDto dto, RequestMeta meta) throws SQLException {

    List list = zcBdPinpDao.getPinpList(dto, meta);

    ZcSUtil.setBillDBDigest(list);

    return list;

  }

  public ZcBBrand selectByPrimaryKey(String primKey, RequestMeta requestMeta) throws SQLException {

    ZcBBrand bill = zcBdPinpDao.selectByPrimaryKey(primKey);

    bill.setDbDigest(bill.digest());

    return bill;
  }

  public int deleteByPrimaryKey(String zcPrimKey, RequestMeta requestMeta) throws SQLException {
    return zcBdPinpDao.deleteByPrimaryKey(zcPrimKey);
  }

  public ZcBBrand updateByPrimaryKey(ZcBBrand zcBBrand, RequestMeta meta) throws SQLException {
    String code = "";

    String userId = meta.getSvUserID();

    if ("".equals(ZcSUtil.safeString(zcBBrand.getZcBraCode())) || zcBBrand.getZcBraCode().equals("自动编号")) {

      code = NumUtil.getInstance().getNo("ZC_ZB_PINP", "ZC_BRA_CODE", zcBBrand);

      zcBBrand.setZcBraCode(code);

      zcBBrand.setZcInputCode(userId);

      zcBBrand.setZcInputDate(meta.getSysDate());

      zcBdPinpDao.insert(zcBBrand);

    } else {

      code = zcBBrand.getZcBraCode();

      zcBBrand.setZcOperCode(userId);

      zcBBrand.setZcOperDate(meta.getSysDate());

      ZcBBrand originalBean = this.selectByPrimaryKey(code, meta);

      ZcSUtil.checkDigest(zcBBrand, originalBean, "zcBraCode");//一致性校验

      zcBdPinpDao.updateByPrimaryKey(zcBBrand);
    }

    return this.selectByPrimaryKey(code, meta);
  }

}
