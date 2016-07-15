package com.ufgov.zc.server.zc.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcBMerDiscount;
import com.ufgov.zc.common.zc.model.ZcBMerchandise;
import com.ufgov.zc.server.system.util.NumUtil;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.dao.ibatis.BaseDao;
import com.ufgov.zc.server.zc.service.IZcBMerchandiseService;

public class ZcBMerchandiseService implements IZcBMerchandiseService {

  private BaseDao baseDao;

  //private IZcBMerchandiseDao zcBMerchandiseDao;

  public BaseDao getBaseDao() {
    return baseDao;
  }

  public void setBaseDao(BaseDao baseDao) {
    this.baseDao = baseDao;
  }

  /*public IZcBMerchandiseDao getZcBMerchandiseDao() {
    return zcBMerchandiseDao;
  }

  public void setZcBMerchandiseDao(IZcBMerchandiseDao zcBMerchandiseDao) {
    this.zcBMerchandiseDao = zcBMerchandiseDao;
  }*/

  public List getMerchandiseList(ElementConditionDto dto, RequestMeta meta) throws SQLException {

    List list = baseDao.query("ZcBMerchandise.getMerchandiseList", dto);

    for (int i = 0; i < list.size(); i++) {

      ZcBMerchandise bill = (ZcBMerchandise) list.get(i);

      List itemList = baseDao.query("ZcBMerDiscount.getZcBMerDiscountListByMerCode", bill.getZcMerCode());

      bill.setItemList(itemList);

    }

    ZcSUtil.setBillDBDigest(list);

    return list;

  }

  public ZcBMerchandise selectByPrimaryKey(String primKey, RequestMeta requestMeta) throws SQLException {

    // ZcBMerchandise  bill = zcBMerchandiseDao.getMerchandiseInfo(primKey);

    ZcBMerchandise bill = (ZcBMerchandise) baseDao.read("ZcBMerchandise.getMerchandiseInfo", primKey);

    List itemList = baseDao.query("ZcBMerDiscount.getZcBMerDiscountListByMerCode", primKey);

    bill.setItemList(itemList);

    bill.setDbDigest(bill.digest());

    return bill;
  }

  public void deleteByPrimaryKey(String zcPrimKey, RequestMeta requestMeta) throws SQLException {
    // zcBMerchandiseDao.deleteMerchandise(zcPrimKey);

    baseDao.delete("ZcBMerchandise.deleteMerchandise", zcPrimKey);

    baseDao.delete("ZcBMerDiscount.deleteZcBMerDiscount", zcPrimKey);
  }

  public ZcBMerchandise updateByPrimaryKey(ZcBMerchandise bean, RequestMeta meta) throws SQLException {

    List diseList = bean.getItemList();

    if (diseList == null || diseList.size() == 0)

      throw new RuntimeException("商品价格明细不能为空");

    String code = "";

    String userId = meta.getSvUserID();

    if ("".equals(ZcSUtil.safeString(bean.getZcMerCode())) || bean.getZcMerCode().equals("自动编号")) {

      code = NumUtil.getInstance().getNo("ZC_B_MERCHANDISE", "ZC_MER_CODE", bean);

      bean.setZcMerCode(code);

      bean.setZcCatalogueYear(bean.getZcYear());

      baseDao.insert("ZcBMerchandise.addMerchandiseNW", bean);

    } else {

      code = bean.getZcMerCode();

      ZcBMerchandise originalBean = this.selectByPrimaryKey(code, meta);

      ZcSUtil.checkDigest(bean, originalBean, "zcMerCode");//一致性校验

      baseDao.update("ZcBMerchandise.updateMerchandiseNW", bean);

      baseDao.delete("ZcBMerDiscount.deleteZcBMerDiscount", code);
    }

    for (int i = 0; i < diseList.size(); i++) {

      ZcBMerDiscount bi = (ZcBMerDiscount) diseList.get(i);

      bi.setZcMerCode(bean.getZcMerCode());

      baseDao.insert("ZcBMerDiscount.addZcBMerDiscount", bi);

    }

    return this.selectByPrimaryKey(code, meta);
  }

  
  public void deleteMerchandise(ZcBMerchandise merchandise, RequestMeta meta) throws Exception {
    baseDao.delete("ZcBMerchandise.deleteMerchandise", merchandise);
  }

}
